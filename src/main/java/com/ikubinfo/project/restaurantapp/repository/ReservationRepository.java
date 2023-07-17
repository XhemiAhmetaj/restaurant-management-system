package com.ikubinfo.project.restaurantapp.repository;

import com.ikubinfo.project.restaurantapp.domain.entity.Reservation;
import com.ikubinfo.project.restaurantapp.domain.entity.RestaurantTable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation,Long>, JpaSpecificationExecutor<Reservation> {

    List<Reservation> findByDate(LocalDate date);

    Page<Reservation> findReservationsByDate(LocalDate date, Pageable pageable);


}
