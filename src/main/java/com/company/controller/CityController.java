package com.company.controller;

import com.company.api.service.ICityService;
import com.company.model.dto.CityDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Log4j2
@RequiredArgsConstructor
@RestController
@RequestMapping("/cities")
public class CityController {

    private final ICityService cityService;

    @PreAuthorize(("hasAnyAuthority('ADMIN', 'USER')"))
    @GetMapping("/{id}")
    public ResponseEntity<CityDto> getById(@PathVariable Long id) {
        log.info("received request: /city/getById" + id);
        return ResponseEntity.ok(cityService.getById(id));
    }

    @PreAuthorize(("hasAnyAuthority('ADMIN', 'USER')"))
    @GetMapping("/{name}")
    public ResponseEntity<CityDto> getByName(@PathVariable String name) {
        log.info("received request: /city/getByName" + name);
        return ResponseEntity.ok(cityService.getByName(name));
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping
    public ResponseEntity<Void> addCity(@RequestParam String city) {
        cityService.addCity(city);
        return ResponseEntity.noContent().build();
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@PathVariable Long id, @RequestBody CityDto request) {
        log.info("received request: /city/update " + id);
        cityService.update(id, request);
        return ResponseEntity.noContent().build();
    }

    @PreAuthorize(("hasAnyAuthority('ADMIN', 'USER')"))
    @GetMapping
    public ResponseEntity<List<CityDto>> getAll(@RequestParam(defaultValue = "") String sort) {
        log.info("received request: /city/getAll");
        return ResponseEntity.ok(cityService.getAll(sort));
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        log.info("received request: /city/delete " + id);
        cityService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
