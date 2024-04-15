package com.colsubsidio.microservicebankapi.domain.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
public class MovementDTO implements Serializable{
    
    private Long movementId;
    private BigDecimal amount;
    private LocalDate movementDate;

    private Long accountId;
    private String number;

    private Long movementTypeId;
    private String description;
}

