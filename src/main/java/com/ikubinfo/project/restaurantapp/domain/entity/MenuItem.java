package com.ikubinfo.project.restaurantapp.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "menu_item")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class MenuItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "menu", referencedColumnName = "id")
    private Menu menu;

    @ManyToOne
    @JoinColumn(name = "dish", referencedColumnName = "id")
    private Dish dish;

    @ManyToOne
    @JoinColumn(name = "drink", referencedColumnName = "id")
    private Drink drink;

}
