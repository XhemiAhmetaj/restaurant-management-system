package com.ikubinfo.project.restaurantapp.domain.specification;

import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

public abstract class GenereticSpecification<T> implements Specification {
    private List<SearchCriteria> list;

    public GenereticSpecification() {
        this.list = new ArrayList<>();
    }

    public void add(SearchCriteria criteria) {
        list.add(criteria);
    }

    @Override
    public Predicate toPredicate(Root root, CriteriaQuery query, CriteriaBuilder criteriaBuilder) {
        List<Predicate> predicates = new ArrayList<>();
        for (SearchCriteria criteria : list) {
            if(criteria.getOperation().equals(">")){
                predicates.add(criteriaBuilder.greaterThan(root.get(criteria.getFilterKey()), criteria.getValue().toString()));
            } else if (criteria.getOperation().equals("<")) {
                predicates.add(criteriaBuilder.lessThan(root.get(criteria.getFilterKey()), criteria.getValue().toString()));
            } else if (criteria.getOperation().equals(">=")){
                predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get(criteria.getFilterKey()),criteria.getValue().toString()));
            } else if (criteria.getOperation().equals("<=")) {
                predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get(criteria.getFilterKey()), criteria.getValue().toString()));
            } else if (criteria.getOperation().equals("=")){
                predicates.add(criteriaBuilder.equal(root.get(criteria.getFilterKey()), criteria.getValue().toString()));
            }
        }
        return null;
    }

}
