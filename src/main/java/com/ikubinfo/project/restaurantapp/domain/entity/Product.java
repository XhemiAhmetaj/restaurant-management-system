package com.ikubinfo.project.restaurantapp.domain.entity;

import com.ikubinfo.project.restaurantapp.domain.entity.enums.Measurement;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table(name = "product")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Product {

    @Id
    @GeneratedValue
    private Long id;
    private String name;
    @Enumerated(EnumType.STRING)
    private Measurement measurement;
    private Integer quantity;
    @CreatedDate
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;
    @ToString.Exclude
    @ManyToOne
    @JoinColumn(name = "created_by", referencedColumnName = "id")
    private User created_by;

}
