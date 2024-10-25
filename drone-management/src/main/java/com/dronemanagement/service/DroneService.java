package com.dronemanagement.service;
 
import com.dronemanagement.model.Drone;
import com.dronemanagement.model.Medication;
import com.dronemanagement.model.DroneState;
import com.dronemanagement.repository.DroneRepository;
import com.dronemanagement.repository.MedicationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
 
import java.util.List;
import java.util.Optional;
 
@Service
public class DroneService {
 
    @Autowired
    private DroneRepository droneRepository;
 
    @Autowired
    private MedicationRepository medicationRepository;
 
    public Drone registerDrone(Drone drone) {
        return droneRepository.save(drone);
    }
 
    public List<Drone> getAvailableDronesForLoading() {
        return droneRepository.findByState(DroneState.IDLE);
    }
 
    public Optional<Drone> getDroneById(Long id) {
        return droneRepository.findById(id);
    }
 
    public void loadDrone(Long droneId, List<Medication> medications) {
        Drone drone = droneRepository.findById(droneId).orElseThrow(() -> new IllegalStateException("Drone not found"));
        
        int totalWeight = medications.stream().mapToInt(Medication::getWeight).sum();
        
        if (totalWeight > drone.getWeightLimit()) {
            throw new IllegalStateException("The weight exceeds the drone's capacity");
        }
 
        if (drone.getBatteryCapacity() < 25) {
            throw new IllegalStateException("Battery level too low to load");
        }
 
        drone.setState(DroneState.LOADING);
        droneRepository.save(drone);
        medicationRepository.saveAll(medications);
    }
 
    public List<Medication> getLoadedMedications(Long droneId) {
        Drone drone = droneRepository.findById(droneId).orElseThrow(() -> new IllegalStateException("Drone not found"));
        return medicationRepository.findAll(); // Simplified, you can add logic to filter by drone if needed
    }
 
    public int getDroneBatteryLevel(Long droneId) {
        Drone drone = droneRepository.findById(droneId).orElseThrow(() -> new IllegalStateException("Drone not found"));
        return drone.getBatteryCapacity();
    }
}