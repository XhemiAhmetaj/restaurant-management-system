package com.ikubinfo.project.restaurantapp.controller;

import com.ikubinfo.project.restaurantapp.domain.dto.ProductDTO;
import com.ikubinfo.project.restaurantapp.domain.dto.update.ProductUpdatedDTO;
import com.ikubinfo.project.restaurantapp.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    @PostMapping("/add")
    public ResponseEntity<ProductDTO> addProduct(@RequestBody ProductDTO dto){
        return ResponseEntity.ok(productService.addProduct(dto));
    }

    @GetMapping
    public ResponseEntity<List<ProductDTO>> listProducts(){
        return ResponseEntity.ok(productService.listAllProducts());
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductDTO> updateProduct(@RequestBody ProductUpdatedDTO dto, @PathVariable Long id){
        return ResponseEntity.ok(productService.updateProduct(id, dto));
    }


}
