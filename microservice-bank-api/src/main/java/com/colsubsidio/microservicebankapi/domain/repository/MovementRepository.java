package com.colsubsidio.microservicebankapi.domain.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.colsubsidio.microservicebankapi.domain.entity.Movement;

@Repository
public interface MovementRepository extends JpaRepository<Movement, Long> {

    @Query("SELECT m FROM Movement m JOIN m.account a JOIN a.customer c " +
            "WHERE c.customerId = :customerId " +
            "AND m.movementDate BETWEEN :startDate AND :endDate")
    List<Movement> findAllByCustomerAndDateRange(@Param("customerId") Long customerId,
            @Param("startDate") LocalDate startDate,
            @Param("endDate") LocalDate endDate);

    List<Movement> findByAccount_AccountId(Long accountId);
}
