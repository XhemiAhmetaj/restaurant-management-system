package com.ikubinfo.project.restaurantapp.service.impl;

import com.ikubinfo.project.restaurantapp.domain.dto.ReservationDTO;
import com.ikubinfo.project.restaurantapp.domain.entity.Reservation;
import com.ikubinfo.project.restaurantapp.domain.entity.RestaurantTable;
import com.ikubinfo.project.restaurantapp.domain.entity.enums.TableStatus;
import com.ikubinfo.project.restaurantapp.domain.exception.BadRequestException;
import com.ikubinfo.project.restaurantapp.domain.exception.ResourceNotFoundException;
import com.ikubinfo.project.restaurantapp.domain.mapper.RestaurantMapper;
import com.ikubinfo.project.restaurantapp.repository.ReservationRepository;
import com.ikubinfo.project.restaurantapp.repository.RestaurantTableRepository;
import com.ikubinfo.project.restaurantapp.service.ReservationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static com.ikubinfo.project.restaurantapp.domain.exception.ExceptionConstants.*;
import static com.ikubinfo.project.restaurantapp.domain.mapper.RestaurantMapper.toDto;
import static java.lang.String.format;

@Service
@RequiredArgsConstructor
@Slf4j
public class ReservationServiceImpl implements ReservationService {

    private final ReservationRepository reservationRepository;
    private final RestaurantTableRepository tableRepository;
    @Override
    public ReservationDTO reserveTable(ReservationDTO reservationDTO) {
        Reservation reservation = new Reservation();
        RestaurantTable table = tableRepository.findById(reservationDTO.getTableId()).orElseThrow(()-> new ResourceNotFoundException(format(TABLE_NOT_FOUND,reservationDTO.getTableId())));
            List<Reservation> tableReservations = reservationRepository.findByDate(reservationDTO.getDate()).stream()
                    .filter(r -> r.getTable().getTableId().equals(reservationDTO.getTableId()))
                    .collect(Collectors.toList());

            List<Reservation> reservationList = tableReservations.stream()
                    .filter(res -> reservationDTO.getTime().isBefore(res.getTime().minusMinutes(115)) ||
                            reservationDTO.getTime().isAfter(res.getTime().plusMinutes(115)))
                    .collect(Collectors.toList());

            if (reservationList.size()==tableReservations.size()) {
                createReservation(reservation, reservationDTO,table);
//                table.setTableStatus(TableStatus.RESERVED);
//                tableRepository.save(table);
                reservationRepository.save(reservation);
            }else {
                throw new BadRequestException(TABLE_TIME_RESERVED);
            }
        return toDto(reservation);
    }

    public ReservationDTO createReservation(Reservation reservation, ReservationDTO reservationDTO, RestaurantTable table){
        reservation.setComment(reservationDTO.getComment());
        reservation.setDate(reservationDTO.getDate());
        reservation.setTime(reservationDTO.getTime());
        reservation.setTable(table);
        table.setTableStatus(TableStatus.RESERVED);
        tableRepository.save(table);
        return toDto(reservation);
    }

    @Override
    public List<ReservationDTO> showAllReservations() {
        return reservationRepository.findAll().stream().map(RestaurantMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public ReservationDTO findReservationById(Long reservationId) {
        return toDto(reservationRepository.findById(reservationId).orElseThrow(()->new ResourceNotFoundException(format(RESERVATION_NOT_FOUND,reservationId))));
    }

    @Override
    public List<ReservationDTO> findReservationByUserId(Long userId) {
        return reservationRepository.findAll().stream()
                .filter(reservation -> reservation.getCreatedBy().getId().equals(userId))
                .map(RestaurantMapper::toDto)
                .collect(Collectors.toList());

    }
}
