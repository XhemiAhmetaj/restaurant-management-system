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

import javax.annotation.security.RolesAllowed;
import java.time.LocalDate;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/restaurant")
public class RestaurantController {

    private final RestaurantService restaurantService;
    private final ReservationService reservationService;

    @GetMapping("/menu")
    public ResponseEntity<List<MenuDTO>> getMenu(){
        return ResponseEntity.ok(restaurantService.getMenu());
    }
    @RolesAllowed("COOKER")
    @GetMapping("/menu/{menuId}")
    public ResponseEntity<MenuDTO> saveMenu(@PathVariable Long menuId){
        return ResponseEntity.ok(restaurantService.saveMenu(menuId));
    }
    @RolesAllowed("COOKER")
    @PostMapping("/menu")
    public ResponseEntity<MenuDTO> addMenuItem(@RequestBody AddMenuItem menuItem){
        return ResponseEntity.ok(restaurantService.addMenuItem(menuItem));
    }

    @GetMapping("/tables")
    public ResponseEntity<List<RestaurantTableDTO>> getAllTables(){
        return ResponseEntity.ok(restaurantService.listAllTables());
    }

    @RolesAllowed("ADMIN")
    @PostMapping("/tables")
    public ResponseEntity<RestaurantTableDTO> addTable(@RequestBody RestaurantTableDTO tableDTO){
        return ResponseEntity.ok(restaurantService.addTable(tableDTO));
    }

    @GetMapping("/tables/capacity/{tableCapacity}")
    public ResponseEntity<List<RestaurantTableDTO>> listTablesByCapacity(@PathVariable Integer tableCapacity){
        return ResponseEntity.ok(restaurantService.listTablesByCapacity(tableCapacity));
    }

    @RolesAllowed("CUSTOMER")
    @PostMapping("/reservation/add")
    public ResponseEntity<ReservationDTO> addReservation(@RequestBody ReservationDTO reservationDTO){
        return ResponseEntity.ok(reservationService.reserveTable( reservationDTO ) );
    }

    @RolesAllowed({"ADMIN", "RECEPTIONIST"})
    @GetMapping("/reservations")
    public ResponseEntity<List<ReservationDTO>> listReservations(){
        return ResponseEntity.ok(reservationService.showAllReservations());
    }

    @RolesAllowed({"ADMIN", "RECEPTIONIST"})
    @GetMapping("/reservations/{reservationId}")
    public ResponseEntity<ReservationDTO> getReservationById(@PathVariable Long reservationId){
        return ResponseEntity.ok(reservationService.findReservationById(reservationId));
    }

    @RolesAllowed({"ADMIN", "RECEPTIONIST"})
    @GetMapping("/reservations/user/{userId}")
    public ResponseEntity<List<ReservationDTO>> getReservationByUserId(@PathVariable Long userId){
        return ResponseEntity.ok(reservationService.findReservationByUserId(userId));
    }

    @RolesAllowed({"ADMIN", "RECEPTIONIST"})
    @GetMapping("/reservations/date/{date}")
    public ResponseEntity<Page<ReservationDTO>> getReservationsByTableAndDate(@PathVariable String date, PageParameterDTO pageParameterDTO){
        return ResponseEntity.ok(reservationService.findReservationByTableAndDate(LocalDate.parse(date),pageParameterDTO));
    }

}
