package com.company.controller;

import com.company.api.service.IScooterService;
import com.company.model.dto.ScooterDto;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Log4j2
@RequiredArgsConstructor
@RestController
@RequestMapping("/scooters")
public class ScooterController {

    private final IScooterService scooterService;

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/{id}")
    public ResponseEntity<ScooterDto> getById(@PathVariable Long id) {
        log.info("received request: /scooter/getById" + id);
        return ResponseEntity.ok(scooterService.getById(id));
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping
    public ResponseEntity<Void> addScooter(@RequestBody ScooterDto request) {
        scooterService.addScooter(request);
        return ResponseEntity.noContent().build();

    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        log.info("received request: /scooter/delete " + id);
        scooterService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@PathVariable Long id, @RequestBody ScooterDto request) {
        log.info("received request: /scooter/update " + id);
        scooterService.update(id, request);
        return ResponseEntity.noContent().build();
    }

    @PreAuthorize(("hasAnyAuthority('ADMIN', 'USER')"))
    @GetMapping
    public ResponseEntity<List<ScooterDto>> getAll(@RequestParam(defaultValue = "") String sort) {
        log.info("received request: /scooter/getAllScooters ");
        return ResponseEntity.ok(scooterService.getAll(sort));
    }

    @PreAuthorize(("hasAnyAuthority('ADMIN', 'USER')"))
    @GetMapping("on/location/{id}")
    public ResponseEntity<List<ScooterDto>> getScootersOnLocation(@PathVariable Long id) {
        log.info("received request: /scooters On Location " + id);
        return ResponseEntity.ok(scooterService.getScootersOnLocation(id));
    }

}
