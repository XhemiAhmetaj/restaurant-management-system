package com.ikubinfo.project.restaurantapp.domain.entity;

import com.ikubinfo.project.restaurantapp.domain.entity.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "tables")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class RestaurantTable {
    @Id
    @GeneratedValue
    private Long id;

    private Integer tableId;
    private Integer capacity;
    @Enumerated(EnumType.STRING)
    private Status status;
    @CreatedDate
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

}
