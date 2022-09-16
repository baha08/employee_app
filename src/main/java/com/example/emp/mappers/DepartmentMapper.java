package com.example.emp.mappers;

import com.example.emp.DTOs.DepartmentDto;
import com.example.emp.models.Department;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public abstract class DepartmentMapper {

    public static final DepartmentMapper MAPPER = Mappers.getMapper(DepartmentMapper.class);

    public abstract DepartmentDto departmentToDto(Department department);

    public abstract Department dtoToDepartment(DepartmentDto departmentDto);
}
