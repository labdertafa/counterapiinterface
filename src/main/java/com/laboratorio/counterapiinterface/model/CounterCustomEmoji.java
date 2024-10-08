package com.laboratorio.counterapiinterface.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 *
 * @author Rafael
 * @version 1.0
 * @created 10/07/2024
 * @updated 10/07/2024
 */

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class CounterCustomEmoji {
    private String shortcode;
    private String url;
    private String static_url;
    private boolean visible_in_picker;
    private String category;
}
