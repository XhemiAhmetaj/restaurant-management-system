package com.ikubinfo.project.restaurantapp.repository;

import com.ikubinfo.project.restaurantapp.domain.dto.PageParameterDTO;
import com.ikubinfo.project.restaurantapp.domain.entity.User;
import com.ikubinfo.project.restaurantapp.domain.entity.enums.UserRole;
import com.ikubinfo.project.restaurantapp.repository.specification.SearchCriteria;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long> , JpaSpecificationExecutor<User> {

    Optional<User> findByEmail(String email);
    List<User> findUsersByRole(UserRole role);
    Page<User> findUserByRole(UserRole role, Pageable pageable);

    @Query(value = "SELECT * FROM public.users u WHERE u.role='CUSTOMER' AND u.total_points>0 ORDER BY u.total_points desc", nativeQuery = true)
    List<User> findTopTenUsersByTotalPoint(Pageable pageable);

    @Query(value = "SELECT * FROM public.users u WHERE EXTRACT(DOY FROM birthday) = EXTRACT(DOY FROM now()) AND u.role='CUSTOMER'", nativeQuery = true)
    List<User> findUsersByBirthdayIsToday();

}
