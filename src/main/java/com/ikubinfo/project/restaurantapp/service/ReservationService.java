package com.ikubinfo.project.restaurantapp.service;

import com.ikubinfo.project.restaurantapp.domain.dto.ReservationDTO;
import com.ikubinfo.project.restaurantapp.domain.dto.RestaurantTableDTO;

import java.util.List;

public interface ReservationService {

    ReservationDTO reserveTable(ReservationDTO reservationDTO);
    List<ReservationDTO> showAllReservations();
    ReservationDTO findReservationById(Long reservationId);
    List<ReservationDTO> findReservationByUserId(Long userId);
}
