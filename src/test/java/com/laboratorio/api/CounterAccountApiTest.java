package com.laboratorio.api;

import com.laboratorio.counterapiinterface.CounterAccountApi;
import com.laboratorio.counterapiinterface.exception.CounterApiException;
import com.laboratorio.counterapiinterface.impl.CounterAccountApiImpl;
import com.laboratorio.counterapiinterface.model.CounterAccount;
import com.laboratorio.counterapiinterface.model.CounterRelationship;
import com.laboratorio.counterapiinterface.model.response.CounterAccountListResponse;
import com.laboratorio.counterapiinterface.utils.CounterApiConfig;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.TestMethodOrder;

/**
 *
 * @author Rafael
 * @version 1.0
 * @created 11/09/2024
 * @updated 11/09/2024
 */

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class CounterAccountApiTest {
    private static CounterAccountApi accountApi;
    
    @BeforeEach
    public void initTest() {
        CounterApiConfig config = CounterApiConfig.getInstance();
        String accessToken = config.getProperty("access_token");
        accountApi = new CounterAccountApiImpl(accessToken);
    }
    
    @Test
    public void getAccountById() {
        String userId = "101013";
        
        CounterAccount account = accountApi.getAccountById(userId);
        
        assertEquals(userId, account.getId());
    }
    
    @Test
    public void findAccountByInvalidId() {
        String id = "1125349753AAABBB60";
        
        assertThrows(CounterApiException.class, () -> {
            accountApi.getAccountById(id);
        });
    }
    
/*    @Test
    public void get40Followers() throws Exception {
        String id = "82711";
        int maxLimit = 80;
        int cantidad = 40;
        
        CounterAccountListResponse accountListResponse = accountApi.getFollowers(id, maxLimit, cantidad);

        assertEquals(cantidad, accountListResponse.getAccounts().size());
        assertTrue(!accountListResponse.getMaxId().isEmpty());
    }
    
    @Test
    public void get40FollowersDefaultLimit() throws Exception {
        String id = "82711";
        int defaultLimit = 0;
        int cantidad = 40;
        
        CounterAccountListResponse accountListResponse = accountApi.getFollowers(id, defaultLimit, cantidad);

        assertEquals(cantidad, accountListResponse.getAccounts().size());
        assertTrue(!accountListResponse.getMaxId().isEmpty());
    }
    
    @Test
    public void get80Followers() throws Exception {
        String id = "82711";
        int maxLimit = 80;
        int cantidad = 80;
        
        CounterAccountListResponse accountListResponse = accountApi.getFollowers(id, maxLimit, cantidad);

        assertEquals(cantidad, accountListResponse.getAccounts().size());
        assertTrue(!accountListResponse.getMaxId().isEmpty());
    }
    
    @Test
    public void get81Followers() throws Exception {
        String id = "82711";
        int maxLimit = 80;
        int cantidad = 81;
        
        CounterAccountListResponse accountListResponse = accountApi.getFollowers(id, maxLimit, cantidad);

        assertEquals(cantidad, accountListResponse.getAccounts().size());
        assertTrue(!accountListResponse.getMaxId().isEmpty());
    }
    
    @Test
    public void get200Followers() throws Exception {
        String id = "82711";
        int maxLimit = 80;
        int cantidad = 200;
        
        CounterAccountListResponse accountListResponse = accountApi.getFollowers(id, maxLimit, cantidad);

        assertEquals(cantidad, accountListResponse.getAccounts().size());
        assertTrue(!accountListResponse.getMaxId().isEmpty());
    }
    
    @Test
    public void getAllFollowers() throws Exception {     // Usa default limit
        String id = "82711";
        
        CounterAccountListResponse accountListResponse = accountApi.getFollowers(id);
        assertTrue(accountListResponse.getMaxId() == null);
        assertTrue(!accountListResponse.getAccounts().isEmpty());
    }
    
    @Test
    public void getFollowersInvalidId() {
        String id = "1125349753AAABBB60";
        
        assertThrows(CounterApiException.class, () -> {
            accountApi.getFollowers(id);
        });
    }
    
    @Test
    public void get40Followings() throws Exception {
        String id = "82711";
        int maxLimit = 80;
        int cantidad = 40;
        
        CounterAccountListResponse accountListResponse = accountApi.getFollowings(id, maxLimit, cantidad);

        assertEquals(cantidad, accountListResponse.getAccounts().size());
        assertTrue(!accountListResponse.getMaxId().isEmpty());
    }
    
    @Test
    public void get40FollowingsDefaultLimit() throws Exception {
        String id = "82711";
        int defaulLimit = 0;
        int cantidad = 40;
        
        CounterAccountListResponse accountListResponse = accountApi.getFollowings(id, defaulLimit, cantidad);

        assertEquals(cantidad, accountListResponse.getAccounts().size());
        assertTrue(!accountListResponse.getMaxId().isEmpty());
    }
    
    @Test
    public void get80Followings() throws Exception {
        String id = "82711";
        int maxLimit = 80;
        int cantidad = 80;
        
        CounterAccountListResponse accountListResponse = accountApi.getFollowings(id, maxLimit, cantidad);

        assertEquals(cantidad, accountListResponse.getAccounts().size());
        assertTrue(!accountListResponse.getMaxId().isEmpty());
    }
    
    @Test
    public void get81Followings() throws Exception {
        String id = "82711";
        int maxLimit = 80;
        int cantidad = 81;
        
        CounterAccountListResponse accountListResponse = accountApi.getFollowings(id, maxLimit, cantidad);

        assertEquals(cantidad, accountListResponse.getAccounts().size());
        assertTrue(!accountListResponse.getMaxId().isEmpty());
    }
    
    @Test
    public void get200Followings() throws Exception {
        String id = "82711";
        int maxLimit = 80;
        int cantidad = 200;
        
        CounterAccountListResponse accountListResponse = accountApi.getFollowings(id, maxLimit, cantidad);

        assertEquals(cantidad, accountListResponse.getAccounts().size());
        assertTrue(!accountListResponse.getMaxId().isEmpty());
    }

    @Test
    public void getAllFollowings() throws Exception { // Usa default limit
        String id = "82711";
        
        CounterAccountListResponse accountListResponse = accountApi.getFollowings(id);

        assertTrue(accountListResponse.getAccounts().size() >= 0);
        assertTrue(accountListResponse.getMaxId() == null);
    }
    
    @Test
    public void getFollowingsInvalidId() {
        String id = "1125349753AAABBB60";
        
        assertThrows(CounterApiException.class, () -> {
            accountApi.getFollowings(id);
        });
    }
    
    @Test @Order(1)
    public void followAccount() {
        String id = "187224";
        
        boolean result = accountApi.followAccount(id);
        
        assertTrue(result);
    }
    
    @Test
    public void followInvalidAccount() {
        String id = "1125349753AAABBB60";
        
        assertThrows(CounterApiException.class, () -> {
            accountApi.followAccount(id);
        });
    }
    
    @Test @Order(2)
    public void unfollowAccount() {
        String id = "187224";
        
        boolean result = accountApi.unfollowAccount(id);
        
        assertTrue(result);
    }
    
    @Test
    public void unfollowInvalidAccount() {
        String id = "1125349753AAABBB60";
        
        assertThrows(CounterApiException.class, () -> {
            accountApi.unfollowAccount(id);
        });
    }
    
    @Test
    public void checkRelationships() {
        List<String> ids = List.of("101013", "82711");
        
        List<CounterRelationship> list = accountApi.checkrelationships(ids);
        assertTrue(list.size() == 2);
    }
    
    @Test
    public void checkMutualRelationship() {
        List<String> ids = List.of("147362");
        
        List<CounterRelationship> list = accountApi.checkrelationships(ids);
        assertTrue(list.get(0).isFollowing());
        assertTrue(list.get(0).isFollowed_by());
    } */
}