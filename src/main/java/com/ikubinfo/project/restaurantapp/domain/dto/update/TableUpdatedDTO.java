package com.ikubinfo.project.restaurantapp.domain.dto.update;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TableUpdatedDTO {

    private Long id;
    private Integer capacity;
    private String description;
    private String status;
}
