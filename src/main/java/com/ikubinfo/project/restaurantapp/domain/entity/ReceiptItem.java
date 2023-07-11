package com.ikubinfo.project.restaurantapp.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "receipt_item")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class ReceiptItem {

    @Id
    @GeneratedValue
    private Long id;
    private String dishName;
    private Double dishPrice;

    @ManyToOne
    @JoinColumn(name = "receipt_id", referencedColumnName = "id")
    private Receipt receipt;
}
