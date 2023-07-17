package com.ikubinfo.project.restaurantapp.service.impl;

import com.ikubinfo.project.restaurantapp.domain.dto.ReceiptDTO;
import com.ikubinfo.project.restaurantapp.domain.entity.Receipt;
import com.ikubinfo.project.restaurantapp.domain.exception.ResourceNotFoundException;
import com.ikubinfo.project.restaurantapp.domain.mapper.ReceiptMapper;
import com.ikubinfo.project.restaurantapp.repository.ReceiptRepository;
import com.ikubinfo.project.restaurantapp.service.ReceiptService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static com.ikubinfo.project.restaurantapp.domain.exception.ExceptionConstants.RECEIPT_NOT_FOUND;
import static java.lang.String.format;

@Service
@RequiredArgsConstructor
public class ReceiptServiceImpl implements ReceiptService {

    private final ReceiptRepository repository;
    @Override
    public ReceiptDTO getReceipt(Long id) {
        Receipt receipt= repository.findById(id).orElseThrow(()-> new ResourceNotFoundException(format(RECEIPT_NOT_FOUND,id)));
        return ReceiptMapper.toDto(receipt);
    }

    @Override
    public List<ReceiptDTO> getAllReceiptFromUser(Long userId) {
        return repository.findReceiptsByOrder_UserId(userId).stream().map(ReceiptMapper::toDto).collect(Collectors.toList());
    }
}
