package com.example.soliqjwttask.controller;

import com.example.soliqjwttask.dto.ApiResponse;
import com.example.soliqjwttask.dto.OrderDto;
import com.example.soliqjwttask.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/order")
public class OrderController {

    @Autowired
    OrderService orderService;

    @PreAuthorize("hasAnyAuthority('ADMIN','USER')")
    @GetMapping
    public HttpEntity<?> getAll(){
        ApiResponse all=orderService.getAll();
        return ResponseEntity.ok().body(all);
    }

    @PreAuthorize("hasAnyAuthority('ADMIN','USER')")
    @GetMapping("/{id}")
    public HttpEntity<?> getOneId(@PathVariable Integer id){
        ApiResponse apiResponse=orderService.getOneId(id);
        return ResponseEntity.status(apiResponse.isSuccess()? HttpStatus.OK:HttpStatus.NO_CONTENT).body(apiResponse);
    }

    @PreAuthorize("hasAuthority('USER')")
    @PostMapping
    public HttpEntity<?> add(@Valid @RequestBody OrderDto orderDto){
        ApiResponse add=orderService.add(orderDto);
        return ResponseEntity.status(add.isSuccess()?HttpStatus.OK:HttpStatus.NO_CONTENT).body(add);
    }

    @PreAuthorize("hasAuthority('USER')")
    @PutMapping("/{id}")
    public HttpEntity<?> edit(@PathVariable Integer id , @Valid @RequestBody OrderDto orderDto){
        ApiResponse edit=orderService.edit(id , orderDto);
        return ResponseEntity.status(edit.isSuccess()?HttpStatus.OK:HttpStatus.NO_CONTENT).body(edit);
    }

    @PreAuthorize("hasAuthority('USER')")
    @DeleteMapping("/{id}")
    public HttpEntity<?> delet(@PathVariable Integer id){
        ApiResponse delet=orderService.delet(id);
        return ResponseEntity.status(delet.isSuccess()?HttpStatus.OK:HttpStatus.NO_CONTENT).body(delet);
    }
}
