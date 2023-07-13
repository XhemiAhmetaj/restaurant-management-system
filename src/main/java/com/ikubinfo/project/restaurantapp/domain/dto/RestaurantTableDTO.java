package com.ikubinfo.project.restaurantapp.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RestaurantTableDTO {

    private Long id;
    private Long tableId;
    private Integer capacity;
    private String description;
    private String status;
}
