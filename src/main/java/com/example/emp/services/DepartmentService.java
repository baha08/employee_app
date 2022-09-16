package com.example.emp.services;

import com.example.emp.exceptions.ResourceNotFoundException;
import com.example.emp.models.Department;
import com.example.emp.models.Employee;
import com.example.emp.repository.DepartmentRepository;
import com.example.emp.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import static com.example.emp.constants.ErrorConstant.ERROR_MESSAGE;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class DepartmentService {
    private final DepartmentRepository departmentRepository;
    private final EmployeeRepository employeeRepository;

    public Department getDepartmentById(final String id) {
        return departmentRepository.findById(id).orElseThrow(() -> {
            log.error(ERROR_MESSAGE, id);
            return new ResourceNotFoundException();
        });

    }

    public List<Department> getAllDepartments() {
        return departmentRepository.findAll();
    }
    public Department createDepartment(final Department department) {

        return departmentRepository.save(department);
    }
    public Department updateDepartment(String id, Department departmentDetails){
        Department department = departmentRepository.findById(id).orElseThrow(() -> {
            log.error(ERROR_MESSAGE, id);
            return new ResourceNotFoundException();
        });
        List<Employee> employees = employeeRepository.findEmployeeByDepartmentName(department.getName());
        for (Employee emp : employees){
            emp.getDepartment().setName(departmentDetails.getName());
            employeeRepository.save(emp);
        }

        department.setName(departmentDetails.getName());

        return  departmentRepository.save(department);
    }
}
