package com.ikubinfo.project.restaurantapp.domain.entity;

import com.ikubinfo.project.restaurantapp.domain.entity.enums.Measurement;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "dish_ingredients")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class DishIngredient {

    @Id
    @GeneratedValue
    private Long id;
    @Enumerated(EnumType.STRING)
    private Measurement measurement;
    private Double measure;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "dish_id", referencedColumnName = "id")
    private Dish dish;
}
