package com.laboratorio.api;

import com.laboratorio.counterapiinterface.CounterStatusApi;
import com.laboratorio.counterapiinterface.exception.CounterApiException;
import com.laboratorio.counterapiinterface.impl.CounterStatusApiImpl;
import com.laboratorio.counterapiinterface.model.CounterMediaAttachment;
import com.laboratorio.counterapiinterface.model.CounterStatus;
import com.laboratorio.counterapiinterface.utils.CounterApiConfig;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 *
 * @author Rafael
 * @version 1.0
 * @created 12/09/2024
 * @updated 12/09/2024
 */

public class CounterStatusApiTest {
    private CounterStatusApi statusApi;
    
    @BeforeEach
    public void initTest() {
        String accessToken = CounterApiConfig.getInstance().getProperty("access_token");
        this.statusApi = new CounterStatusApiImpl(accessToken);
    }
    
    @Test
    public void postStatus() {
        String text = "Hola, les saludo desde El laboratorio de Rafa. Post automático";
        
        CounterStatus status = this.statusApi.postStatus(text);
        assertTrue(!status.getId().isEmpty());
        assertTrue(status.getContent().contains(text));
    }
    
    @Test
    public void postInvalidStatus() {
        this.statusApi = new CounterStatusApiImpl("INVALID_TOKEN");
        
        assertThrows(CounterApiException.class, () -> {
            this.statusApi.postStatus("");
        });
    }
    
    @Test
    public void postImage() throws Exception {
        String imagen = "C:\\Users\\rafa\\Pictures\\Formula_1\\Monza_1955.jpg";
        String text = "Hola, les saludo desde El laboratorio de Rafa. Post automático";
        
        CounterMediaAttachment media = this.statusApi.uploadImage(imagen);
        assertTrue(media.getPreview_url() != null);
        
        CounterStatus status = this.statusApi.postStatus(text, media.getId());
        assertTrue(!status.getId().isEmpty());
        assertTrue(status.getContent().contains(text));
    }
}