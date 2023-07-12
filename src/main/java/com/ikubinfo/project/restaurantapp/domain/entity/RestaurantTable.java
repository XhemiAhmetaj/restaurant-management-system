package com.ikubinfo.project.restaurantapp.domain.entity;

import com.ikubinfo.project.restaurantapp.domain.Auditable;
import com.ikubinfo.project.restaurantapp.domain.entity.enums.TableStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "tables")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class RestaurantTable extends Auditable<User> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer tableId;
    private Integer capacity;
    private String description;
    @Enumerated(EnumType.STRING)
    private TableStatus tableStatus;

}
