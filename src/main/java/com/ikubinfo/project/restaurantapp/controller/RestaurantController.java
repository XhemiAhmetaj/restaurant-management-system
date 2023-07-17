package com.ikubinfo.project.restaurantapp.controller;

import com.ikubinfo.project.restaurantapp.domain.dto.*;
import com.ikubinfo.project.restaurantapp.domain.dto.update.TableUpdatedDTO;
import com.ikubinfo.project.restaurantapp.domain.entity.Reservation;
import com.ikubinfo.project.restaurantapp.service.ReservationService;
import com.ikubinfo.project.restaurantapp.service.RestaurantService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/restaurant")
public class RestaurantController {

    private final RestaurantService restaurantService;
    private final ReservationService reservationService;

//    @GetMapping("/menu")
//    public ResponseEntity<List<MenuDTO>> getMenu(){
//        return ResponseEntity.ok(restaurantService.getMenu());
//    }

    @GetMapping("/tables")
    public ResponseEntity<List<RestaurantTableDTO>> getAllTables(){
        return ResponseEntity.ok(restaurantService.listAllTables());
    }

    @PostMapping("/tables")
    public ResponseEntity<RestaurantTableDTO> addTable(@RequestBody RestaurantTableDTO tableDTO){
        return ResponseEntity.ok(restaurantService.addTable(tableDTO));
    }

    @PutMapping("/tables/{tableId}/{tableStatus}")
    public ResponseEntity<RestaurantTableDTO> updateTable(@PathVariable Long tableId, @PathVariable String tableStatus){
        return ResponseEntity.ok(restaurantService.updateTable(tableId,tableStatus));
    }

    @GetMapping("/tables/{tableStatus}")
    public ResponseEntity<List<RestaurantTableDTO>> listTablesByStatus(@PathVariable String tableStatus){
        return ResponseEntity.ok(restaurantService.listTablesByStatus(tableStatus));
    }

    @GetMapping("/tables/available")
    public ResponseEntity<List<RestaurantTableDTO>> listAvailableTables(){
        return ResponseEntity.ok(restaurantService.listAllAvailableTables());
    }

    @GetMapping("/tables/capacity/{tableCapacity}")
    public ResponseEntity<List<RestaurantTableDTO>> listTablesByCapacity(@PathVariable Integer tableCapacity){
        return ResponseEntity.ok(restaurantService.listTablesByCapacity(tableCapacity));
    }

    @PostMapping("/reservation/add")
    public ResponseEntity<ReservationDTO> addReservation(@RequestBody ReservationDTO reservationDTO){
        return ResponseEntity.ok(reservationService.reserveTable( reservationDTO ) );
    }

    @GetMapping("/reservations")
    public ResponseEntity<List<ReservationDTO>> listReservations(){
        return ResponseEntity.ok(reservationService.showAllReservations());
    }

    @GetMapping("/reservations/{reservationId}")
    public ResponseEntity<ReservationDTO> getReservationById(@PathVariable Long reservationId){
        return ResponseEntity.ok(reservationService.findReservationById(reservationId));
    }

    @GetMapping("/reservations/user/{userId}")
    public ResponseEntity<List<ReservationDTO>> getReservationByUserId(@PathVariable Long userId){
        return ResponseEntity.ok(reservationService.findReservationByUserId(userId));
    }

    @GetMapping("/reservations/date/{date}")
    public ResponseEntity<Page<ReservationDTO>> getReservationsByTableAndDate(@PathVariable String date, PageParameterDTO pageParameterDTO){
        return ResponseEntity.ok(reservationService.findReservationByTableAndDate(LocalDate.parse(date),pageParameterDTO));
    }

}
