package com.laboratorio.counterapiinterface.model;

/**
 *
 * @author Rafael
 * @version 1.0
 * @created 25/07/2024
 * @updated 25/07/2024
 */
public enum CounterNotificationType {
    MENTION, STATUS, REBLOG, FOLLOW, FOLLOW_REQUEST, FAVOURITE, POLL, UPDATE, ADMIN_SIGN_UP, ADMIN_REPORT, SEVERED_RELATIONSHIPS, MODERATION_WARNING;
    
    public static CounterNotificationType fromString(String value) {
        for (CounterNotificationType type : CounterNotificationType.values()) {
            if (type.name().equalsIgnoreCase(value)) {
                return type;
            }
        }
        throw new IllegalArgumentException("No enum constant " + CounterNotificationType.class.getCanonicalName() + "." + value);
    }
}