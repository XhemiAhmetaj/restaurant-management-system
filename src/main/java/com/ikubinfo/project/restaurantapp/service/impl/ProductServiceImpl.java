package com.ikubinfo.project.restaurantapp.service.impl;

import com.ikubinfo.project.restaurantapp.domain.dto.ProductDTO;
import com.ikubinfo.project.restaurantapp.domain.dto.update.ProductUpdatedDTO;
import com.ikubinfo.project.restaurantapp.domain.entity.Product;
import com.ikubinfo.project.restaurantapp.domain.exception.ResourceNotFoundException;
import com.ikubinfo.project.restaurantapp.domain.mapper.DishMapper;
import com.ikubinfo.project.restaurantapp.domain.mapper.ProductMapper;
import com.ikubinfo.project.restaurantapp.repository.ProductRepository;
import com.ikubinfo.project.restaurantapp.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final UserServiceImpl userService;
    @Override
    public ProductDTO addProduct(ProductDTO dto) {
        return ProductMapper.toDto(productRepository.save(ProductMapper.toEntity(dto, userService.getUserFromToken((Jwt) SecurityContextHolder.getContext().getAuthentication().getPrincipal()))));
    }

//    @Override
//    public ProductDTO addProduct(ProductDTO dto) {
//        Product product = ProductMapper.toEntity(dto);
//        product = productRepository.save(product);
//        return ProductMapper.toDto(product);
//    }

    @Override
    public List<ProductDTO> listAllProducts() {
        return productRepository.findAll()
                .stream()
                .map(ProductMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public ProductDTO updateProduct(Long id, ProductUpdatedDTO dto) {
        Product product = productRepository.findById(id).orElseThrow(
                ()->new ResourceNotFoundException("Product not found"));
        return ProductMapper.toDto(ProductMapper.updateProduct(product,dto));
    }
}
