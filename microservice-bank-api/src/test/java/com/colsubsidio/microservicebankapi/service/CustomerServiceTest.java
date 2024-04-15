package com.colsubsidio.microservicebankapi.service;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import com.colsubsidio.microservicebankapi.domain.dto.CustomerDTO;
import com.colsubsidio.microservicebankapi.domain.entity.Customer;
import com.colsubsidio.microservicebankapi.domain.repository.CustomerRepository;
import com.colsubsidio.microservicebankapi.service.impl.AccountService;
import com.colsubsidio.microservicebankapi.service.impl.CustomerService;

@ExtendWith(MockitoExtension.class)
class CustomerServiceTest {

    @Mock
    private CustomerRepository customerRepository;
    @Mock
    private AccountService accountService;
    
    private ModelMapper modelMapper = new ModelMapper();

    @InjectMocks
    private CustomerService customerService;

    private Customer customer;
    private CustomerDTO customerDTO;

    @BeforeEach
    void setUp() {
        customer = new Customer();
        customer.setCustomerId(1L);
        customer.setName("John Doe");
        customer.setAccounts(new ArrayList<>());

        customerDTO = new CustomerDTO();
        customerDTO.setCustomerId(1L);
        customerDTO.setName("John Doe");

        customerService.setModelMapper(modelMapper);
    }

    @Test
    void testSaveCustomer() {
        when(customerRepository.save(any(Customer.class))).thenReturn(customer);
        CustomerDTO savedCustomer = customerService.save(customerDTO);
        assertNotNull(savedCustomer);
        assertEquals("John Doe", savedCustomer.getName());
    }

    @Test
    void testGetCustomer() {
        when(customerRepository.findById(anyLong())).thenReturn(Optional.of(customer));
        CustomerDTO foundCustomer = customerService.get(1L);
        assertNotNull(foundCustomer);
        assertEquals("John Doe", foundCustomer.getName());
    }

    @Test
    void testUpdateCustomer() {
        when(customerRepository.findById(anyLong())).thenReturn(Optional.of(customer));
        when(customerRepository.save(any(Customer.class))).thenReturn(customer);
        CustomerDTO updatedCustomer = customerService.update(1L, customerDTO);
        assertNotNull(updatedCustomer);
        assertEquals("John Doe", updatedCustomer.getName());
    }

    @Test
    void testDeleteCustomer() {
        when(customerRepository.findById(anyLong())).thenReturn(Optional.of(customer));
        doNothing().when(customerRepository).deleteById(anyLong());
        assertDoesNotThrow(() -> customerService.delete(1L));
    }

    @Test
    void testGetAllCustomers() {
        List<Customer> customers = new ArrayList<>();
        customers.add(customer);
        when(customerRepository.findAll()).thenReturn(customers);
        List<CustomerDTO> customerDTOs = customerService.getAll();
        assertFalse(customerDTOs.isEmpty());
        assertEquals(1, customerDTOs.size());
    }
}