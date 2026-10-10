package com.college.parking.controller;

import com.college.parking.entity.User;
import com.college.parking.repository.UserRepository;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "http://localhost:5173")
public class AuthController {
    private final UserRepository users;
    public AuthController(UserRepository users){this.users=users;}

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String,String> body){
        return users.findByEmail(body.get("email"))
            .filter(u -> u.getPassword().equals(body.get("password")))
            .<ResponseEntity<?>>map(u -> ResponseEntity.ok(Map.of(
                "id",u.getId(),"name",u.getName(),"email",u.getEmail(),"role",u.getRole().name()
            )))
            .orElseGet(() -> ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body(Map.of("message","Invalid email or password")));
    }
}
