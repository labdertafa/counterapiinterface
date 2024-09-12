package com.laboratorio.counterapiinterface.utils;

import java.io.FileReader;
import java.util.Properties;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 *
 * @author Rafael
 * @version 1.0
 * @created 16/08/2024
 * @updated 21/08/2024
 */
public class CounterApiConfig {
    private static final Logger log = LogManager.getLogger(CounterApiConfig.class);
    private static CounterApiConfig instance;
    private final Properties properties;

    private CounterApiConfig() {
        properties = new Properties();
        loadProperties();
    }

    private void loadProperties() {
        try {
            this.properties.load(new FileReader("config//counter_api.properties"));
        } catch (Exception e) {
            log.error("Ha ocurrido un error leyendo el fichero de configuración del API de Counter Social. Finaliza la aplicación!");
            log.error(String.format("Error: %s", e.getMessage()));
            if (e.getCause() != null) {
                log.error(String.format("Causa: %s", e.getCause().getMessage()));
            }
            System.exit(-1);
        }
    }

    public static CounterApiConfig getInstance() {
        if (instance == null) {
            synchronized (CounterApiConfig.class) {
                if (instance == null) {
                    instance = new CounterApiConfig();
                }
            }
        }
        
        return instance;
    }

    public String getProperty(String key) {
        return properties.getProperty(key);
    }
}