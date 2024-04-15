package com.colsubsidio.microservicebankapi.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.colsubsidio.microservicebankapi.domain.dto.base.GenericResponseDto;
import com.colsubsidio.microservicebankapi.domain.dto.consumer.ChuckNorrisJokeDTO;
import com.colsubsidio.microservicebankapi.service.impl.ChuckNorrisJokeService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(value = "/chuck-norris-client")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class ChuckNorrisClientController {
    
    private final ChuckNorrisJokeService chuckNorrisJokeService;
    
    @GetMapping
    public ResponseEntity<GenericResponseDto<ChuckNorrisJokeDTO>> getJoke() {
        ChuckNorrisJokeDTO response = chuckNorrisJokeService.getJoke();
        GenericResponseDto<ChuckNorrisJokeDTO> result = new GenericResponseDto<>();
        result.setData(response);
        result.setMessage("Joke found");
        return ResponseEntity.ok(result);
    }
}
