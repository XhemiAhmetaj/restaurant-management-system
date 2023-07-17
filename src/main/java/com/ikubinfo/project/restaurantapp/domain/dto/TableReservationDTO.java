package com.ikubinfo.project.restaurantapp.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TableReservationDTO {

    private Long id;
    private LocalDate date;
    private LocalTime startTime;
    private Long tableId;
//    private Long reservationId;

}
