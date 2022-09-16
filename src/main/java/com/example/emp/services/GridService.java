package com.example.emp.services;

import com.example.emp.exceptions.ResourceNotFoundException;
import com.example.emp.models.Employee;
import com.example.emp.models.GridCoord;
import com.example.emp.repository.EmployeeRepository;
import com.example.emp.repository.GridRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.lang.module.ResolutionException;
import java.util.List;

import static com.example.emp.constants.ErrorConstant.ERROR_MESSAGE;

@Service
@Slf4j
@RequiredArgsConstructor
public class GridService {

    private final GridRepository gridRepository;
    private final EmployeeRepository employeeRepository;

    public GridCoord getGridById(String id){
        return gridRepository.findById(id).orElseThrow(()-> {
            log.error("grid not found wih id {}",id);
            return new ResourceNotFoundException();
        });
    }
    public List<GridCoord> getAllGrids(){
        return gridRepository.findAll();
    }
    public GridCoord createGrid(GridCoord gridCoord){
        return gridRepository.save(gridCoord);
    }
    public List<GridCoord> addGridToEmp(String gridId, String empId){
        Employee employee = employeeRepository.findById(empId).orElseThrow(() -> {
            log.error(ERROR_MESSAGE, empId);
            return new ResourceNotFoundException();
        });
        GridCoord gridCoord = gridRepository.findById(gridId).orElseThrow(()-> {
            log.error("grid not found wih id {}",gridId);
            return new ResourceNotFoundException();
        });
        employee.addGrid(gridCoord);
        employeeRepository.save(employee);
        return employee.getGridCoord();
    }
    public List<GridCoord> saveGrids(List<GridCoord> gridCoords){
       return gridRepository.saveAll(gridCoords);
    }

}
