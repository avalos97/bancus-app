package com.colsubsidio.microservicebankapi.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.colsubsidio.microservicebankapi.components.exception.custom.RecordNotFoundException;
import com.colsubsidio.microservicebankapi.components.util.BaseMapper;
import com.colsubsidio.microservicebankapi.domain.dto.AccountDTO;
import com.colsubsidio.microservicebankapi.domain.dto.CustomerDTO;
import com.colsubsidio.microservicebankapi.domain.entity.Account;
import com.colsubsidio.microservicebankapi.domain.entity.Customer;
import com.colsubsidio.microservicebankapi.domain.repository.CustomerRepository;
import com.colsubsidio.microservicebankapi.service.ICustomerService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CustomerService extends BaseMapper<Customer, CustomerDTO> implements ICustomerService{

    private final CustomerRepository repository;
    private final AccountService accountService;

    @Override
    public CustomerDTO save(CustomerDTO dto) {
        Customer entity = repository.save(this.dtoToEntity(dto));
        return this.entityToDto(entity);
    }

    @Override
    public CustomerDTO update(Long id, CustomerDTO dto) {
        Customer findEntity = repository.findById(id).orElseThrow(() -> new RecordNotFoundException(id));
        dto.setCustomerId(id);
        Customer updateEntity = this.dtoToEntity(dto);
        updateEntity.setAccounts(findEntity.getAccounts());
        return this.entityToDto(repository.save(updateEntity));
    }

    @Override
    public void delete(Long id) {
        this.get(id); // * validamos que exista */
        repository.deleteById(id);
    }

    @Override
    public CustomerDTO get(Long id) {
        Customer entity = repository.findById(id).orElseThrow(() -> new RecordNotFoundException(id));
        return this.entityToDto(entity);
    }

    @Override
    public List<CustomerDTO> getAll() {
        return this.entityListToDtoList(repository.findAll());
    }

    @Override
    public List<AccountDTO> saveAccountByCustomer(Long customerId, AccountDTO accountDto) {
        Customer customer = repository.findById(customerId).orElseThrow(() -> new RecordNotFoundException(customerId));
        Account account = accountService.dtoToEntity(accountDto);
        account.setCustomer(customer);
        customer.getAccounts().add(account);
        repository.save(customer);
        return accountService.entityListToDtoList(customer.getAccounts());
    }

    @Override
    public List<AccountDTO> updateAccountByCustomer(Long customerId, Long accountId, AccountDTO accountDto) {
        Customer customer = repository.findById(customerId).orElseThrow(() -> new RecordNotFoundException(customerId));
        customer.getAccounts().forEach(account -> {
            if (account.getAccountId().equals(accountId)) {
                account.setBalance(accountDto.getBalance());
                account.setNumber(accountDto.getNumber());
            }
        });
        repository.save(customer);

        return customer.getAccounts().stream()
                .map(accountService::entityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteAccountByCustomer(Long customerId, Long accountId) {
        Customer entity = repository.findById(customerId).orElseThrow(() -> new RecordNotFoundException(customerId));
        entity.getAccounts().removeIf(account -> account.getAccountId().equals(accountId));
        repository.save(entity);
    }

    @Override
    protected Class<Customer> getEntityClass() {
        return Customer.class;
    }

    @Override
    protected Class<CustomerDTO> getDtoClass() {
        return CustomerDTO.class;
    }

    
}
