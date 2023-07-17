package com.ikubinfo.project.restaurantapp.repository.specification;

import com.ikubinfo.project.restaurantapp.domain.entity.Order;
import com.ikubinfo.project.restaurantapp.domain.entity.Product;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;

public class OrderSpecification extends GenericSpecification<Order> {

    public OrderSpecification(SearchCriteria searchCriteria) {
        super.criteria = searchCriteria;
    }

    public static Specification<Order> toSpecification(List<SearchCriteria> filters) {

        Specification<Order> specification = null;

        for (SearchCriteria filter : filters) {
            if (specification == null){
                specification = new OrderSpecification(filter);
            } else {
                specification = specification.and(new OrderSpecification(filter));
            }
        }

        return specification;
    }
}
