package com.example.soliqjwttask.repository;

import com.example.soliqjwttask.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
    @Modifying
    @Query(value = "delete from productd t where t.name like '%:name%'", nativeQuery = true)
    public int deleteByName(@Param("name") String name);
}
