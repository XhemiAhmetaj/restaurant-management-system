package com.ikubinfo.project.restaurantapp.service;

import com.ikubinfo.project.restaurantapp.domain.dto.PageParameterDTO;
import com.ikubinfo.project.restaurantapp.domain.dto.ProductDTO;
import com.ikubinfo.project.restaurantapp.domain.dto.UserDTO;
import com.ikubinfo.project.restaurantapp.domain.dto.update.ProductUpdatedDTO;
import com.ikubinfo.project.restaurantapp.repository.specification.SearchCriteria;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ProductService {

    ProductDTO addProduct(ProductDTO dto);
    List<ProductDTO> listAllProducts();

    ProductDTO updateProduct(Long id, ProductUpdatedDTO dto);

    void findProductsWithLessQuantity();

    Page<ProductDTO> filterProducts(List<SearchCriteria> searchCriteria, PageParameterDTO pageDTO);

}
