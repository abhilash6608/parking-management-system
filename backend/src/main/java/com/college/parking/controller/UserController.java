package com.college.parking.controller;

import com.college.parking.entity.User;
import com.college.parking.repository.UserRepository;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "http://localhost:5173")
public class UserController {
    private final UserRepository repo;
    public UserController(UserRepository repo){this.repo=repo;}

    @GetMapping public List<User> all(){return repo.findAll();}
    @GetMapping("/{id}") public ResponseEntity<User> one(@PathVariable Long id){
        return repo.findById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping public ResponseEntity<?> create(@RequestBody User user){
        if(repo.findByEmail(user.getEmail()).isPresent())
            return ResponseEntity.badRequest().body(Map.of("message","Email already registered"));
        if(user.getRole()==null) user.setRole(User.Role.STUDENT);
        return ResponseEntity.ok(repo.save(user));
    }
}
