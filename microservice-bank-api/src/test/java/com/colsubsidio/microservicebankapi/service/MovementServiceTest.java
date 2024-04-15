package com.colsubsidio.microservicebankapi.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Collections;
import java.util.Optional;

import com.colsubsidio.microservicebankapi.domain.dto.MovementDTO;
import com.colsubsidio.microservicebankapi.domain.entity.Account;
import com.colsubsidio.microservicebankapi.domain.entity.Movement;
import com.colsubsidio.microservicebankapi.domain.repository.AccountRepository;
import com.colsubsidio.microservicebankapi.domain.repository.MovementRepository;
import com.colsubsidio.microservicebankapi.service.impl.MovementService;

@ExtendWith(MockitoExtension.class)
public class MovementServiceTest {

    @Mock
    private MovementRepository movementRepository;
    @Mock
    private AccountRepository accountRepository;

    private ModelMapper modelMapper = new ModelMapper();

    @Captor
    private ArgumentCaptor<Account> accountCaptor;

    @InjectMocks
    private MovementService movementService;

    private Movement movement;
    private MovementDTO movementDTO;
    private Account account;

    @BeforeEach
    void setUp() {

        movementService.setModelMapper(modelMapper);
        account = new Account();
        account.setAccountId(1L);
        account.setBalance(new BigDecimal("1000"));

        movement = new Movement();
        movement.setMovementId(1L);
        movement.setAccount(account);
        movement.setAmount(new BigDecimal("100"));
        movement.setMovementDate(LocalDate.now());

        movementDTO = new MovementDTO();
        movementDTO.setAccountId(1L);
        movementDTO.setAmount(new BigDecimal("100"));
        movementDTO.setMovementTypeId(1L); 

        when(accountRepository.findById(anyLong())).thenReturn(Optional.of(account));
    }

    @Test
    void testSaveMovementIncreasesBalanceForCredit() {
        movementDTO.setMovementTypeId(1L);
        when(movementRepository.save(any(Movement.class))).thenReturn(movement);
        MovementDTO savedMovement = movementService.save(movementDTO);
        
        assertNotNull(savedMovement);
        verify(accountRepository).save(accountCaptor.capture());
        assertEquals(0, accountCaptor.getValue().getBalance().compareTo(new BigDecimal("1100")));
    }

    @Test
    void testSaveMovementDecreasesBalanceForDebit() {
        movementDTO.setMovementTypeId(2L);
        when(movementRepository.save(any(Movement.class))).thenReturn(movement);
        MovementDTO savedMovement = movementService.save(movementDTO);
        
        assertNotNull(savedMovement);
        verify(accountRepository).save(accountCaptor.capture());
        assertEquals(0, accountCaptor.getValue().getBalance().compareTo(new BigDecimal("900")));
    }

}
