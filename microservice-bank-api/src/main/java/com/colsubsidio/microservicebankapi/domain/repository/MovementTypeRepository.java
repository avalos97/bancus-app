package com.colsubsidio.microservicebankapi.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.colsubsidio.microservicebankapi.domain.entity.MovementType;

public interface MovementTypeRepository extends JpaRepository<MovementType, Long> {
}
