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
    @NotNull
    private String name;
    @NotNull
    private String measurement;
    @NotNull
    private Double productWeight;
    @NotNull
    private Integer productQuantity;

}
