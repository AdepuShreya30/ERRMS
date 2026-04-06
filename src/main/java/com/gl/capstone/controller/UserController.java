package com.gl.capstone.controller;

import com.gl.capstone.entity.User;
import com.gl.capstone.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<User> registerUser(@RequestBody User user) {
        return ResponseEntity.ok(userService.registerUser(user));
    }

    @GetMapping("/{empId}")
    public ResponseEntity<User> getUser(@PathVariable String empId) {
        return ResponseEntity.ok(userService.getUserById(empId));
    }

    @PutMapping("/{empId}")
    public ResponseEntity<User> updateUser(@PathVariable String empId,
                                           @RequestParam(required = false) String bandLevel,
                                           @RequestParam(required = false) String managerId) {
        return ResponseEntity.ok(userService.updateUser(empId, bandLevel, managerId));
    }

    @GetMapping("/band/{bandLevel}")
    public ResponseEntity<List<User>> getUsersByBand(@PathVariable String bandLevel) {
        return ResponseEntity.ok(userService.getUsersByBand(bandLevel));
    }
}