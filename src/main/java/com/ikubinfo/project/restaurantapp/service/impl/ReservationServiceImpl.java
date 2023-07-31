package com.ikubinfo.project.restaurantapp.service.impl;

import com.ikubinfo.project.restaurantapp.domain.dto.PageParameterDTO;
import com.ikubinfo.project.restaurantapp.domain.dto.ReservationDTO;
import com.ikubinfo.project.restaurantapp.domain.entity.Reservation;
import com.ikubinfo.project.restaurantapp.domain.entity.RestaurantTable;
import com.ikubinfo.project.restaurantapp.domain.entity.TableReservation;
import com.ikubinfo.project.restaurantapp.domain.entity.User;
import com.ikubinfo.project.restaurantapp.domain.entity.enums.TableStatus;
import com.ikubinfo.project.restaurantapp.domain.exception.BadRequestException;
import com.ikubinfo.project.restaurantapp.domain.exception.ResourceNotFoundException;
import com.ikubinfo.project.restaurantapp.domain.mapper.RestaurantMapper;
import com.ikubinfo.project.restaurantapp.repository.ReservationRepository;
import com.ikubinfo.project.restaurantapp.repository.RestaurantTableRepository;
import com.ikubinfo.project.restaurantapp.repository.TableReservationRepository;
import com.ikubinfo.project.restaurantapp.service.EmailSenderService;
import com.ikubinfo.project.restaurantapp.service.ReservationService;
import com.ikubinfo.project.restaurantapp.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static com.ikubinfo.project.restaurantapp.configuration.SecurityConfig.getJwt;
import static com.ikubinfo.project.restaurantapp.domain.Constants.*;
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
    private final UserService userService;

    @Override
    public ReservationDTO reserveTable(ReservationDTO reservationDTO){
        Reservation reservation = new Reservation();
        TableReservation tableReservation = new TableReservation();
        RestaurantTable table = tableRepository.findById(reservationDTO.getTableId()).orElseThrow();

        User u = userService.getUserFromToken(getJwt());
        if(u.getEmail().equals(GUEST_EMAIL_ADDRESS)){
            throw new BadRequestException(USER_NOT_AUTHENTICATED);
        }

        if(reservationDTO.getDate().isBefore(LocalDate.now())&&reservationDTO.getTime().isBefore(LocalTime.now().plusMinutes(50))){
            throw new BadRequestException(RESERVATION_FAILED);
        }


        List<TableReservation> tableReservations = tableReservationRepository.findAllByDate(reservationDTO.getDate()).stream()
                        .filter(t-> (reservationDTO.getTime().isAfter(t.getStartTime().minusMinutes(115)) &&
                                        reservationDTO.getTime().isBefore(t.getStartTime().plusMinutes(115))))
                .filter(t->t.getTableReservation().equals(table))
                                .collect(Collectors.toList());

        log.info("----------------tableReservation.size---------" + tableReservations.size());

        if (tableReservations.isEmpty()) {
                createReservation(reservation, reservationDTO,table);
                reservationRepository.save(reservation);
                if(reservation.getCreatedBy().getEmail().equals(GUEST_EMAIL_ADDRESS)){
                    reservationRepository.deleteById(reservation.getId());
                    throw new BadRequestException(USER_NOT_AUTHENTICATED);
                }else {
                    reservationRepository.save(reservation);
                }

                createTableReservation(reservation, tableReservation, table);
                tableReservationRepository.save(tableReservation);

                emailService.sendEmail(reservation.getCreatedBy().getEmail(), EMAIL_CONFIRMATION_SUBJECT,
                        format(EMAIL_CONFIRMATION_BODY,reservation.getCreatedBy().getName(),
                                reservation.getCreatedBy().getName() , reservation.getCreatedBy().getLastname() ,
                                reservation.getDate(),
                                reservation.getTime(),
                                reservation.getNumberOfPeople(),
                                reservation.getTable().getTableId()
                                ));

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
