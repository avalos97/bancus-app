package com.colsubsidio.microservicebankapi.domain.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;

@Getter
@Setter
public class AccountDTO implements Serializable{
    
    private Long accountId;
    private String number;
    private BigDecimal balance;
}
