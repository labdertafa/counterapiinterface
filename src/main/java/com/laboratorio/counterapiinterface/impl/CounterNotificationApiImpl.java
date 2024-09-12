package com.laboratorio.counterapiinterface.impl;

import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import com.laboratorio.clientapilibrary.model.ApiRequest;
import com.laboratorio.clientapilibrary.model.ProcessedResponse;
import com.laboratorio.counterapiinterface.CounterNotificationApi;
import com.laboratorio.counterapiinterface.exception.CounterApiException;
import com.laboratorio.counterapiinterface.model.CounterNotification;
import com.laboratorio.counterapiinterface.model.response.CounterNotificationListResponse;
import java.util.List;

/**
 *
 * @author Rafael
 * @version 1.0
 * @created 11/09/2024
 * @updated 11/09/2024
 */
public class CounterNotificationApiImpl extends CounterBaseApi implements CounterNotificationApi {
    public CounterNotificationApiImpl(String accessToken) {
        super(accessToken);
    }

    @Override
    public CounterNotificationListResponse getAllNotifications() throws Exception {
        return this.getAllNotifications(0);
    }

    @Override
    public CounterNotificationListResponse getAllNotifications(int limit) throws Exception {
        return this.getAllNotifications(limit, 0);
    }

    @Override
    public CounterNotificationListResponse getAllNotifications(int limit, int quantity) throws Exception {
        return this.getAllNotifications(limit, quantity, null);
    }
    
    // Función que devuelve una página de notificaciones de una cuenta
    private CounterNotificationListResponse getNotificationPage(String uri, int limit, int okStatus, String posicionInicial) throws Exception {
        try {
            ApiRequest request = new ApiRequest(uri, okStatus);
            request.addApiPathParam("limit", Integer.toString(limit));
            request.addApiPathParam("min_id", posicionInicial);
            
            request.addApiHeader("Content-Type", "application/json");
            request.addApiHeader("Authorization", "Bearer " + this.accessToken);
            
            ProcessedResponse response = this.client.getProcessedResponseGetRequest(request);
            
            String minId = posicionInicial;
            List<CounterNotification> notifications = gson.fromJson(response.getResponseDetail(), new TypeToken<List<CounterNotification>>(){}.getType());
            if (!notifications.isEmpty()) {
                log.debug("Se ejecutó la query: " + uri);
                log.debug("Resultados encontrados: " + notifications.size());

                String linkHeader = response.getResponse().getHeaderString("link");
                log.debug("Recibí este link: " + linkHeader);
                minId = this.extractMinId(linkHeader);
                log.debug("Valor del min_id: " + minId);
            }

            // return accounts;
            return new CounterNotificationListResponse(minId, notifications);
        } catch (JsonSyntaxException e) {
            logException(e);
            throw e;
        } catch (Exception e) {
            throw new CounterApiException(CounterNotificationApiImpl.class.getName(), e.getMessage());
        }
    }

    @Override
    public CounterNotificationListResponse getAllNotifications(int limit, int quantity, String posicionInicial) throws Exception {
        String endpoint = this.apiConfig.getProperty("getNotifications_endpoint");
        int okStatus = Integer.parseInt(this.apiConfig.getProperty("getNotifications_ok_status"));
        int defaultLimit = Integer.parseInt(this.apiConfig.getProperty("getNotifications_default_limit"));
        int maxLimit = Integer.parseInt(this.apiConfig.getProperty("getNotifications_max_limit"));
        int usedLimit = limit;
        if ((limit == 0) || (limit > maxLimit)) {
            usedLimit = defaultLimit;
        }
        List<CounterNotification> notifications = null;
        boolean continuar = true;
        String min_id = "0";
        if (posicionInicial != null) {
            min_id = posicionInicial;
        }
        
        if (quantity > 0) {
            usedLimit = Math.min(usedLimit, quantity);
        }
        
        try {
            do {
                CounterNotificationListResponse notificationListResponse = this.getNotificationPage(endpoint, usedLimit, okStatus, min_id);
                if (notifications == null) {
                    notifications = notificationListResponse.getNotifications();
                } else {
                    notifications.addAll(notificationListResponse.getNotifications());
                }
                
                min_id = notificationListResponse.getMinId();
                log.debug("getFollowers. Cantidad: " + quantity + ". Recuperados: " + notifications.size() + ". Min_id: " + min_id);
                if (notificationListResponse.getNotifications().isEmpty()) {
                    continuar = false;
                } else {
                    if (quantity > 0) {
                        if (notifications.size() >= quantity) {
                            continuar = false;
                        }
                    } else {
                        if (notificationListResponse.getNotifications().size() < usedLimit) {
                            continuar = false;
                        }
                    }
                }
            } while (continuar);

            if (quantity == 0) {
                return new CounterNotificationListResponse(min_id, notifications);
            }
            
            return new CounterNotificationListResponse(min_id, notifications.subList(0, Math.min(quantity, notifications.size())));
        } catch (Exception e) {
            throw e;
        }
    }
}