package com.example.emp.DTOs;

import com.example.emp.models.Department;
import com.example.emp.models.GridCoord;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;

@Getter
@Setter
@NoArgsConstructor
public class EmployeeDto {
    private String id;
    private String firstName;
    private String lastName;
    private String email;
    private ArrayList<GridCoord> gridCoord;
    private Department department;

}