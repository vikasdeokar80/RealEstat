package com.realestate.controller;

import com.realestate.dto.PropertyDto;
import com.realestate.exception.PropertyNotFoundException;
import com.realestate.service.PropertyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/property")
public class PropertyController {

    @Autowired
    private PropertyService propertyService;

    @PostMapping("/add")
    public ResponseEntity<String> createProperty(@RequestBody PropertyDto propertyDto) {
        try {
            this.propertyService.addProperty(propertyDto);
            return ResponseEntity.ok("Property created successfully.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to create property: " + e.getMessage());
        }
    }

    @PutMapping("/update")
    public ResponseEntity<String> editProperty(@RequestBody PropertyDto propertyDto, @RequestParam Integer propertyId) {
        try {
            this.propertyService.updateProperty(propertyDto, propertyId);
            return ResponseEntity.ok("Property updated successfully.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to update property: " + e.getMessage());
        }
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<PropertyDto>> showAllProperties() {
        List<PropertyDto> allProperties = this.propertyService.getAllProperties();
        return new ResponseEntity<>(allProperties, HttpStatus.OK);
    }

    @GetMapping("/getById")
    public ResponseEntity<?> showPropertyById(@RequestParam Integer propertyId) throws PropertyNotFoundException {
        try {
            PropertyDto propertyById = this.propertyService.getPropertyById(propertyId);
            return new ResponseEntity<>(propertyById, HttpStatus.OK);
        } catch (PropertyNotFoundException e) {
            throw new PropertyNotFoundException("Property not found with ID: " + propertyId);
        }
    }

    @DeleteMapping("/deleteById")
    public ResponseEntity<?> deleteById(@RequestParam Integer propertyId) throws PropertyNotFoundException {
        try {
            this.propertyService.deletePropertyById(propertyId);
            return ResponseEntity.ok("Property deleted successfully.");
        } catch (PropertyNotFoundException e) {
            throw new PropertyNotFoundException("Property not found with ID: " + propertyId);
        }

    }

}
