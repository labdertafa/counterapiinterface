package com.laboratorio.counterapiinterface;

import com.laboratorio.counterapiinterface.model.CounterAccount;
import com.laboratorio.counterapiinterface.model.CounterRelationship;
import com.laboratorio.counterapiinterface.model.response.CounterAccountListResponse;
import java.util.List;

/**
 *
 * @author Rafael
 * @version 1.0
 * @created 05/09/2024
 * @updated 11/09/2024
 */
public interface CounterAccountApi {
    CounterAccount getAccountById(String userId);
    
    CounterAccountListResponse getFollowers(String userId) throws Exception;
    CounterAccountListResponse getFollowers(String id, int limit) throws Exception;
    CounterAccountListResponse getFollowers(String userId, int limit, int quantity) throws Exception;
    CounterAccountListResponse getFollowers(String userId, int limit, int quantity, String posicionInicial) throws Exception;
    
    CounterAccountListResponse getFollowings(String userId) throws Exception;
    CounterAccountListResponse getFollowings(String id, int limit) throws Exception;
    CounterAccountListResponse getFollowings(String userId, int limit, int quantity) throws Exception;
    CounterAccountListResponse getFollowings(String userId, int limit, int quantity, String posicionInicial) throws Exception;
    
    // Seguir a un usuario
    boolean followAccount(String userId);
    // Dejar de seguir a un usuario
    boolean unfollowAccount(String userId);
    
    // Chequea la relación con otro usuario
    List<CounterRelationship> checkrelationships(List<String> ids);
}