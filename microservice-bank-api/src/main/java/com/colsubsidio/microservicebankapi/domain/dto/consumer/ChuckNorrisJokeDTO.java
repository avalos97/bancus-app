package com.colsubsidio.microservicebankapi.domain.dto.consumer;

import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ChuckNorrisJokeDTO {

    private List<String> categories;
    @JsonProperty("icon_url")
    private String iconUrl;
    private String id;
    private String url;
    private String value;

}
