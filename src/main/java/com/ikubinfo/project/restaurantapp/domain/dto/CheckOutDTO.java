package com.ikubinfo.project.restaurantapp.domain.dto;

import com.ikubinfo.project.restaurantapp.domain.entity.Payment;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CheckOutDTO {

    private Long id;
    @NotNull(message = "PaymentMethod is required!")
    @NotEmpty
    private String paymentMethod;

}
