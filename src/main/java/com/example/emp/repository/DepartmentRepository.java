package com.example.emp.repository;

import com.example.emp.models.Department;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface DepartmentRepository extends MongoRepository<Department,String> {
    public Department findDepartmentByName(String name);
}
