package com.ikubinfo.project.restaurantapp.domain.mapper;

import com.ikubinfo.project.restaurantapp.domain.dto.ProductDTO;
import com.ikubinfo.project.restaurantapp.domain.dto.update.ProductUpdatedDTO;
import com.ikubinfo.project.restaurantapp.domain.entity.Drink;
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
                .productWeight(p.getProductWeight())
                .productQuantity(p.getProductQuantity())
                .build();
    }

    public static Product toEntity(ProductDTO dto){
        return Product.builder()
                .name(dto.getName())
                .measurement(Measurement.valueOf(dto.getMeasurement()))
                .productWeight(dto.getProductWeight())
                .productQuantity(dto.getProductQuantity())
                .build();
    }

    public static Product updateProduct(Product product, ProductUpdatedDTO updatedDto){
        product.setProductWeight(updatedDto.getProductWeight());
        product.setProductQuantity(updatedDto.getProductQuantity());
        return product;
    }

    public static ProductUpdatedDTO toUpdateDto(Product p){
        return ProductUpdatedDTO.builder()
                .id(p.getId())
                .productWeight(p.getProductWeight())
                .productQuantity(p.getProductQuantity())
                .build();
    }

    public static Product buildProduct(Drink drink){
        Product product = new Product();
        product.setName(drink.getName());
        product.setMeasurement(Measurement.MILILITER);
        product.setProductWeight(0D);
        product.setProductQuantity(0);
        return product;
    }

}
