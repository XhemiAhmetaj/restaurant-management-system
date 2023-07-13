package com.ikubinfo.project.restaurantapp.service;

import com.ikubinfo.project.restaurantapp.domain.dto.ReceiptDTO;

import java.util.List;

public interface ReceiptService {

    ReceiptDTO getReceipt(Long id);
    List<ReceiptDTO> getAllReceiptFromUser(Long userId);
}
