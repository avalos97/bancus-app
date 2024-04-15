package com.colsubsidio.microservicebankapi.components.util;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class BaseMapper<Entity, DTO> {

    @Autowired
    protected ModelMapper modelMapper;

    public DTO entityToDto(Entity entity) {
        return modelMapper.map(entity, getDtoClass());
    }

    public Entity dtoToEntity(DTO dto) {
        return modelMapper.map(dto, getEntityClass());
    }

    protected abstract Class<Entity> getEntityClass();

    protected abstract Class<DTO> getDtoClass();

    public List<DTO> entityListToDtoList(List<Entity> entityList) {
        return entityList.stream()
                .map(this::entityToDto)
                .collect(Collectors.toList());
    }

    public List<Entity> dtoListToEntityList(List<DTO> dtoList) {
        return dtoList.stream()
                .map(this::dtoToEntity)
                .collect(Collectors.toList());
    }
}