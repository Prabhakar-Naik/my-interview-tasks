package com.trade.one.controllers;

import com.trade.one.dtos.UserDTO;
import com.trade.one.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author prabhakar, @Date 26-02-2025
 */

@RestController
@RequestMapping(value = "/api/admin")
public class AdminController {

    @Autowired
    private UserService userService;


    @GetMapping("/all/users")
    public ResponseEntity<List<UserDTO>> getAllUsers() {
        List<UserDTO> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<String> deleteUser(@PathVariable Long userId) {
        String response = userService.deleteUser(userId);
        return ResponseEntity.ok(response);
    }



}
