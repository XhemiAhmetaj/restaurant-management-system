package com.ikubinfo.project.restaurantapp.controller;

import com.ikubinfo.project.restaurantapp.domain.dto.PageParameterDTO;
import com.ikubinfo.project.restaurantapp.domain.dto.ProductDTO;
import com.ikubinfo.project.restaurantapp.domain.dto.UserDTO;
import com.ikubinfo.project.restaurantapp.domain.dto.update.ProductUpdatedDTO;
import com.ikubinfo.project.restaurantapp.repository.specification.SearchCriteria;
import com.ikubinfo.project.restaurantapp.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    @RolesAllowed({"ADMIN","COOKER"})
    @PostMapping("/add")
    public ResponseEntity<ProductDTO> addProduct(@RequestBody ProductDTO dto){
        return ResponseEntity.ok(productService.addProduct(dto));
    }
    @RolesAllowed({"ADMIN","COOKER"})
    @GetMapping
    public ResponseEntity<List<ProductDTO>> listProducts(){
        return ResponseEntity.ok(productService.listAllProducts());
    }

    @RolesAllowed({"ADMIN","COOKER"})
    @PutMapping("/{id}")
    public ResponseEntity<ProductDTO> updateProduct(@RequestBody ProductUpdatedDTO dto, @PathVariable Long id){
        return ResponseEntity.ok(productService.updateProduct(id, dto));
    }

    @RolesAllowed({"ADMIN","COOKER"})
    @PostMapping("/list")
    public ResponseEntity<Page<ProductDTO>> filterProducts (@RequestBody List<SearchCriteria> criteria, PageParameterDTO pageParameterDTO) {
        return ResponseEntity.ok(productService.filterProducts(criteria,pageParameterDTO));
    }

}
