package com.example.soliqjwttask.dto;

import com.example.soliqjwttask.entity.OrderProduct;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class OrderDto {

    private Integer userId;
    private List<OrderProductDto> orderProductDtos;
}
