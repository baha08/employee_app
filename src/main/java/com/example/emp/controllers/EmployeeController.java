package com.example.emp.controllers;

import com.example.emp.DTOs.EmployeeDto;
import com.example.emp.DTOs.GridDto;
import com.example.emp.mappers.EmployeeMapper;
import com.example.emp.mappers.GridMapper;
import com.example.emp.models.Employee;
import com.example.emp.models.GridCoord;
import com.example.emp.services.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/emp")
@RequiredArgsConstructor
@CrossOrigin("http://localhost:4200")
public class EmployeeController {
    private final EmployeeService employeeService;

    @GetMapping

    public List<EmployeeDto> getAllEmployees() {
        return employeeService.getAllEmployees().stream().map(employee -> EmployeeMapper.MAPPER.employeeToDto(employee)).collect(Collectors.toList());
    }

    @GetMapping("{id}")

    public ResponseEntity<EmployeeDto> getEmployeeById(@PathVariable final String id) {
        return new ResponseEntity<>(EmployeeMapper.MAPPER.employeeToDto(employeeService.getEmployeeById(id)), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<EmployeeDto> createEmployee(@RequestBody final EmployeeDto employeeDto) {
        final Employee employee = EmployeeMapper.MAPPER.dtoToEmployee(employeeDto);
        return new ResponseEntity<>(EmployeeMapper.MAPPER.employeeToDto(employeeService.createEmployee(employee)), HttpStatus.CREATED);
    }
    @PutMapping("{id}")
    public ResponseEntity<EmployeeDto> updateEmployee(@PathVariable final String id, @RequestBody final EmployeeDto employeeDto) {
        final Employee employee = EmployeeMapper.MAPPER.dtoToEmployee(employeeDto);
        return new ResponseEntity<>(EmployeeMapper.MAPPER.employeeToDto(employeeService.updateEmployee(id, employee)), HttpStatus.ACCEPTED);
    }
    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteEmployee(@PathVariable final String id) {
        employeeService.deleteEmployee(id);
        return ResponseEntity.noContent().build();
    }
    @GetMapping("/empbydept/{name}")
    public List<EmployeeDto> findEmployeeByDepartmentName(@PathVariable final String name) {
        return employeeService.findEmployeeByDepartmentName(name).stream().map(employee -> EmployeeMapper.MAPPER.employeeToDto(employee)).collect(Collectors.toList());
    }
    @PutMapping("{id_emp}/{id_dept}")
    public ResponseEntity<EmployeeDto> addEmployeeToDepartment(@PathVariable(value = "id_emp") final String empId, @PathVariable(value = "id_dept") final String deptId) {
        return new ResponseEntity<>(EmployeeMapper.MAPPER.employeeToDto(employeeService.addEmployeeToDepartment(empId, deptId)), HttpStatus.ACCEPTED);
    }
    @PutMapping("/update_grid/{id}")
    public ResponseEntity<EmployeeDto> updateGrid(@PathVariable String id, @RequestBody List<GridCoord> grids){
        //return new ResponseEntity<>(EmployeeMapper.MAPPER.employeeToDto(employeeService.updateGrids(id, Arrays.stream(gridDtos).map(gridDto -> GridMapper.MAPPER.dtoToGrid(gridDto)).collect(Collectors.toList()))),HttpStatus.ACCEPTED);
        return new ResponseEntity<>(EmployeeMapper.MAPPER.employeeToDto(employeeService.updateGrids(id,grids)), HttpStatus.ACCEPTED);
    }
}