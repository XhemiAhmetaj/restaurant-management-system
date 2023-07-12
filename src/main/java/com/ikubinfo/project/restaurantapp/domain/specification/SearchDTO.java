package com.ikubinfo.project.restaurantapp.domain.specification;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SearchDTO {
    private List<SearchCriteria> searchCriteriaList;

}
