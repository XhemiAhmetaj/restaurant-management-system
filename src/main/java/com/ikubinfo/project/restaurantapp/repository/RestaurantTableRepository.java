package com.ikubinfo.project.restaurantapp.repository;

import com.ikubinfo.project.restaurantapp.domain.entity.RestaurantTable;
import com.ikubinfo.project.restaurantapp.domain.entity.enums.TableStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RestaurantTableRepository extends JpaRepository<RestaurantTable,Long> {

    List<RestaurantTable> findRestaurantTablesByTableStatus(TableStatus tableStatus);

//    List<RestaurantTable> findRestaurantTablesByStatus_Available();
    List<RestaurantTable> findRestaurantTablesByCapacity(Integer capacity);

}
