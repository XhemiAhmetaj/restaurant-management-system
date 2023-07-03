package com.ikubinfo.project.restaurantapp.domain.entity;

import com.ikubinfo.project.restaurantapp.domain.entity.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "restaurant")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Restaurant {

    @Id
    private Long id;
    private String name;
    private String nipt;
    private String address;

}
