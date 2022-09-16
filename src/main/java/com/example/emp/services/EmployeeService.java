package com.example.emp.services;

import com.example.emp.exceptions.ResourceNotFoundException;
import com.example.emp.models.Department;
import com.example.emp.models.Employee;
import com.example.emp.models.GridCoord;
import com.example.emp.repository.DepartmentRepository;
import com.example.emp.repository.EmployeeRepository;
import com.example.emp.repository.GridRepository;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import static com.example.emp.constants.ErrorConstant.ERROR_MESSAGE;

@RequiredArgsConstructor
@Service
@Slf4j
public class EmployeeService {
    private final EmployeeRepository employeeRepository;
    private final DepartmentRepository departmentRepository;
    private final DepartmentService departmentService;
    private final GridService gridService;
    private final GridRepository gridRepository;

    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    public Employee getEmployeeById(final String id) {
        return employeeRepository.findById(id).orElseThrow(() -> {
            log.error(ERROR_MESSAGE, id);
            return new ResourceNotFoundException();
        });

    }
    public Employee createEmployee(final Employee employee) {


            if ((departmentRepository.findById(employee.getDepartment().getId()).isPresent())  && (employee.getDepartment().getName() == null)   )
        {
            Department department = departmentService.getDepartmentById(employee.getDepartment().getId());
            employee.setDepartment(department);
            return employeeRepository .save(employee);


        }
            if (departmentRepository.findDepartmentByName(employee.getDepartment().getName()) != null  && employee.getDepartment().getId() == null){
                Department department = departmentRepository.findDepartmentByName(employee.getDepartment().getName());
                employee.setDepartment(department);
                employee.getDepartment().setId(department.getId());
                return employeeRepository .save(employee);
                }



            if (employee.getDepartment().getId() != null && employee.getDepartment().getName() != null )
            {departmentRepository.save(employee.getDepartment());
                return employeeRepository .save(employee);}
            else
                return employee;



        }




    public Employee updateEmployee(final String id, final Employee employeeDetails) {
        final Employee employee = employeeRepository.findById(id).orElseThrow(() -> {
            log.error(ERROR_MESSAGE, id);
            return new ResourceNotFoundException();
        });
        Department department = departmentService.getDepartmentById(employeeDetails.getDepartment().getId());
        employee.setFirstName(employeeDetails.getFirstName());
        employee.setLastName(employeeDetails.getLastName());
        employee.setEmail(employeeDetails.getEmail());
        employee.setDepartment(department);
        employee.setGridCoord(employee.getGridCoord());
        return employeeRepository.save(employee);

    }
    public void deleteEmployee(final String id) {
        final Employee employee = employeeRepository.findById(id).orElseThrow(() -> {
            log.error(ERROR_MESSAGE, id);
            return new ResourceNotFoundException();
        });
        employeeRepository.delete(employee);
    }
    public List<Employee> findEmployeeByDepartmentName(final String name) {
    return employeeRepository.findEmployeeByDepartmentName(name);
    }
    public Employee addEmployeeToDepartment(String empId, String deptId){
        final Employee employee = employeeRepository.findById(empId).orElseThrow(() -> {
            log.error(ERROR_MESSAGE, empId);
            return new ResourceNotFoundException();
        });
        final Department department = departmentRepository.findById(deptId).orElseThrow(() -> {
            log.error(ERROR_MESSAGE, deptId);
            return new ResourceNotFoundException();
        });
        employee.setDepartment(department);
        return employeeRepository.save(employee);
    }
    public List<GridCoord> findGridByEmployeeId(String id){
        final Employee employee = employeeRepository.findById(id).orElseThrow(() -> {
            log.error(ERROR_MESSAGE, id);
            return new ResourceNotFoundException();
        });
        return employee.getGridCoord();
    }
    public Employee updateGrids(String id,List<GridCoord> gridCoord){
        final Employee employee = employeeRepository.findById(id).orElseThrow(() -> {
            log.error(ERROR_MESSAGE, id);
            return new ResourceNotFoundException();
        });
        for (GridCoord grid: gridCoord){
            if(gridRepository.findById(grid.getId()).isPresent())
                continue;
            else
                gridRepository.save(grid);
        }
        employee.setGridCoord(gridCoord);



        return employeeRepository.save(employee);
    }

}
