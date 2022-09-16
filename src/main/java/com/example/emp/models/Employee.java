package com.example.emp.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document

public class Employee {

    @Id
    private String id;
    private String firstName;
    private String lastName;
    @Indexed(unique = true)
    private String email;

    private List<GridCoord> gridCoord = new ArrayList();

    private Department department =  new Department();

    public void addGrid(GridCoord gridCoord){
        this.gridCoord.add(gridCoord);
    }

    public void setGridCoord(List<GridCoord> gridCoord) {
        this.gridCoord = gridCoord;
    }
}