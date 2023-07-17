package com.ikubinfo.project.restaurantapp.service;

import com.ikubinfo.project.restaurantapp.domain.dto.PageParameterDTO;
import com.ikubinfo.project.restaurantapp.domain.dto.ReservationDTO;
import com.ikubinfo.project.restaurantapp.domain.dto.RestaurantTableDTO;
import org.springframework.data.domain.Page;

import java.time.LocalDate;
import java.util.List;

public interface ReservationService {

    ReservationDTO reserveTable(ReservationDTO reservationDTO);
    List<ReservationDTO> showAllReservations();
    ReservationDTO findReservationById(Long reservationId);
    List<ReservationDTO> findReservationByUserId(Long userId);
    Page<ReservationDTO> findReservationByTableAndDate(LocalDate date, PageParameterDTO pageDTO);
}
