package com.colsubsidio.microservicebankapi.service;

import com.colsubsidio.microservicebankapi.domain.dto.consumer.ChuckNorrisJokeDTO;

public interface IChuckNorrisJokeService {
    
    ChuckNorrisJokeDTO getJoke();
}
