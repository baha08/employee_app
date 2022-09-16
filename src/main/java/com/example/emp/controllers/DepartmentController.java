package com.example.emp.controllers;

import com.example.emp.DTOs.DepartmentDto;
import com.example.emp.mappers.DepartmentMapper;
import com.example.emp.models.Department;
import com.example.emp.services.DepartmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/dept")
@RequiredArgsConstructor
@CrossOrigin("http://localhost:4200")
public class DepartmentController {
    private final DepartmentService departmentService;

    @PostMapping
    public ResponseEntity<DepartmentDto> createDepartment(@RequestBody final DepartmentDto departmentDto) {
        final Department department = DepartmentMapper.MAPPER.dtoToDepartment(departmentDto);
        return new ResponseEntity<>(DepartmentMapper.MAPPER.departmentToDto(departmentService.createDepartment(department)), HttpStatus.CREATED);
    }

    @GetMapping("{id}")
    public ResponseEntity<DepartmentDto> getDepartmentById(@PathVariable final String id) {
        return new ResponseEntity<>(DepartmentMapper.MAPPER.departmentToDto(departmentService.getDepartmentById(id)), HttpStatus.OK);
    }
    @GetMapping
    public List<DepartmentDto> getAllDepartments() {
        return departmentService.getAllDepartments().stream().map(department -> DepartmentMapper.MAPPER.departmentToDto(department)).collect(Collectors.toList());
    }

    @PutMapping("{id}")
    public ResponseEntity<DepartmentDto> updateDepartment(@PathVariable String id, @RequestBody DepartmentDto departmentDto){
        Department department = DepartmentMapper.MAPPER.dtoToDepartment(departmentDto);
        return new ResponseEntity<>(DepartmentMapper.MAPPER.departmentToDto(departmentService.updateDepartment(id,department)),HttpStatus.ACCEPTED );
    }
}