package com.example.emp.mappers;

import com.example.emp.DTOs.EmployeeDto;
import com.example.emp.models.Employee;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public abstract class EmployeeMapper {

    public static final EmployeeMapper MAPPER = Mappers.getMapper(EmployeeMapper.class);

    public abstract EmployeeDto employeeToDto(Employee employee);

    public abstract Employee dtoToEmployee(EmployeeDto employeeDto);

}