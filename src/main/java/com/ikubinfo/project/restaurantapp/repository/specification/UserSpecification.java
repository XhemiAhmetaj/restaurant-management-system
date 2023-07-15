package com.ikubinfo.project.restaurantapp.repository.specification;

import com.ikubinfo.project.restaurantapp.domain.entity.User;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;

public class UserSpecification extends GenereticSpecification<User> {
    private SearchCriteria searchCriteria;

    public UserSpecification(SearchCriteria searchCriteria) {
        this.searchCriteria = searchCriteria;
    }

    public UserSpecification() {
    }

//    @Override
//    public SearchCriteria getSearchCriteria() {
//        return searchCriteria;
//    }

    public static Specification<User> toSpecification(List<SearchCriteria> filters){
        final Specification<User> specification = new UserSpecification();
        filters.forEach(f-> specification.or(new UserSpecification(f)));
        return specification;
    }

}
