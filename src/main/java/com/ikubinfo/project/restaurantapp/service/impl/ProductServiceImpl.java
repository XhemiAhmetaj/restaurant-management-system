package com.ikubinfo.project.restaurantapp.service.impl;

import com.ikubinfo.project.restaurantapp.domain.dto.PageParameterDTO;
import com.ikubinfo.project.restaurantapp.domain.dto.ProductDTO;
import com.ikubinfo.project.restaurantapp.domain.dto.update.ProductUpdatedDTO;
import com.ikubinfo.project.restaurantapp.domain.entity.Product;
import com.ikubinfo.project.restaurantapp.domain.exception.ResourceNotFoundException;
import com.ikubinfo.project.restaurantapp.domain.mapper.ProductMapper;
import com.ikubinfo.project.restaurantapp.repository.ProductRepository;
import com.ikubinfo.project.restaurantapp.repository.specification.ProductSpecification;
import com.ikubinfo.project.restaurantapp.repository.specification.SearchCriteria;
import com.ikubinfo.project.restaurantapp.service.EmailSenderService;
import com.ikubinfo.project.restaurantapp.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static com.ikubinfo.project.restaurantapp.domain.Constants.ADMIN_EMAIL_ADDRES;
import static com.ikubinfo.project.restaurantapp.domain.Constants.WARNING_EMAIL_SUBJECT;
import static com.ikubinfo.project.restaurantapp.domain.exception.ExceptionConstants.PRODUCT_NOT_FOUND;
import static com.ikubinfo.project.restaurantapp.domain.mapper.ProductMapper.toDto;
import static java.lang.String.format;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final EmailSenderService emailService;

    @Override
    public ProductDTO addProduct(ProductDTO dto) {
        Product product =  productRepository.save(ProductMapper.toEntity(dto));
        return toDto(product);
    }

    @Override
    public List<ProductDTO> listAllProducts() {
        return productRepository.findAll()
                .stream()
                .map(ProductMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public ProductDTO findProductById(Long productId){
        Product product =  productRepository.findById(productId).orElseThrow(()-> new ResourceNotFoundException(format(PRODUCT_NOT_FOUND,productId)));
        return toDto(product);
    }

    @Override
    public ProductDTO updateProduct(Long id, ProductUpdatedDTO dto) {
        Product product = productRepository.findById(id).orElseThrow(
                ()->new ResourceNotFoundException(format(PRODUCT_NOT_FOUND,id)));
        ProductMapper.updateProduct(product, dto);
        return toDto(productRepository.save(product));
    }

    @Scheduled(cron = "0 0 4 1/2 * ?")
    @Override
    public void findProductsWithLessQuantity(){
        List<ProductDTO> productDTOList = productRepository.findProductsByProductWeightIsLessThanEqual(1000.0).stream().map(ProductMapper::toDto).collect(Collectors.toList());
        List<ProductDTO> product2List = productRepository.findProductsByProductQuantityIsLessThanEqual(20).stream().map(ProductMapper::toDto).collect(Collectors.toList());
        String body = "";
        if(!(productDTOList.isEmpty() || product2List.isEmpty())){
            for(ProductDTO product : productDTOList){
               body = body.concat("Product: "+product.getName() + " Weight: "+product.getProductWeight()+ " " + product.getMeasurement() + "\n");
            }

            for(ProductDTO product : product2List){
                if(product.getProductQuantity().equals(0)){
                    continue;
                }
                body = body.concat("Product: "+product.getName() + " Quantity: "+product.getProductQuantity()+"\n");
            }
            emailService.sendEmail(ADMIN_EMAIL_ADDRES, WARNING_EMAIL_SUBJECT, body);
        }
    }

    @Override
    public Page<ProductDTO> filterProducts(List<SearchCriteria> searchCriteria, PageParameterDTO pageDTO){
        Sort sort = Sort.by(pageDTO.getSortDirection(), pageDTO.getSort());
        Pageable pageable = PageRequest.of(pageDTO.getPageNumber(), pageDTO.getPageSize(), sort);
        if (searchCriteria != null && searchCriteria.size() > 0) {
            var productSpec = ProductSpecification.toSpecification(searchCriteria);
            return productRepository.findAll(productSpec, pageable).map(ProductMapper::toDto);
        } else {
            return productRepository.findAll(pageable).map(ProductMapper::toDto);
        }
    }


}
