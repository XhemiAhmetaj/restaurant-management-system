package com.ikubinfo.project.restaurantapp.domain.entity;

import com.ikubinfo.project.restaurantapp.domain.Auditable;
import com.ikubinfo.project.restaurantapp.domain.dto.CategoryDTO;
import com.ikubinfo.project.restaurantapp.domain.entity.enums.MenuStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Where;
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
@Where(clause = "deleted = false")
public class Menu extends Auditable<User> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(value = EnumType.STRING)
    private MenuStatus status;

    private boolean deleted=false;


//    @OneToMany(mappedBy = "menu", cascade = CascadeType.ALL)
//    private List<Dish> dishes;

    @OneToMany(mappedBy = "menu", cascade = CascadeType.ALL)
    private List<MenuItem> items;


}
