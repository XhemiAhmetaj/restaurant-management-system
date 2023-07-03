package com.ikubinfo.project.restaurantapp.domain.dto.update;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DishUpdatedDTO {

    private String description;
    private double price;
}
