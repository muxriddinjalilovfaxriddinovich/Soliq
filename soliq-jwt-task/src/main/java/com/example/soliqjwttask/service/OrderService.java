package com.example.soliqjwttask.service;

import com.example.soliqjwttask.dto.ApiResponse;
import com.example.soliqjwttask.dto.OrderDto;
import com.example.soliqjwttask.dto.OrderProductDto;
import com.example.soliqjwttask.entity.Order;
import com.example.soliqjwttask.entity.OrderProduct;
import com.example.soliqjwttask.entity.Product;
import com.example.soliqjwttask.entity.User;
import com.example.soliqjwttask.repository.OrderProductRepository;
import com.example.soliqjwttask.repository.OrderRepository;
import com.example.soliqjwttask.repository.ProductRepository;
import com.example.soliqjwttask.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

@Service
public class OrderService {


    @Autowired
    OrderRepository orderRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    OrderProductRepository orderProductRepository;

    @Autowired
    ProductRepository productRepository;

    public ApiResponse getAll() {
        List<Order> all = orderRepository.findAll();
        return new ApiResponse("Mana", true, all);
    }


    public ApiResponse getOneId(Integer id) {

        Optional<Order> byId = orderRepository.findById(id);
        if (!byId.isPresent()) {
            return new ApiResponse("Bunday id li order mavjud emas", false);
        }
        return new ApiResponse("Mana", true, byId.get());
    }


    public ApiResponse add(OrderDto orderDto) {
        Optional<User> byId = userRepository.findById(orderDto.getUserId());
        if (!byId.isPresent()) {
            return new ApiResponse("Bunday id li foydalanuvchi mavjud emas", false);
        }
        Order order = new Order();
        order.setUser(byId.get());
        Order order1 = orderRepository.save(order);

        List<OrderProduct> orderProducts = new ArrayList<>();
        for (OrderProductDto orderProductDto : orderDto.getOrderProductDtos()) {
            OrderProduct orderProduct = new OrderProduct();

            Optional<Product> productOptional = productRepository.findById(orderProductDto.getProductId());
            orderProduct.setOrder(order1);
            orderProduct.setProduct(productOptional.get());
            orderProduct.setAmount(orderProductDto.getAmount());
            OrderProduct save = orderProductRepository.save(orderProduct);
            orderProducts.add(save);
        }

        order.setOrderProducts(orderProducts);
        Order save = orderRepository.save(order);
        return new ApiResponse("Qo'shildi", true, save);
    }


    public ApiResponse edit(Integer id, OrderDto orderDto) {
        Optional<Order> byId = orderRepository.findById(id);
        if (!byId.isPresent()) {
            return new ApiResponse("Bunday id li order mavjud emas", false);
        }
        Order order = byId.get();
       order.setOrderProducts(byId.get().getOrderProducts());
       order.setUser(byId.get().getUser());
        Order save = orderRepository.save(order);
        return new ApiResponse("Mana",true,save);
    }


    public ApiResponse delet(Integer id) {
        Optional<Order> byId = orderRepository.findById(id);
        if (!byId.isPresent()) {
            return new ApiResponse("Bunday id li product mavjud emas", false);
        }
        orderRepository.deleteById(id);
        return new ApiResponse("O'chirildi", true);
    }
}
