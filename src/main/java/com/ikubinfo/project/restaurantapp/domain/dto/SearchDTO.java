package com.ikubinfo.project.restaurantapp.domain.dto;

import com.ikubinfo.project.restaurantapp.repository.specification.SearchCriteria;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Sort;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SearchDTO {
    private List<SearchCriteria> searchCriteriaList;
    private int pageNumber = 0;
    private int pageSize = 10;
    private Sort.Direction sortDirection = Sort.Direction.ASC;

}
