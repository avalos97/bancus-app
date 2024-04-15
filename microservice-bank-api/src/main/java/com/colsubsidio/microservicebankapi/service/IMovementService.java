package com.colsubsidio.microservicebankapi.service;

import java.time.LocalDate;
import java.util.List;

import com.colsubsidio.microservicebankapi.domain.dto.MovementDTO;

public interface IMovementService extends IBaseService<MovementDTO, Long>{
    
    List<MovementDTO> getMovementsByAccount(Long accountId);

    List<MovementDTO> getMovementsByCriteria(Long customerId, LocalDate startDate, LocalDate endDate);
}
