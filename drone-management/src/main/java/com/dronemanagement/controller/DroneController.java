package com.dronemanagement.controller;
 
import com.dronemanagement.model.Medication;
import com.dronemanagement.service.DroneService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
 
import java.util.List;

import com.dronemanagement.model.Drone;
 
@RestController
@RequestMapping("/api/drones")
public class DroneController {
 
    @Autowired
    private DroneService droneService;
 
    @PostMapping("/register")
    public ResponseEntity<Drone> registerDrone(@RequestBody Drone drone) {
        return ResponseEntity.ok(droneService.registerDrone(drone));
    }
 
    @GetMapping("/available")
    public ResponseEntity<List<Drone>> getAvailableDrones() {
        return ResponseEntity.ok(droneService.getAvailableDronesForLoading());
    }
 
    @PostMapping("/{droneId}/load")
    public ResponseEntity<Void> loadDrone(@PathVariable Long droneId, @RequestBody List<Medication> medications) {
        droneService.loadDrone(droneId, medications);
        return ResponseEntity.ok().build();
    }
 
    @GetMapping("/{droneId}/medications")
    public ResponseEntity<List<Medication>> getLoadedMedications(@PathVariable Long droneId) {
        return ResponseEntity.ok(droneService.getLoadedMedications(droneId));
    }
 
    @GetMapping("/{droneId}/battery")
    public ResponseEntity<Integer> getDroneBatteryLevel(@PathVariable Long droneId) {
        return ResponseEntity.ok(droneService.getDroneBatteryLevel(droneId));
    }
}