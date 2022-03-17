package com.example.soliqjwttask.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class OrderProductDto {

    private Integer productId;
    private Integer amount;
}
