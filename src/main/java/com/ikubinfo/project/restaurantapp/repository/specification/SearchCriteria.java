package com.ikubinfo.project.restaurantapp.repository.specification;

import lombok.*;

@NoArgsConstructor
@Getter
@Setter
public class SearchCriteria {
    private String key;
    private Object value;
    private String operation;

    public SearchCriteria(String key, Object value, String operation) {
        this.key = key;
        this.value = value;
        this.operation = operation;
    }
}
