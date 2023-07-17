package com.ikubinfo.project.restaurantapp.repository;

import com.ikubinfo.project.restaurantapp.domain.entity.TableReservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.Table;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface TableReservationRepository extends JpaRepository<TableReservation,Long> {

    List<TableReservation> findAllByTableReservation_TableId(Long tableId);
    List<TableReservation> findAllByDate(LocalDate date);
}
