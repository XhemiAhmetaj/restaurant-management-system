package com.ikubinfo.project.restaurantapp.controller;

import com.ikubinfo.project.restaurantapp.domain.dto.UserDTO;
import com.ikubinfo.project.restaurantapp.domain.dto.update.UserUpdatedDTO;
import com.ikubinfo.project.restaurantapp.domain.entity.User;
import com.ikubinfo.project.restaurantapp.domain.mapper.UserMapper;
import com.ikubinfo.project.restaurantapp.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import javax.validation.Valid;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @RolesAllowed("ADMIN")
    @PostMapping("/admin/{userRole}")
    public ResponseEntity<UserDTO> registerUser(@RequestBody @Valid UserDTO req, @PathVariable String userRole ){
        UserDTO dto = userService.registerUser(req, userRole);
        return ResponseEntity.ok(dto);

    }
    @RolesAllowed("ADMIN")
    @GetMapping("/admin/{id}")
    public ResponseEntity<UserDTO> findUser(@PathVariable Long id){
        User u = userService.findById(id);
        return ResponseEntity.ok(UserMapper.toDto(u));
    }

    @RolesAllowed("ADMIN")
    @GetMapping
    public ResponseEntity<List<UserDTO>> returnAllUsers(){
        return ResponseEntity.ok(userService.findAllUsers());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id){
        userService.deleteUser(id);
        return new ResponseEntity(HttpStatus.OK);
    }

    @RolesAllowed("ADMIN")
    @GetMapping("/admin/role/{userRole}")
    public ResponseEntity<List<UserDTO>> returnUserByRole(@PathVariable String userRole){
        return ResponseEntity.ok(userService.findUserByRole(userRole));
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserUpdatedDTO> updateUser(@RequestBody UserUpdatedDTO req, @PathVariable Long id){
        UserUpdatedDTO u =userService.updateUser(id, req);
        return ResponseEntity.ok(u);
    }

    @GetMapping("/{userId}/points")
    public ResponseEntity<Integer> getPoints(@PathVariable Long userId){
        return ResponseEntity.ok(userService.getUserPoints(userId));
    }

}
