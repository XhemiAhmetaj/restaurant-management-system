package com.ikubinfo.project.restaurantapp.service.impl;

import com.ikubinfo.project.restaurantapp.domain.dto.PageParameterDTO;
import com.ikubinfo.project.restaurantapp.domain.dto.ReservationDTO;
import com.ikubinfo.project.restaurantapp.domain.entity.Reservation;
import com.ikubinfo.project.restaurantapp.domain.entity.RestaurantTable;
import com.ikubinfo.project.restaurantapp.domain.entity.TableReservation;
import com.ikubinfo.project.restaurantapp.domain.entity.enums.TableStatus;
import com.ikubinfo.project.restaurantapp.domain.exception.BadRequestException;
import com.ikubinfo.project.restaurantapp.domain.exception.ResourceNotFoundException;
import com.ikubinfo.project.restaurantapp.domain.mapper.RestaurantMapper;
import com.ikubinfo.project.restaurantapp.repository.ReservationRepository;
import com.ikubinfo.project.restaurantapp.repository.RestaurantTableRepository;
import com.ikubinfo.project.restaurantapp.repository.TableReservationRepository;
import com.ikubinfo.project.restaurantapp.service.EmailSenderService;
import com.ikubinfo.project.restaurantapp.service.ReservationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static com.ikubinfo.project.restaurantapp.domain.exception.ExceptionConstants.*;
import static com.ikubinfo.project.restaurantapp.domain.mapper.RestaurantMapper.*;
import static java.lang.String.format;

@Service
@RequiredArgsConstructor
@Slf4j
public class ReservationServiceImpl implements ReservationService {

    private final ReservationRepository reservationRepository;
    private final RestaurantTableRepository tableRepository;
    private final TableReservationRepository tableReservationRepository;
    private final EmailSenderService emailService;

    @Override
    public ReservationDTO reserveTable(ReservationDTO reservationDTO){
        Reservation reservation = new Reservation();
        TableReservation tableReservation = new TableReservation();
        RestaurantTable table = tableRepository.findById(reservationDTO.getTableId()).orElseThrow();

        List<TableReservation> tableReservations = tableReservationRepository.findAllByDate(reservationDTO.getDate()).stream()
                        .filter(t-> (reservationDTO.getTime().isAfter(t.getStartTime().minusMinutes(115)) &&
                                        reservationDTO.getTime().isBefore(t.getStartTime().plusMinutes(115))))
                                .collect(Collectors.toList());

        if (tableReservations.isEmpty()) {
                createReservation(reservation, reservationDTO,table);
                reservationRepository.save(reservation);
                if(reservation.getCreatedBy().getEmail().equals("guest@gmail.com")){
                    reservationRepository.deleteById(reservation.getId());
                    throw new BadRequestException(USER_NOT_AUTHENTICATED);
                }else {
                    reservationRepository.save(reservation);
                }

                createTableReservation(reservation, tableReservation, table);
                tableReservationRepository.save(tableReservation);

                emailService.sendEmail(reservation.getCreatedBy().getEmail(), "Reservation Confirmed!",
                        "Dear "+reservation.getCreatedBy().getName()+
                                "\nYour reservation at our restaurant is confirmed!"+
                                "\n\nReservation Details"+
                                "\nReservation name: "+reservation.getCreatedBy().getName() + " " + reservation.getCreatedBy().getLastname() +
                                "\nDate: "+reservation.getDate()+
                                "\nTime: " + reservation.getTime()+
                                "\nNumber of People: " + reservation.getNumberOfPeople()+
                                "\nTable: "+ reservation.getTable().getTableId() + " " + reservation.getTable().getDescription()+
                                "\n\nWe hope you have a great time!"+
                                "\n\nAll the best, \nReservationTeam");

            }else {
                throw new BadRequestException(TABLE_TIME_RESERVED);
            }

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

    @Override
    public Page<ReservationDTO> findReservationByTableAndDate(LocalDate date, PageParameterDTO pageDTO){
        Sort sort = Sort.by(pageDTO.getSortDirection(), pageDTO.getSort());
        Pageable pageable = PageRequest.of(pageDTO.getPageNumber(),pageDTO.getPageSize(),sort);
        return reservationRepository.findReservationsByDate(date,pageable).map(RestaurantMapper::toDto);
    }
}
