package com.colsubsidio.microservicebankapi.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.colsubsidio.microservicebankapi.domain.dto.consumer.ChuckNorrisJokeDTO;
import com.colsubsidio.microservicebankapi.service.IChuckNorrisJokeService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ChuckNorrisJokeService implements IChuckNorrisJokeService{
    
    private final RestTemplate restTemplate;

    private final String url = "https://api.chucknorris.io/jokes/random";

    @Override
    public ChuckNorrisJokeDTO getJoke() {
        ChuckNorrisJokeDTO joke = restTemplate.getForObject(url, ChuckNorrisJokeDTO.class);
        return joke;
    }
}
