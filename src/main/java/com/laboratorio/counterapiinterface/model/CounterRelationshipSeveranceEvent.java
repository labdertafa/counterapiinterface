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
public class CounterRelationshipSeveranceEvent {
    private String id;
    private String type;
    private boolean purged;
    private String target_name;
    private int relationships_count;
    private String created_at;
}