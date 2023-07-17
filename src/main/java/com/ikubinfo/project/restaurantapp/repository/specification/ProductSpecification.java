package com.ikubinfo.project.restaurantapp.repository.specification;

import com.ikubinfo.project.restaurantapp.domain.entity.Product;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;

public class ProductSpecification extends GenericSpecification<Product>{
    public ProductSpecification(SearchCriteria searchCriteria) {
        super.criteria = searchCriteria;
    }

    public static Specification<Product> toSpecification(List<SearchCriteria> filters) {

        Specification<Product> specification = null;

        for (SearchCriteria filter : filters) {
            if (specification == null){
                specification = new ProductSpecification(filter);
            } else {
                specification = specification.and(new ProductSpecification(filter));
            }
        }

        return specification;
    }

}
