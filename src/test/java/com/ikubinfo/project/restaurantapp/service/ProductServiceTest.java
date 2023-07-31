package com.ikubinfo.project.restaurantapp.service;

import com.ikubinfo.project.restaurantapp.domain.dto.DrinkDTO;
import com.ikubinfo.project.restaurantapp.domain.dto.ProductDTO;
import com.ikubinfo.project.restaurantapp.domain.dto.update.DishUpdatedDTO;
import com.ikubinfo.project.restaurantapp.domain.dto.update.ProductUpdatedDTO;
import com.ikubinfo.project.restaurantapp.domain.entity.Category;
import com.ikubinfo.project.restaurantapp.domain.entity.Dish;
import com.ikubinfo.project.restaurantapp.domain.entity.Drink;
import com.ikubinfo.project.restaurantapp.domain.entity.Product;
import com.ikubinfo.project.restaurantapp.domain.entity.enums.Measurement;
import com.ikubinfo.project.restaurantapp.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
public class ProductServiceTest {

    @MockBean
    private ProductRepository productRepository;

    @Autowired
    private ProductService productService;

    @Test
    public void ProductService_createProduct_ReturnProduct(){
        Product product = new Product();
        product.setName("Product");
        product.setMeasurement(Measurement.MILILITER);
        product.setProductQuantity(null);
        product.setProductWeight(1500.0);

        ProductDTO productDTO = ProductDTO.builder()
                .name("Product")
                .measurement("MILILITER")
                .productQuantity(null)
                .productWeight(1500.0)
                .build();

        when(productRepository.save(any(Product.class))).thenReturn(product);

        ProductDTO savedProduct = productService.addProduct(productDTO);
        assertNotNull(savedProduct);
    }

    @Test
    public void ProductService_GetAllProducts_ReturnProducts(){
        List<Product> products = new ArrayList<>();
        when(productRepository.findAll()).thenReturn(products);
        List<ProductDTO> out = productService.listAllProducts();
        assertNotNull(out);
    }

    @Test
    public void ProductService_GetProductById_ReturnProduct(){
        Product product = new Product();
        product.setName("Product");
        product.setMeasurement(Measurement.MILILITER);
        product.setProductQuantity(null);
        product.setProductWeight(1500.0);

        when(productRepository.findById(1L)).thenReturn(Optional.of(product));

        ProductDTO savedProduct = productService.findProductById(1L);
        assertNotNull(savedProduct);
    }

    @Test
    public void ProductService_UpdateProduct_ReturnProduct(){
        Product product = new Product();
        product.setName("Product");
        product.setMeasurement(Measurement.MILILITER);
        product.setProductQuantity(null);
        product.setProductWeight(1500.0);

        ProductUpdatedDTO productUpdatedDTO = ProductUpdatedDTO.builder()
                .productQuantity(null)
                .productWeight(1700.0)
                .build();

        when(productRepository.findById(1L)).thenReturn(Optional.of(product));
        when(productRepository.save(any(Product.class))).thenReturn(product);

        ProductDTO savedProduct = productService.updateProduct(1L,productUpdatedDTO);
        assertNotNull(savedProduct);
    }
}
