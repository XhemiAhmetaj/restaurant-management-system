package com.ikubinfo.project.restaurantapp.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import javax.persistence.Table;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "dishes")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Dish {

    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String description;
    private Double price;
    @OneToMany(mappedBy = "dish", cascade = CascadeType.ALL)
    private List<DishIngredient> ingredients;
    @ManyToOne
    @JoinColumn(name = "category_id", referencedColumnName = "id")
    private Category category;


    @CreatedDate
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;

}
