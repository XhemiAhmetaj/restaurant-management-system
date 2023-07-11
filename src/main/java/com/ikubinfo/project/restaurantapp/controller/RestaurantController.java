package com.ikubinfo.project.restaurantapp.controller;

import com.ikubinfo.project.restaurantapp.domain.dto.MenuDTO;
import com.ikubinfo.project.restaurantapp.domain.dto.RestaurantTableDTO;
import com.ikubinfo.project.restaurantapp.domain.dto.update.TableUpdatedDTO;
import com.ikubinfo.project.restaurantapp.service.RestaurantService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/restaurant")
public class RestaurantController {

    private final RestaurantService restaurantService;

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
}
