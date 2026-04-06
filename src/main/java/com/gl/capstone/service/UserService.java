package com.gl.capstone.service;

import com.gl.capstone.entity.User;
import com.gl.capstone.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public User registerUser(User user) {
        userRepository.findByEmail(user.getEmail()).ifPresent(u -> { throw new RuntimeException("Email already exists"); });
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    public User getUserById(String empId) {
        return userRepository.findById(empId).orElseThrow(() -> new RuntimeException("User not found"));
    }

    public User updateUser(String empId, String bandLevel, String managerId) {
        User user = getUserById(empId);
        if(bandLevel != null) user.setBandLevel(bandLevel);
        if(managerId != null) user.setManagerId(managerId);
        return userRepository.save(user);
    }

    public List<User> getUsersByBand(String bandLevel) {
        return userRepository.findByBandLevel(bandLevel);
    }
}