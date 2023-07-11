package com.ikubinfo.project.restaurantapp.domain.dto;

import com.ikubinfo.project.restaurantapp.domain.entity.Payment;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CheckOutDTO {

    private Long id;
    private String paymentMethod;

}
