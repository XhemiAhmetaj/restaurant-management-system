package com.ikubinfo.project.restaurantapp.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ReceiptDTO {

    private Long id;
    private String restaurantName;
    private String restaurantNipt;
    private String restaurantAddress;
    private List<OrderItemDTO> items;
    private Double total;
    private Integer points;
}
