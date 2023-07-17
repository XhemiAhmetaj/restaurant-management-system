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
    private String sort = "";


}
