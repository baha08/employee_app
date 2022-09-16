package com.example.emp.repository;

import com.example.emp.models.Department;
import com.example.emp.models.Employee;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface EmployeeRepository extends MongoRepository<Employee,String> {

    public List<Employee> findEmployeeByDepartmentName(String name);
}
