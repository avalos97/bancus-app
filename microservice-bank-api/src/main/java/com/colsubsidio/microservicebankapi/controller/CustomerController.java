package com.colsubsidio.microservicebankapi.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.colsubsidio.microservicebankapi.domain.dto.AccountDTO;
import com.colsubsidio.microservicebankapi.domain.dto.CustomerDTO;
import com.colsubsidio.microservicebankapi.domain.dto.base.GenericResponseDto;
import com.colsubsidio.microservicebankapi.service.ICustomerService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(value = "/customer")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class CustomerController {
    
    private final ICustomerService customerService;

    @GetMapping
    public ResponseEntity<GenericResponseDto<List<CustomerDTO>>> getAll() {
        List<CustomerDTO> response = customerService.getAll();
        GenericResponseDto<List<CustomerDTO>> result = new GenericResponseDto<>();
        result.setData(response);
        result.setMessage("Customers found");
        return ResponseEntity.ok(result);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<GenericResponseDto<CustomerDTO>> get(@PathVariable(name = "id") Long id) {
        CustomerDTO response = customerService.get(id);
        GenericResponseDto<CustomerDTO> result = new GenericResponseDto<>();
        result.setData(response);
        result.setMessage("Customer found");
        return ResponseEntity.ok(result);
    }

    @PostMapping
    public ResponseEntity<GenericResponseDto<CustomerDTO>> save(@RequestBody CustomerDTO request) {

        CustomerDTO response = customerService.save(request);
        GenericResponseDto<CustomerDTO> result = new GenericResponseDto<>();
        result.setData(response);
        result.setMessage("Customer created successfully");
        return ResponseEntity.ok(result);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<GenericResponseDto<CustomerDTO>> update(@PathVariable(name = "id") Long id, @RequestBody CustomerDTO request) {
        CustomerDTO response = customerService.update(id, request);
        GenericResponseDto<CustomerDTO> result = new GenericResponseDto<>();
        result.setData(response);
        result.setMessage("Customer updated successfully");
        return ResponseEntity.ok(result);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable(name = "id") Long id) {
        customerService.delete(id);
        return ResponseEntity.ok().build();
    }

    @PostMapping(value = "/{id}/account")
    public ResponseEntity<GenericResponseDto<List<AccountDTO>>> saveAccountByCustomer(@PathVariable(name = "id") Long customerId, @RequestBody AccountDTO saveAccount) {
        List<AccountDTO> response = customerService.saveAccountByCustomer(customerId, saveAccount);
        GenericResponseDto<List<AccountDTO>> result = new GenericResponseDto<>();
        result.setData(response);
        result.setMessage("Account created successfully");
        return ResponseEntity.ok(result);
    }

    @PutMapping(value = "/{id}/account/{accountId}")
    public ResponseEntity<GenericResponseDto<List<AccountDTO>>> updateAccountByCustomer(@PathVariable(name = "id") Long customerId,@PathVariable(name = "accountId") Long accountId, @RequestBody AccountDTO updateAccount) {
        List<AccountDTO> response = customerService.updateAccountByCustomer(customerId, accountId, updateAccount);
        GenericResponseDto<List<AccountDTO>> result = new GenericResponseDto<>();
        result.setData(response);
        result.setMessage("Account updated successfully");
        return ResponseEntity.ok(result);
    }

    @DeleteMapping(value = "/{id}/account/{accountId}")
    public ResponseEntity<Void> deleteAccountByCustomer(@PathVariable(name = "id") Long customerId, @PathVariable(name = "accountId") Long accountId) {
        customerService.deleteAccountByCustomer(customerId, accountId);
        return ResponseEntity.ok().build();
    }
}
