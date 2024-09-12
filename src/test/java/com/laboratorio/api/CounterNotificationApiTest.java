package com.laboratorio.api;

import com.laboratorio.counterapiinterface.CounterNotificationApi;
import com.laboratorio.counterapiinterface.exception.CounterApiException;
import com.laboratorio.counterapiinterface.impl.CounterNotificationApiImpl;
import com.laboratorio.counterapiinterface.model.response.CounterNotificationListResponse;
import com.laboratorio.counterapiinterface.utils.CounterApiConfig;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 *
 * @author Rafael
 * @version 1.0
 * @created 11/09/2024
 * @updated 11/09/2024
 */
public class CounterNotificationApiTest {
    private CounterNotificationApi notificationApi;
    
    @BeforeEach
    private void initNotificationApi() {
        String accessToken = CounterApiConfig.getInstance().getProperty("access_token");
        this.notificationApi = new CounterNotificationApiImpl(accessToken);
    }
    
    @Test
    public void get20Notifications() throws Exception { // Con default limit
        int cantidad  = 20;
        
        CounterNotificationListResponse notificationListResponse = this.notificationApi.getAllNotifications(0, cantidad);

        assertTrue(!notificationListResponse.getNotifications().isEmpty());
        assertTrue(notificationListResponse.getMinId() != null);
    }
    
    @Test
    public void get20NotificationsWithLimit() throws Exception { // Con limit
        int cantidad  = 20;
        int limit = 50;
        
        CounterNotificationListResponse notificationListResponse = this.notificationApi.getAllNotifications(limit, cantidad);

        assertTrue(!notificationListResponse.getNotifications().isEmpty());
        assertTrue(notificationListResponse.getMinId() != null);
    }
    
    @Test
    public void getAllNotifications() throws Exception {
        CounterNotificationListResponse notificationListResponse = this.notificationApi.getAllNotifications(80);

        assertTrue(!notificationListResponse.getNotifications().isEmpty());
        assertTrue(notificationListResponse.getMinId() != null);
    }
    
    @Test
    public void getNotificationError() {
        this.notificationApi = new CounterNotificationApiImpl("INVALID_TOKEN");

        assertThrows(CounterApiException.class, () -> {
            this.notificationApi.getAllNotifications();
        });
    }
}