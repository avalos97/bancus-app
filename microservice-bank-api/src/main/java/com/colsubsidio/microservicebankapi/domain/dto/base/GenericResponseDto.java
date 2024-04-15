package com.colsubsidio.microservicebankapi.domain.dto.base;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GenericResponseDto<T> {
    
    private T data;
    private String message;
}
