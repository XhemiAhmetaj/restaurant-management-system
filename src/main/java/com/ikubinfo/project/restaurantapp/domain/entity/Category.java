package com.ikubinfo.project.restaurantapp.domain.entity;

import com.ikubinfo.project.restaurantapp.domain.Auditable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Where;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.persistence.Table;
import java.util.List;
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "dish_category")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Where(clause = "deleted = false")
public class Category extends Auditable<User> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    private boolean deleted=false;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "parent_id",referencedColumnName = "id")
    private Category categoryParent;

    @OneToMany(mappedBy = "categoryParent",cascade = CascadeType.ALL)
    private List<Category> subCategories;

    @OneToMany(mappedBy = "category", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Dish> dishes;

    @OneToMany(mappedBy = "category", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Drink> drinks;

}
