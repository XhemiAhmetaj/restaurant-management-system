package com.ikubinfo.project.restaurantapp.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;
@Entity
@Table(name = "table_reservations")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class TableReservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate date;
    private LocalTime startTime;

    @ManyToOne
    @JoinColumn(name = "tableId", referencedColumnName = "id")
    private RestaurantTable tableReservation;
//
//    @ManyToOne
//    @JoinColumn(name = "reservationId", referencedColumnName = "id")
//    private Reservation reservation;
}
