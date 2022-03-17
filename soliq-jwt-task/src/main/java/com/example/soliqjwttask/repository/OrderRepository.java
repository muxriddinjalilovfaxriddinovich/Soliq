package com.example.soliqjwttask.repository;

import com.example.soliqjwttask.entity.Order;
import com.example.soliqjwttask.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order,Integer> {
}
