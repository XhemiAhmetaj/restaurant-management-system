package com.ikubinfo.project.restaurantapp.domain.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderDTO {
    private Long id;
    private List<OrderItemDTO> orderItems;
    private Double totalAmount;
    private String orderStatus;
    private Long userId;
    private Long tableId;
    @JsonIgnore
    private Long receiptId;
}
