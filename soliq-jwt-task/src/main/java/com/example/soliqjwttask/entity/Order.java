package com.example.soliqjwttask.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;
import org.springframework.boot.autoconfigure.quartz.QuartzDataSource;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Entity(name = "orders")
@AllArgsConstructor
@Data
@NoArgsConstructor
public class Order {
    @Id
    @Type(type = "org.hibernate.type.PostgresUUIDType")
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
    private UUID id;

    @OneToMany(mappedBy = "order",fetch = FetchType.LAZY)
    private List<OrderProduct> orderProducts;

    @ManyToOne
    private User user;
}
