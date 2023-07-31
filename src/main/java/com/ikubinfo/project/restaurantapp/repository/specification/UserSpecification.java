package com.ikubinfo.project.restaurantapp.repository.specification;

import com.ikubinfo.project.restaurantapp.domain.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;

@Slf4j
public class UserSpecification extends GenericSpecification<User> {


    public UserSpecification(SearchCriteria searchCriteria) {
        super.criteria = searchCriteria;
    }

    public UserSpecification() {
    }

    public static Specification<User> toSpecification(List<SearchCriteria> filters) {
        Specification<User> specification = null;

        for (SearchCriteria filter : filters) {
            if (specification == null){
                specification = new UserSpecification(filter);
            } else {
                specification = specification.and(new UserSpecification(filter));
            }
        }
        return specification;
    }

}
