package com.ikubinfo.project.restaurantapp.domain.dto;

import lombok.*;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.Objects;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PageParameterDTO {
    private Integer pageNumber = 0;
    private Integer pageSize = 10;
    private Sort.Direction sortDirection = Sort.Direction.ASC;
    private String sort = "name";

//    public Pageable getPageable(PageParameterDTO dto){
//        Integer page = Objects.nonNull(dto.getPageNumber()) ? dto.getPageNumber() : this.pageNumber;
//        Integer size = Objects.nonNull(dto.getPageSize()) ? dto.getPageSize() : this.pageSize;
//        Sort.Direction sort = Objects.nonNull(dto.getSortDirection()) ? dto.getSortDirection() : this.sortDirection;
//        String sortBy = Objects.nonNull(dto.getSortByColumn()) ? dto.getSortByColumn() : this.sortByColumn;
//
//        PageRequest request = PageRequest.of(page,size,sort,sortBy);
//
//        return request;
//
//    }

}
