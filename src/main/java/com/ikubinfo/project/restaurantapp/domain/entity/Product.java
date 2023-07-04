package com.ikubinfo.project.restaurantapp.domain.entity;

import com.ikubinfo.project.restaurantapp.domain.Auditable;
import com.ikubinfo.project.restaurantapp.domain.entity.enums.Measurement;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import javax.persistence.*;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table(name = "product")
@AllArgsConstructor
@NoArgsConstructor
@EnableJpaAuditing
@Getter
@Setter
@Builder
public class Product extends Auditable<User> {

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

//    @Getter(AccessLevel.NONE)
//    @Setter(AccessLevel.NONE)
//    @ToString.Exclude
//    @ManyToOne
//    @JoinColumn(name = "createdBy", referencedColumnName = "id")
//    private User userBy;

}
