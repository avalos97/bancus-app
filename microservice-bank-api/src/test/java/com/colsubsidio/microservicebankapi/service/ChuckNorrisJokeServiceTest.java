package com.colsubsidio.microservicebankapi.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.client.RestTemplate;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import com.colsubsidio.microservicebankapi.domain.dto.consumer.ChuckNorrisJokeDTO;
import com.colsubsidio.microservicebankapi.service.impl.ChuckNorrisJokeService;

@ExtendWith(MockitoExtension.class)
class ChuckNorrisJokeServiceTest {

    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    private ChuckNorrisJokeService jokeService;

    private ChuckNorrisJokeDTO expectedJoke;

    @BeforeEach
    void setUp() {
        expectedJoke = new ChuckNorrisJokeDTO();
        expectedJoke.setValue("Funny Chuck Norris joke");
        expectedJoke.setIconUrl("https://assets.chucknorris.host/img/avatar/chuck-norris.png");
    }

    @Test
    void testGetJokeReturnsJoke() {
        when(restTemplate.getForObject("https://api.chucknorris.io/jokes/random", ChuckNorrisJokeDTO.class)).thenReturn(expectedJoke);

        ChuckNorrisJokeDTO result = jokeService.getJoke();

        assertNotNull(result);
        assertEquals("Funny Chuck Norris joke", result.getValue());
        assertEquals("https://assets.chucknorris.host/img/avatar/chuck-norris.png", result.getIconUrl());
        verify(restTemplate).getForObject("https://api.chucknorris.io/jokes/random", ChuckNorrisJokeDTO.class);
    }
}
