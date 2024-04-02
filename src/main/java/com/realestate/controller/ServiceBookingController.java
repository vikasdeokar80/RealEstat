package com.realestate.controller;

import com.realestate.dto.ServiceBookingDto;
import com.realestate.exception.ServiceBookingNotFoundException;
import com.realestate.service.ServiceBookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/service-bookings")
public class ServiceBookingController {

    @Autowired
    private ServiceBookingService serviceBookingService;

    @PostMapping("/add")
    public ResponseEntity<String> createServiceBooking(@RequestBody ServiceBookingDto serviceBookingDto) {
        try {
            serviceBookingService.createServiceBooking(serviceBookingDto);
            return ResponseEntity.ok("Service booking created successfully.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to create service booking: " + e.getMessage());
        }
    }

    @GetMapping("/byId")
    public ResponseEntity<ServiceBookingDto> getServiceBookingById(@RequestParam Integer serviceBookingId) {
        try {
            ServiceBookingDto serviceBookingDto = serviceBookingService.getServiceBookingById(serviceBookingId);
            return ResponseEntity.status(HttpStatus.OK).body(serviceBookingDto);
        } catch (ServiceBookingNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @GetMapping("/all")
    public ResponseEntity<List<ServiceBookingDto>> getAllServiceBookings() {
        List<ServiceBookingDto> serviceBookings = serviceBookingService.getAllServiceBookings();
        return ResponseEntity.status(HttpStatus.OK).body(serviceBookings);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteServiceBooking(@RequestParam Integer serviceBookingId) {
        try {
            serviceBookingService.deleteServiceBooking(serviceBookingId);
            return ResponseEntity.ok("Service booking deleted successfully.");
        } catch (ServiceBookingNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Service booking not found with ID: " + serviceBookingId);
        }
    }

    @PatchMapping("/update/{serviceBookingId}")
    public ResponseEntity<String> updateServiceBooking(@RequestBody ServiceBookingDto serviceBookingDto, @PathVariable Integer serviceBookingId) {
        try {
            serviceBookingService.updateServiceBooking(serviceBookingId, serviceBookingDto);
            return ResponseEntity.ok("Service booking updated successfully.");
        } catch (ServiceBookingNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Service booking not found with ID: " + serviceBookingId);
        }
    }
}
