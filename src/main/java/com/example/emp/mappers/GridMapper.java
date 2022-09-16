package com.example.emp.mappers;

import com.example.emp.DTOs.GridDto;
import com.example.emp.models.GridCoord;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public abstract class GridMapper {
    public static final GridMapper MAPPER = Mappers.getMapper(GridMapper.class);

    public abstract GridDto gridToDto(GridCoord gridCoord);
    public abstract GridCoord dtoToGrid(GridDto gridDto);
}
