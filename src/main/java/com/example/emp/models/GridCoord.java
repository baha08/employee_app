package com.example.emp.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Document
public class GridCoord {
    @Id
    private String id;
    private int width;
    private int xaxis;
    private int yaxis;
    private int height;

}
