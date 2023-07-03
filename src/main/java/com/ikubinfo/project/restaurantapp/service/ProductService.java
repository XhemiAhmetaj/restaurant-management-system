package com.ikubinfo.project.restaurantapp.service;

import com.ikubinfo.project.restaurantapp.domain.dto.ProductDTO;
import com.ikubinfo.project.restaurantapp.domain.dto.update.ProductUpdatedDTO;

import java.util.List;

public interface ProductService {

    ProductDTO addProduct(ProductDTO dto);
    List<ProductDTO> listAllProducts();

    ProductDTO updateProduct(Long id, ProductUpdatedDTO dto);
}
