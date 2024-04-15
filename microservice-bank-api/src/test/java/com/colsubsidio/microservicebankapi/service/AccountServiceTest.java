package com.colsubsidio.microservicebankapi.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.junit.jupiter.api.extension.ExtendWith;

import com.colsubsidio.microservicebankapi.domain.dto.AccountDTO;
import com.colsubsidio.microservicebankapi.domain.entity.Account;
import com.colsubsidio.microservicebankapi.domain.repository.AccountRepository;
import com.colsubsidio.microservicebankapi.service.impl.AccountService;

import java.math.BigDecimal;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class AccountServiceTest {

    @Mock
    private AccountRepository accountRepository;

    private ModelMapper modelMapper = new ModelMapper();

    @InjectMocks
    private AccountService accountService;

    private Account account;
    private AccountDTO accountDTO;

    @BeforeEach
    void setUp() {
        accountService.setModelMapper(modelMapper);
        account = new Account();
        account.setAccountId(1L);
        account.setBalance(new BigDecimal(100.0));
        account.setNumber("123456");

        accountDTO = new AccountDTO();
        accountDTO.setAccountId(1L);
        accountDTO.setBalance(new BigDecimal(100.0));
        accountDTO.setNumber("123456");
    }

    @Test
    void testSaveAccount() {
        when(accountRepository.save(any(Account.class))).thenReturn(account);
        AccountDTO savedDto = accountService.save(accountDTO);
        assertNotNull(savedDto);
        assertEquals(savedDto.getNumber(), accountDTO.getNumber());
    }

    @Test
    void testGetAccount() {
        when(accountRepository.findById(anyLong())).thenReturn(Optional.of(account));
        AccountDTO retrievedDto = accountService.get(1L);
        assertNotNull(retrievedDto);
        assertEquals(retrievedDto.getNumber(), account.getNumber());
    }

    @Test
    void testUpdateAccount() {
        when(accountRepository.findById(anyLong())).thenReturn(Optional.of(account));
        when(accountRepository.save(any(Account.class))).thenReturn(account);
        AccountDTO updatedDto = accountService.update(1L, accountDTO);
        assertNotNull(updatedDto);
        assertEquals(updatedDto.getNumber(), accountDTO.getNumber());
    }

    @Test
    void testDeleteAccount() {
        when(accountRepository.findById(anyLong())).thenReturn(Optional.of(account));
        doNothing().when(accountRepository).deleteById(anyLong());
        assertDoesNotThrow(() -> accountService.delete(1L));
    }
}