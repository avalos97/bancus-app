package com.colsubsidio.microservicebankapi.service.impl;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;

import com.colsubsidio.microservicebankapi.components.exception.custom.RecordNotFoundException;
import com.colsubsidio.microservicebankapi.components.exception.custom.TransactionExceptionCustom;
import com.colsubsidio.microservicebankapi.components.util.BaseMapper;
import com.colsubsidio.microservicebankapi.domain.dto.MovementDTO;
import com.colsubsidio.microservicebankapi.domain.entity.Movement;
import com.colsubsidio.microservicebankapi.domain.repository.AccountRepository;
import com.colsubsidio.microservicebankapi.domain.repository.MovementRepository;
import com.colsubsidio.microservicebankapi.service.IMovementService;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class MovementService extends BaseMapper<Movement, MovementDTO> implements IMovementService {

    private final MovementRepository repository;
    private final AccountRepository accountRepository;

    @Override
    public MovementDTO save(MovementDTO dto) {
        accountRepository.findById(dto.getAccountId()).ifPresentOrElse(account -> {
            BigDecimal newBalance = BigDecimal.ZERO;
            if (dto.getMovementTypeId() == 1) {
                newBalance = account.getBalance().add(dto.getAmount());
            } else if (dto.getMovementTypeId() == 2) {
                newBalance = account.getBalance().subtract(dto.getAmount());
            }

            if (newBalance.compareTo(BigDecimal.ZERO) < 0) {
                throw new TransactionExceptionCustom("The account balance can't be negative");
            }
            account.setBalance(newBalance);
            accountRepository.save(account);
        }, () -> {
            throw new TransactionExceptionCustom("The account does not exist");
        });
        Movement entity = repository.save(this.dtoToEntity(dto));
        return this.entityToDto(entity);
    }

    @Override
    public MovementDTO update(Long id, MovementDTO dto) {
        accountRepository.findById(dto.getAccountId()).ifPresent(account -> {
            Movement movement = repository.findById(id).orElseThrow(() -> new RecordNotFoundException(id));

            BigDecimal newBalance = BigDecimal.ZERO;
            if (dto.getMovementTypeId() == 1) {
                newBalance = account.getBalance().subtract(movement.getAmount()).add(dto.getAmount());
            } else if (dto.getMovementTypeId() == 2) {
                newBalance = account.getBalance().add(movement.getAmount()).subtract(dto.getAmount());
            }
            if (newBalance.compareTo(BigDecimal.ZERO) < 0) {
                throw new TransactionExceptionCustom("The account balance can't be negative");
            }
            account.setBalance(newBalance);
            accountRepository.save(account);
        });

        dto.setMovementId(id);
        Movement entity = this.dtoToEntity(dto);
        return this.entityToDto(repository.save(entity));
    }

    @Override
    public void delete(Long id) {
        Movement movement = repository.findById(id).orElseThrow(() -> new RecordNotFoundException(id));
        accountRepository.findById(movement.getAccount().getAccountId()).ifPresent(account -> {
            BigDecimal newBalance = BigDecimal.ZERO;
            if (movement.getMovementType().getMovementTypeId() == 1) {
                newBalance = account.getBalance().subtract(movement.getAmount());
            } else if (movement.getMovementType().getMovementTypeId() == 2) {
                newBalance = account.getBalance().add(movement.getAmount());
            }
            account.setBalance(newBalance);
            accountRepository.save(account);
        });
        repository.deleteById(id);
    }

    @Override
    public MovementDTO get(Long id) {

        Movement entity = repository.findById(id).orElseThrow(() -> new RecordNotFoundException(id));
        return this.entityToDto(entity);
    }

    @Override
    public List<MovementDTO> getAll() {
        List<MovementDTO> list = this.entityListToDtoList(repository.findAll());
        return list;
    }

    @Override
    public List<MovementDTO> getMovementsByAccount(Long accountId) {
        accountRepository.findById(accountId)
                .orElseThrow(() -> new TransactionExceptionCustom("The account does not exist"));
        return this.entityListToDtoList(repository.findByAccount_AccountId(accountId));
    }

    @Override
    public List<MovementDTO> getMovementsByCriteria(Long customerId, LocalDate startDate, LocalDate endDate) {
        List<MovementDTO> list = this.entityListToDtoList(repository.findAllByCustomerAndDateRange(customerId, startDate, endDate));
        return list;
    }

    @Override
    protected Class<Movement> getEntityClass() {
        return Movement.class;
    }

    @Override
    protected Class<MovementDTO> getDtoClass() {
        return MovementDTO.class;
    }

}
