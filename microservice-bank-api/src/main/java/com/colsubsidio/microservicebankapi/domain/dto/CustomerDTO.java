package com.colsubsidio.microservicebankapi.domain.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CustomerDTO implements Serializable{

    private Long customerId;
    private String name;
    private String address;
    private String phone;
    private List<AccountDTO> accounts = new ArrayList<>();

}
