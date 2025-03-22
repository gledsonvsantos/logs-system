package com.example.logs.infrastructure.mapper;

import com.example.logs.domain.Log;
import com.example.logs.infrastructure.repository.LogEntity;
import com.example.logs.infrastructure.dto.LogDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface LogMapper {

    @Mapping(target = "id", source = "id")
    @Mapping(target = "message", source = "message")
    Log toDomain(LogEntity entity);

    @Mapping(target = "id", source = "id")
    @Mapping(target = "message", source = "message")
    LogEntity toEntity(Log log);

    @Mapping(target = "id", source = "id")
    @Mapping(target = "message", source = "message")
    LogDTO toDto(Log log);
}