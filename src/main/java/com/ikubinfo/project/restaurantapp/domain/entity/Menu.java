package com.ikubinfo.project.restaurantapp.domain.entity;

import com.ikubinfo.project.restaurantapp.domain.Auditable;
import com.ikubinfo.project.restaurantapp.domain.dto.CategoryDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.List;
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "menu")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Menu extends Auditable<User> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

//    @OneToMany(mappedBy = "menu", cascade = CascadeType.ALL)
//    private List<Category> categories;

//    @OneToMany(mappedBy = "menu", cascade = CascadeType.ALL)
//    private List<Dish> dishes;
}
