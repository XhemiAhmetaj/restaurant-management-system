package com.ikubinfo.project.restaurantapp.repository.specification;

import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Date;
import java.util.List;


public class GenereticSpecification<T> implements Specification<T> {
    private SearchCriteria criteria;
//    public  SearchCriteria getSearchCriteria();


    @Override
    public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        if (criteria.getOperation().equalsIgnoreCase(">=")) {
            return criteriaBuilder.greaterThanOrEqualTo(
                    root.<String> get(criteria.getKey()), criteria.getValue().toString());
        }
        else if (criteria.getOperation().equalsIgnoreCase("<=")) {
            return criteriaBuilder.lessThanOrEqualTo(
                    root.<String> get(criteria.getKey()), criteria.getValue().toString());
        }
        else if (criteria.getOperation().equalsIgnoreCase(">")) {
            return criteriaBuilder.greaterThan(
                    root.<String> get(criteria.getKey()), criteria.getValue().toString());
        }
        else if (criteria.getOperation().equalsIgnoreCase("<")) {
            return criteriaBuilder.lessThan(
                    root.<String> get(criteria.getKey()), criteria.getValue().toString());
        }
        else if (criteria.getOperation().equalsIgnoreCase(":")) {
            if (root.get(criteria.getKey()).getJavaType() == String.class) {
                return criteriaBuilder.like(
                        root.<String>get(criteria.getKey()), "%" + criteria.getValue() + "%");
            } else {
                return criteriaBuilder.equal(root.get(criteria.getKey()), criteria.getValue());
            }

        }
        else if (criteria.getOperation().equalsIgnoreCase("date-between")) {
            List<String> dates = (List<String>) criteria.getValue();
            Instant startInstant = LocalDateTime.parse(dates.get(0)).toInstant(ZoneOffset.UTC);
            var startDate = Date.from(startInstant);
            Instant endInstant =  LocalDateTime.parse(dates.get(1)).toInstant(ZoneOffset.UTC);
            var endDate = Date.from(endInstant);
            return criteriaBuilder.between(root.get(criteria.getKey()), startDate, endDate);
        }
        return null;
    }

}
