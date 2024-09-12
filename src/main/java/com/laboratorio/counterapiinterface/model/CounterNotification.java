package com.laboratorio.counterapiinterface.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 *
 * @author Rafael
 * @version 1.0
 * @created 25/07/2024
 * @updated 25/07/2024
 */

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class CounterNotification {
    private String id;
    private String type;
    private String created_at;
    private CounterAccount account;
    private CounterStatus status;
    private CounterReport report;
    private CounterRelationshipSeveranceEvent relationship_severance_event;
    private CounterAccountWarning moderation_warning;
}