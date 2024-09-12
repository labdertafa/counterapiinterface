package com.laboratorio.counterapiinterface.model.response;

import com.laboratorio.counterapiinterface.model.CounterNotification;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author Rafael
 * @version 1.0
 * @created 25/07/2024
 * @updated 05/09/2024
 */
@Getter @Setter @AllArgsConstructor
public class CounterNotificationListResponse {
    private String minId;
    private List<CounterNotification> notifications;
}