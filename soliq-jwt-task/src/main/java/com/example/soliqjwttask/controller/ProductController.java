package com.example.soliqjwttask.controller;

import com.example.soliqjwttask.dto.ApiResponse;
import com.example.soliqjwttask.dto.ProductDto;
import com.example.soliqjwttask.entity.Product;
import com.example.soliqjwttask.excaption.Not_Found;
import com.example.soliqjwttask.repository.ProductRepository;
import com.example.soliqjwttask.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/product")
public class ProductController {
    @Autowired
    ProductService productService;
    @Autowired
    ProductRepository productRepository;

    @PreAuthorize("hasAnyAuthority('USER','ADMIN')")
    @GetMapping
    public HttpEntity<?> getAll() {

        List<Product> all = productRepository.findAll();
        if (all.isEmpty()) {
            return new HttpEntity<Not_Found>(new Not_Found("List bo'sh"));
        }
        return ResponseEntity.ok().body(all);
//        return ResponseEntity.ok().body(productRepository.findAll());
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/{id}")
    public HttpEntity<?> getByID(@PathVariable String id) {
        for (char c : id.toCharArray()) {
            if (!Character.isDigit(c)) {
                return new HttpEntity<Not_Found>(new Not_Found("Bunday id li product mavjud emas"));
            }
        }
        Optional<Product> productOptional = productRepository.findById(Integer.valueOf(id));
        return ResponseEntity.ok().body(productOptional.get());

    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping
    public HttpEntity<?> add(@Valid @RequestBody ProductDto productDto) {
        ApiResponse add = productService.add(productDto);
        return ResponseEntity.status(add.isSuccess() ? HttpStatus.OK : HttpStatus.NOT_FOUND).body(add);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PutMapping
    public HttpEntity<?> edit(@PathVariable Integer id, @Valid @RequestBody ProductDto productDto) {
        ApiResponse edit = productService.edit(id, productDto);
        return ResponseEntity.status(edit.isSuccess() ? HttpStatus.OK : HttpStatus.NOT_FOUND).body(edit);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping("/{id}")
    public HttpEntity<?> delet(@PathVariable Integer id) {
        ApiResponse delet = productService.delet(id);
        return ResponseEntity.status(delet.isSuccess() ? HttpStatus.OK : HttpStatus.NOT_FOUND).body(delet);
    }
}
