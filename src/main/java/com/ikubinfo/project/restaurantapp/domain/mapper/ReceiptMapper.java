package com.ikubinfo.project.restaurantapp.domain.mapper;

import com.ikubinfo.project.restaurantapp.domain.dto.ReceiptDTO;
import com.ikubinfo.project.restaurantapp.domain.entity.Receipt;

import java.util.stream.Collectors;

public class ReceiptMapper {

    public static ReceiptDTO toDto(Receipt receipt){
        return ReceiptDTO.builder()
                .restaurantAddress(receipt.getRestaurant().getAddress())
                .restaurantName(receipt.getRestaurant().getName())
                .restaurantNipt(receipt.getRestaurant().getNipt())
                .total(receipt.getTotal())
                .build();
    }




}
