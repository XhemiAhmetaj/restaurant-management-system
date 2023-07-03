package com.ikubinfo.project.restaurantapp.repository;

import com.ikubinfo.project.restaurantapp.domain.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {

}
