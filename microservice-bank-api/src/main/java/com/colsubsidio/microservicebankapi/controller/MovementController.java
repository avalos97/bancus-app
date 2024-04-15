package com.colsubsidio.microservicebankapi.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.colsubsidio.microservicebankapi.domain.dto.MovementDTO;
import com.colsubsidio.microservicebankapi.domain.dto.base.GenericResponseDto;
import com.colsubsidio.microservicebankapi.service.IMovementService;

import lombok.RequiredArgsConstructor;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(value = "/movement")
@RequiredArgsConstructor
public class MovementController {

    //TODO: agregar validaciones en DTO, y unit test
    private final IMovementService movementService;

    @GetMapping
    public ResponseEntity<GenericResponseDto<List<MovementDTO>>> getAll() {
        List<MovementDTO> response = movementService.getAll();
        GenericResponseDto<List<MovementDTO>> result = new GenericResponseDto<>();
        result.setData(response);
        result.setMessage("Movements found");
        return ResponseEntity.ok(result);
    }

    @GetMapping(value = "/account/{id}")
    public ResponseEntity<GenericResponseDto<List<MovementDTO>>> getMovementsByAccount(
            @PathVariable(name = "id") Long id) {
        List<MovementDTO> response = movementService.getMovementsByAccount(id);
        GenericResponseDto<List<MovementDTO>> result = new GenericResponseDto<>();
        result.setData(response);
        result.setMessage("Movements found");
        return ResponseEntity.ok(result);
    }

    @GetMapping(value = "/by-criteria")
    public ResponseEntity<GenericResponseDto<List<MovementDTO>>> getMovementsByCriteria(
            @RequestParam(name = "customerId") Long customerId,
            @RequestParam(name = "startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam(name = "endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {
        List<MovementDTO> response = movementService.getMovementsByCriteria(customerId, startDate, endDate);
        GenericResponseDto<List<MovementDTO>> result = new GenericResponseDto<>();
        result.setData(response);
        result.setMessage("Movements found");
        return ResponseEntity.ok(result);
    }

    @PostMapping
    public ResponseEntity<GenericResponseDto<MovementDTO>> save(@RequestBody MovementDTO request) {
        MovementDTO response = movementService.save(request);
        GenericResponseDto<MovementDTO> result = new GenericResponseDto<>();
        result.setData(response);
        result.setMessage("Movement created successfully");
        return ResponseEntity.ok(result);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<GenericResponseDto<MovementDTO>> update(@PathVariable(name = "id") Long id,
            @RequestBody MovementDTO request) {
        MovementDTO response = movementService.update(id, request);
        GenericResponseDto<MovementDTO> result = new GenericResponseDto<>();
        result.setData(response);
        result.setMessage("Movement updated successfully");
        return ResponseEntity.ok(result);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable(name = "id") Long id) {
        movementService.delete(id);
        return ResponseEntity.ok().build();
    }

}
