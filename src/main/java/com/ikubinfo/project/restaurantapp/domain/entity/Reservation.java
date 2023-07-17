package com.ikubinfo.project.restaurantapp.domain.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ikubinfo.project.restaurantapp.domain.Auditable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.AuditorAware;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Optional;

@Entity
@Table(name = "reservations")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Reservation extends Auditable<User> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer numberOfPeople;
    private String comment;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate date;
    @JsonFormat(pattern = "HH:mm")
    @DateTimeFormat(pattern = "HH:mm")
    private LocalTime time;

    @ManyToOne
    @JoinColumn(name = "table_id", referencedColumnName = "id")
    private RestaurantTable table;

}
