package com.ikubinfo.project.restaurantapp.domain.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductDTO {

    private Long id;
    private String name;
    private String measurement;
    private Integer quantity;
    @JsonIgnore
    private String created_by;
}
