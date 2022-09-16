package com.example.emp.controllers;

import com.example.emp.DTOs.GridDto;
import com.example.emp.mappers.GridMapper;
import com.example.emp.models.GridCoord;
import com.example.emp.services.EmployeeService;
import com.example.emp.services.GridService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/grid")
@RequiredArgsConstructor
@CrossOrigin("http://localhost:4200")
public class GridController {
    private  final GridService gridService;
    private final EmployeeService employeeService;

    @PostMapping
    public ResponseEntity<GridDto> createGrid(@RequestBody GridDto gridDto){
        GridCoord gridCoord = GridMapper.MAPPER.dtoToGrid(gridDto);
        return new ResponseEntity<>(GridMapper.MAPPER.gridToDto(gridService.createGrid(gridCoord)), HttpStatus.CREATED );
    }
    @PutMapping("{grid_id}/{emp_id}")
    public List<GridDto> addGridToEmp(@PathVariable(value = "grid_id" )String gridId,@PathVariable(value = "emp_id" )String empId){
    return gridService.addGridToEmp(gridId,empId).stream().map(gridCoord -> GridMapper.MAPPER.gridToDto(gridCoord)).collect(Collectors.toList());
    }
    @GetMapping("{id}")
    public List<GridDto> findGridByEmployeeId(@PathVariable String id){
        return employeeService.findGridByEmployeeId(id).stream().map(gridCoord -> GridMapper.MAPPER.gridToDto(gridCoord)).collect(Collectors.toList());
    }
    @PostMapping("/all")
    public ResponseEntity<List<GridDto>> saveGrids(@RequestBody List<GridDto> gridDtos){
        List<GridCoord> gridCoords = gridDtos.stream().map(gridDto -> GridMapper.MAPPER.dtoToGrid(gridDto)).collect(Collectors.toList());
        return new ResponseEntity<>(gridService.saveGrids(gridCoords).stream().map(gridCoord -> GridMapper.MAPPER.gridToDto(gridCoord)).collect(Collectors.toList()),HttpStatus.CREATED );
    }
}
