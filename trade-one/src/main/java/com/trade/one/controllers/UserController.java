package com.trade.one.controllers;

import com.trade.one.dtos.UserDTO;
import com.trade.one.dtos.UserLoginDTO;
import com.trade.one.dtos.UserSignupDTO;
import com.trade.one.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author prabhakar, @Date 25-02-2025
 */
@RestController
@RequestMapping(value = "/api/users")
public class UserController {

    @Autowired
    private UserService userService;


    @GetMapping("/{userId}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable Long userId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();
        if (this.userService.isPresentEmail(userName)) {
            UserDTO userDto = userService.getUserById(userId);
            return ResponseEntity.ok(userDto);
        }else
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new UserDTO());
    }



    @PutMapping("/{userId}")
    public ResponseEntity<String> updateUser(@PathVariable Long userId, @RequestBody UserDTO userDto) {
        String response = userService.updateUser(userId, userDto);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<String> deleteUser(@PathVariable Long userId) {
        String response = userService.deleteUser(userId);
        return ResponseEntity.ok(response);
    }
}
