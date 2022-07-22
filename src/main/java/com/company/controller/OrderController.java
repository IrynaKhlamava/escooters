package com.company.controller;

import com.company.api.service.IOrderService;

import com.company.model.dto.OrderDto;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Log4j2
@RequiredArgsConstructor
@RestController
@RequestMapping("/orders")
public class OrderController {

    private final IOrderService orderService;

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/{id}")
    public ResponseEntity<OrderDto> getById(@PathVariable Long id) {
        log.info("received request: /order/getById" + id);
        return ResponseEntity.ok(orderService.getById(id));
    }

    @PreAuthorize(("hasAnyAuthority('ADMIN', 'USER')"))
    @PostMapping
    public ResponseEntity<Void> addOrder(@RequestBody OrderDto order) {
        orderService.addOrder(order);
        return ResponseEntity.noContent().build();
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        log.info("received request: /order/delete " + id);
        orderService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PreAuthorize(("hasAnyAuthority('ADMIN', 'USER')"))
    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@PathVariable Long id, @RequestBody OrderDto request) {
        log.info("received request: /order/update " + id);
        orderService.update(id, request);
        return ResponseEntity.noContent().build();
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping
    public ResponseEntity<List<OrderDto>> getAll(@RequestParam(defaultValue = "") String sort) {
        log.info("received request: /order/getAll ");
        return ResponseEntity.ok(orderService.getAll(sort));

    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping(path = "/scooterHistory/{id}")
    public ResponseEntity<List<OrderDto>> getScooterHistory(@PathVariable Long id) {
        log.info("received request: /order/scooterHistory " + id);
        return ResponseEntity.ok(orderService.getHistory(id, "scooterId"));
    }

    @PreAuthorize(("hasAnyAuthority('ADMIN', 'USER')"))
    @GetMapping(path = "/userHistory/{id}")
    public ResponseEntity<List<OrderDto>> getUserHistory(@PathVariable Long id) {
        log.info("received request: /order/userHistory " + id);
        return ResponseEntity.ok(orderService.getHistory(id, "userId"));
    }

    @PreAuthorize(("hasAnyAuthority('ADMIN', 'USER')"))
    @GetMapping(path = "/close/{id}")
    public ResponseEntity<OrderDto> closeOrder(
            @PathVariable Long id, @RequestParam(name = "mileage") Double mileage,
            @RequestParam(name = "newLocationId") Long newLocationId
    ) {
        log.info("received request: /close/byId/" + id);
        return ResponseEntity.ok(orderService.closeOrder(id, mileage, newLocationId));
    }

}
