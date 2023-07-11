package com.ikubinfo.project.restaurantapp.domain.entity;

import com.ikubinfo.project.restaurantapp.domain.Auditable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.persistence.Table;
import java.util.List;

@Entity
@Table(name = "dishes")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Dish extends Auditable<User> {

    @Id
    @GeneratedValue()
    private Long id;
    private String name;
    private String description;
    private Double price;
    @OneToMany(mappedBy = "dish", cascade = CascadeType.ALL)
    private List<DishIngredient> ingredients;
    @ManyToOne
    @JoinColumn(name = "category_id", referencedColumnName = "id")
    private Category category;

    @ManyToOne
    @JoinColumn(name = "menu_id", referencedColumnName = "id")
    private Menu menu;

}
