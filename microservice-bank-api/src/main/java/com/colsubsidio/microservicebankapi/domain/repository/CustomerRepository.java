package com.colsubsidio.microservicebankapi.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.colsubsidio.microservicebankapi.domain.entity.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

}
