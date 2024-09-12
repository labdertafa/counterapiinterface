package com.laboratorio.counterapiinterface.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 *
 * @author Rafael
 * @version 1.0
 * @created 24/07/2024
 * @updated 24/07/2024
 */

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
class CounterFilter {
    private String id;
    private String title;
    private String[] context;
    private String expires_at;
    private String filter_action;
    private CounterFilterKeyword[] keywords;
    private CounterFilterStatus[] statuses;
}