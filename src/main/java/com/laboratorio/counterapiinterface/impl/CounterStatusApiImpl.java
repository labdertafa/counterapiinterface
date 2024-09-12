package com.laboratorio.counterapiinterface.impl;

import com.laboratorio.clientapilibrary.model.ApiRequest;
import com.laboratorio.counterapiinterface.CounterStatusApi;
import com.laboratorio.counterapiinterface.exception.CounterApiException;
import com.laboratorio.counterapiinterface.model.CounterMediaAttachment;
import com.laboratorio.counterapiinterface.model.CounterStatus;

/**
 *
 * @author Rafael
 * @version 1.0
 * @created 12/09/2024
 * @updated 12/09/2024
 */
public class CounterStatusApiImpl extends CounterBaseApi implements CounterStatusApi {
    public CounterStatusApiImpl(String accessToken) {
        super(accessToken);
    }

    @Override
    public CounterStatus postStatus(String text) {
        return this.postStatus(text, null);
    }

    @Override
    public CounterStatus postStatus(String text, String imagenId) {
        String endpoint = this.apiConfig.getProperty("postStatus_endpoint");
        int okStatus = Integer.parseInt(this.apiConfig.getProperty("postStatus_ok_status"));
        
        try {
            String uri = endpoint;
            ApiRequest request = new ApiRequest(uri, okStatus);
            request.addApiPathParam("status", text);
            request.addApiPathParam("visibility", "public");
            request.addApiPathParam("language", "es");
            if (imagenId != null) {
                request.addApiPathParam("media_ids[]", imagenId);
            }
            
            request.addApiHeader("Content-Type", "application/json");
            request.addApiHeader("Authorization", "Bearer " + this.accessToken);
            
            String jsonStr = this.client.executePostRequest(request);
            return this.gson.fromJson(jsonStr, CounterStatus.class);
        } catch (Exception e) {
            throw new CounterApiException(CounterAccountApiImpl.class.getName(), e.getMessage());
        }
    }

    @Override
    public CounterMediaAttachment uploadImage(String filePath) {
        String endpoint = this.apiConfig.getProperty("UploadImage_endpoint");
        int okStatus = Integer.parseInt(this.apiConfig.getProperty("UploadImage_ok_status"));
        
        try {
            String uri = endpoint;
            
            ApiRequest request = new ApiRequest(uri, okStatus);
            request.addApiHeader("Authorization", "Bearer " + this.accessToken);
            request.addFileFormData("file", filePath);
                        
            String jsonStr = this.client.executePostRequest(request);
            
            return this.gson.fromJson(jsonStr, CounterMediaAttachment.class);
        } catch (Exception e) {
            throw new CounterApiException(CounterAccountApiImpl.class.getName(), e.getMessage());
        }
    }
}