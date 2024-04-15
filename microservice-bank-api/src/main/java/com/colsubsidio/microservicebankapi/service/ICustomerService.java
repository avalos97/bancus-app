package com.colsubsidio.microservicebankapi.service;

import java.util.List;

import com.colsubsidio.microservicebankapi.domain.dto.AccountDTO;
import com.colsubsidio.microservicebankapi.domain.dto.CustomerDTO;

public interface ICustomerService extends IBaseService<CustomerDTO, Long>{

    // List<AccountDTO> getAccountsByCustomer(Long id);
    List<AccountDTO> saveAccountByCustomer(Long customerId, AccountDTO saveAccount);
    List<AccountDTO> updateAccountByCustomer(Long customerId, Long accountId, AccountDTO updateAccount);
    void deleteAccountByCustomer(Long customerId, Long accountId);
}
