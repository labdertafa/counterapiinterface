package com.laboratorio.counterapiinterface;

import com.laboratorio.counterapiinterface.model.response.CounterNotificationListResponse;

/**
 *
 * @author Rafael
 * @version 1.0
 * @created 11/09/2024
 * @updated 11/09/2024
 */
public interface CounterNotificationApi {
    CounterNotificationListResponse getAllNotifications() throws Exception;
    CounterNotificationListResponse getAllNotifications(int limit) throws Exception;
    CounterNotificationListResponse getAllNotifications(int limit, int quantity) throws Exception;
    CounterNotificationListResponse getAllNotifications(int limit, int quantity, String posicionInicial) throws Exception;
}