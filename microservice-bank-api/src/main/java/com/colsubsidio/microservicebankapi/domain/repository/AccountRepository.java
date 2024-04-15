package com.colsubsidio.microservicebankapi.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.colsubsidio.microservicebankapi.domain.entity.Account;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {

}

