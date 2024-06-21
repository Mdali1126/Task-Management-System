package com.maven.TaskManagementSystem.controller;

import com.maven.TaskManagementSystem.dto.UserDTO;
import com.maven.TaskManagementSystem.model.User;
import com.maven.TaskManagementSystem.mapper.UserMapper;
import com.maven.TaskManagementSystem.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("md/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping

    public ResponseEntity<UserDTO> createUser(@RequestBody UserDTO userDTO) {
        User user = UserMapper.toEntity(userDTO);
        User createdUser = userService.createUser(user);
        UserDTO createdUserDto = UserMapper.toDto(createdUser);
        return new ResponseEntity<>(createdUserDto, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable Long id) {
        User user = userService.getUserById(id);
        UserDTO userDTO = UserMapper.toDto(user);
        return ResponseEntity.ok(userDTO);
    }

    @GetMapping
    public ResponseEntity<List<UserDTO>> getAllUsers() {
        List<User> users = userService.getAllUsers();
        List<UserDTO> userDTOS = users.stream().map(UserMapper::toDto).toList();
        return ResponseEntity.ok(userDTOS);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserDTO> updateUser(@PathVariable Long id, @RequestBody UserDTO userDTO) {
        userDTO.setId(id);
      User user = UserMapper.toEntity(userDTO);
      User updatedUser = userService.updateUser(user);
      UserDTO updatedUserDTo = UserMapper.toDto(updatedUser);
      return ResponseEntity.ok(updatedUserDTo);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUserById(@PathVariable Long id){
        userService.deleteUser(id);
        return new ResponseEntity<>("User Deleted Successfully",HttpStatus.OK);
    }
}
