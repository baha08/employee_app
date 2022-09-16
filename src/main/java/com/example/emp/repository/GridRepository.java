package com.example.emp.repository;

import com.example.emp.models.GridCoord;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface GridRepository extends MongoRepository<GridCoord,String> {

}
