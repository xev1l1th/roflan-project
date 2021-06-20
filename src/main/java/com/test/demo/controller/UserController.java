package com.test.demo.controller;

import com.test.demo.dto.UpdateUserDto;
import com.test.demo.dto.UserDto;
import com.test.demo.model.User;
import com.test.demo.repo.UserRepo;
import io.swagger.v3.oas.annotations.*;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("users")
public class UserController {

    @Autowired
    private UserRepo userRepo;

    @GetMapping("")
    public ResponseEntity<List<UserDto>> getAllUsers(){
        return ResponseEntity.ok(getUserDtos());
    }

    @PostMapping("")
    public ResponseEntity<List<UserDto>> add(@RequestParam String name){
        User user = new User(0L, name);
        userRepo.save(user);
        return ResponseEntity.ok(getUserDtos());
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Object> deleteUser(@PathVariable Long id){
        User user = userRepo.findById(id).orElse(null);
        if(user != null){
            userRepo.delete(user);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.status(400).body("{\"bad\":\"request\"");
        }
    }

    @PutMapping("{id}")
    public ResponseEntity<Object> updateUser(@RequestBody UpdateUserDto dto, @PathVariable Long id){
        User user = userRepo.findById(id).orElse(null);
        if(user != null){
            user.setName(dto.getName());
            userRepo.save(user);
            return ResponseEntity.ok(new UserDto(id, dto.getName()));
        } else {
            return ResponseEntity.status(400).body("{\"bad\":\"request\"");
        }
    }

    private List<UserDto> getUserDtos() {
        return userRepo
                .findAll()
                .stream()
                .map(UserDto::new)
                .collect(Collectors.toList());
    }
}
