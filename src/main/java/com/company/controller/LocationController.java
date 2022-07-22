package com.company.controller;

import com.company.api.service.ILocationService;

import com.company.model.dto.LocationDto;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Log4j2
@RequiredArgsConstructor
@RestController
@RequestMapping("/locations")
public class LocationController {

    private final ILocationService locationService;

    @PreAuthorize(("hasAnyAuthority('ADMIN', 'USER')"))
    @GetMapping("/{id}")
    public ResponseEntity<LocationDto> getById(@PathVariable Long id) {
        log.info("received request: /location/getById" + id);
        return ResponseEntity.ok(locationService.getById(id));
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping
    public ResponseEntity<Void> addLocation(@RequestBody LocationDto location) {
        locationService.addLocation(location);
        return ResponseEntity.noContent().build();
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        log.info("received request: /location/delete " + id);
        locationService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@PathVariable Long id, @RequestBody LocationDto request) {
        log.info("received request: /location/update " + id);
        locationService.update(id, request);
        return ResponseEntity.noContent().build();
    }

    @PreAuthorize(("hasAnyAuthority('ADMIN', 'USER')"))
    @GetMapping
    public ResponseEntity<List<LocationDto>> getAll(@RequestParam(defaultValue = "") String sort) {
        log.info("received request: /location/getAllLocations ");
        return ResponseEntity.ok(locationService.getAll(sort));
    }

}
