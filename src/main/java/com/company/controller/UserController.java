package com.company.controller;

import com.company.api.service.IUserService;

import com.company.model.dto.UserDto;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Log4j2
@RequiredArgsConstructor
@RestController
@RequestMapping("/users")
public class UserController {

    private final IUserService userService;

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getById(@PathVariable Long id) {
        log.info("received request: /user/getById" + id);
        return ResponseEntity.ok(userService.getById(id));
    }

    @PreAuthorize(("hasAnyAuthority('ADMIN', 'USER')"))
    @PostMapping
    public ResponseEntity<Void> addUser(@RequestBody UserDto request) {
        userService.addUser(request.getName(), request.getPhone(), request.getLogin(), request.getPassword());
        return ResponseEntity.noContent().build();
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping
    public ResponseEntity<List<UserDto>> getAll(@RequestParam(defaultValue = "") String sort) {
        log.info("received request: /user/getAll ");
        return ResponseEntity.ok(userService.getAll(sort));
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        log.info("received request: /user/delete " + id);
        userService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PreAuthorize(("hasAnyAuthority('ADMIN', 'USER')"))
    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@PathVariable Long id, @RequestBody UserDto request) {
        log.info("received request: /user/update " + id);
        userService.update(id, request);
        return ResponseEntity.noContent().build();
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PutMapping("/status/{id}")
    public ResponseEntity<Void> changeUserStatus(@PathVariable Long id, @RequestParam String status) {
        log.info("received request: /users/changeUserStatus " + id);
        userService.changeUserStatus(id, status);
        return ResponseEntity.noContent().build();
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PutMapping("/discount/{id}")
    public ResponseEntity<Void> discount(@PathVariable Long id, @RequestParam Integer discount) {
        log.info("received request: /users/changeDiscount " + id);
        userService.changeUserDiscount(id, discount);
        return ResponseEntity.noContent().build();
    }

}