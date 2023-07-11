package com.ikubinfo.project.restaurantapp.domain.mapper;

import com.ikubinfo.project.restaurantapp.domain.dto.ReceiptDTO;
import com.ikubinfo.project.restaurantapp.domain.dto.ReceiptItemDTO;
import com.ikubinfo.project.restaurantapp.domain.entity.Receipt;
import com.ikubinfo.project.restaurantapp.domain.entity.ReceiptItem;

import java.util.stream.Collectors;

public class ReceiptMapper {

    public static ReceiptDTO toDto(Receipt receipt){
        return ReceiptDTO.builder()
                .restaurantAddress(receipt.getRestaurant().getAddress())
                .restaurantName(receipt.getRestaurant().getName())
                .restaurantNipt(receipt.getRestaurant().getNipt())
                .items(receipt.getReceiptItems().stream().map(ReceiptMapper::toDto).collect(Collectors.toList()))
                .total(receipt.getTotal())
                .build();
    }

    public static ReceiptItemDTO toDto(ReceiptItem receiptItem){
        return ReceiptItemDTO.builder()
                .id(receiptItem.getId())
                .dishName(receiptItem.getDishName())
                .dishPrice(receiptItem.getDishPrice())
                .build();
    }



}
