package com.laboratorio.counterapiinterface;

import com.laboratorio.counterapiinterface.model.CounterMediaAttachment;
import com.laboratorio.counterapiinterface.model.CounterStatus;

/**
 *
 * @author Rafael
 * @version 1.0
 * @created 07/09/2024
 * @updated 12/09/2024
 */
public interface CounterStatusApi {
    CounterStatus postStatus(String text);
    CounterStatus postStatus(String text, String imagenId);
    CounterMediaAttachment uploadImage(String filePath);
}