package com.laboratorio.counterapiinterface.model.response;

import com.laboratorio.counterapiinterface.model.CounterAccount;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author Rafael
 * @version 1.0
 * @created 10/07/2024
 * @updated 16/08/2024
 */
@Getter @Setter @AllArgsConstructor
public class CounterAccountListResponse {
    private String maxId;
    private List<CounterAccount> accounts;
}