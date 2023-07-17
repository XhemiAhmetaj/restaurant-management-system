package com.ikubinfo.project.restaurantapp.repository.specification;

import lombok.*;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class SearchCriteria {
    private String key;
    private Object value;
    private String operation;

}
