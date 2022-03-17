package com.example.soliqjwttask.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ProductDto {

    private String name;
    private double price;
    private boolean active;
    private String info;

}
