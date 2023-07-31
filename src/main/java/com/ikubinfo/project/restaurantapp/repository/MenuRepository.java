package com.ikubinfo.project.restaurantapp.repository;

import com.ikubinfo.project.restaurantapp.domain.entity.Menu;
import com.ikubinfo.project.restaurantapp.domain.entity.enums.MenuStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MenuRepository extends JpaRepository<Menu,Long> {

    List<Menu> findAllByDeletedIs(Boolean isDeleted);
    List<Menu> findAllByStatus(MenuStatus status);
}
