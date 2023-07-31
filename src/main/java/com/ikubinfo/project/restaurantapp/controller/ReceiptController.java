package com.ikubinfo.project.restaurantapp.controller;

import com.ikubinfo.project.restaurantapp.domain.dto.ReceiptDTO;
import com.ikubinfo.project.restaurantapp.service.ReceiptService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.security.RolesAllowed;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/receipt")
public class ReceiptController {

    private final ReceiptService receiptService;

    @GetMapping("/{receiptId}")
    public ResponseEntity<ReceiptDTO> getReceipt(@PathVariable Long receiptId){
        return ResponseEntity.ok(receiptService.getReceipt(receiptId));
    }

    @RolesAllowed({"ADMIN", "RECEPTIONIST"})
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<ReceiptDTO>> getAllUserReceipts(@PathVariable Long userId){
        return ResponseEntity.ok(receiptService.getAllReceiptFromUser(userId));
    }
}
