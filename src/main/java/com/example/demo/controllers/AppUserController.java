package com.example.demo.controllers;

import com.example.demo.entity.AppUser;
import com.example.demo.repository.AppUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class AppUserController {

    @Autowired
    private AppUserRepository appUserRepository;

    @GetMapping
    public List<AppUser> getAllUser(){
        return appUserRepository.findAll();
    }

    @GetMapping("/{id}")
    public AppUser getUserById(@PathVariable int id){
        return appUserRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
    }

    @PostMapping
    public AppUser createUser(@RequestBody AppUser user) {
        return appUserRepository.save(user);
    }

    @PutMapping("/{id}")
    public AppUser updateUser(@PathVariable int id, @RequestBody AppUser updatedUser) {
        AppUser user = appUserRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
        user.setUsername(updatedUser.getUsername());
        user.setEmail(updatedUser.getEmail());
        return appUserRepository.save(user);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable int id) {
        appUserRepository.deleteById(id);
    }

}
