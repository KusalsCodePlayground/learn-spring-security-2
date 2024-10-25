package com.example.test.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api/v1/vehicles")
public class VehicleController {

    List<Object> vehicles = new ArrayList<>(
            List.of(
                    "Car",
                    "Bike",
                    "Truck"
            )
    );


    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public String addVehicle() {
        return "Vehicle Added";
    }

    @GetMapping
    public List<Object> getVehicles() {
        return vehicles;
    }


}
