package com.ikubinfo.project.restaurantapp.domain.mapper;

import com.ikubinfo.project.restaurantapp.domain.dto.ProductDTO;
import com.ikubinfo.project.restaurantapp.domain.dto.update.ProductUpdatedDTO;
import com.ikubinfo.project.restaurantapp.domain.entity.Product;
import com.ikubinfo.project.restaurantapp.domain.entity.User;
import com.ikubinfo.project.restaurantapp.domain.entity.enums.Measurement;

import java.time.LocalDateTime;

public class ProductMapper {

    public static ProductDTO toDto(Product p){
        return ProductDTO.builder()
                .id(p.getId())
                .name(p.getName())
                .measurement(p.getMeasurement().getValue())
                .quantity(p.getQuantity())
//                .created_by(p.getCreated_by().getName())
                .build();
    }

    public static Product toEntity(ProductDTO dto, User user){
        return Product.builder()
                .name(dto.getName())
                .measurement(Measurement.valueOf(dto.getMeasurement()))
                .quantity(dto.getQuantity())
                .createdAt(LocalDateTime.now())
                .created_by(user)
                .build();
    }

    public static Product updateProduct(Product product, ProductUpdatedDTO updatedDto){
        product.setQuantity(updatedDto.getQuantity());
        product.setModifiedAt(LocalDateTime.now());
        return product;
    }

}
