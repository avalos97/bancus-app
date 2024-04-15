package com.colsubsidio.microservicebankapi.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.colsubsidio.microservicebankapi.components.exception.custom.RecordNotFoundException;
import com.colsubsidio.microservicebankapi.components.util.BaseMapper;
import com.colsubsidio.microservicebankapi.domain.dto.AccountDTO;
import com.colsubsidio.microservicebankapi.domain.entity.Account;
import com.colsubsidio.microservicebankapi.domain.repository.AccountRepository;
import com.colsubsidio.microservicebankapi.service.IAccountService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AccountService extends BaseMapper<Account, AccountDTO> implements IAccountService{

    private final AccountRepository repository;
    
    @Override
    public AccountDTO save(AccountDTO dto) {
        Account entity = repository.save(this.dtoToEntity(dto));
        return this.entityToDto(entity);
    }

    @Override
    public AccountDTO update(Long id, AccountDTO dto) {
        this.get(id); // * validamos que exista */
        Account entity = this.dtoToEntity(dto);
        return this.entityToDto(repository.save(entity));
    }

    @Override
    public void delete(Long id) {
        this.get(id); // * validamos que exista */
        repository.deleteById(id);
    }

    @Override
    public AccountDTO get(Long id) {
        Account entity = repository.findById(id).orElseThrow(() -> new RecordNotFoundException(id));
        return this.entityToDto(entity);
    }

    @Override
    public List<AccountDTO> getAll() {
        return this.entityListToDtoList(repository.findAll());
    }

    @Override
    protected Class<Account> getEntityClass() {
        return Account.class;
    }

    @Override
    protected Class<AccountDTO> getDtoClass() {
        return AccountDTO.class;
    }
    
}
