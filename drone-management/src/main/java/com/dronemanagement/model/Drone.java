package com.dronemanagement.model;
 
import javax.persistence.*;
import javax.validation.constraints.*;
import javax.validation.constraints.NotBlank;
 
@Entity
public class Drone {
 
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
 
    @NotBlank
    @Size(max = 100)
    private String serialNumber;
 
    @Enumerated(EnumType.STRING)
    @NotNull
    private DroneModel model;
 
    @Max(500)
    @NotNull
    private int weightLimit;
 
    @Min(0)
    @Max(100)
    private int batteryCapacity;
 
    @Enumerated(EnumType.STRING)
    @NotNull
    private DroneState state;
 
    // Getters and setters

    public DroneState getState() {
        return state;
    }

    public void setState(DroneState state) {
        this.state = state;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setModel(DroneModel model) {
        this.model = model;
    }

    public DroneModel getModel() {
        return model;
    }

    public void setWeightLimit(int weightLimit) {
        this.weightLimit = weightLimit;
    }

    public int getWeightLimit() {
        return weightLimit;
    }

    public void setBatteryCapacity(int batteryCapacity) {
        this.batteryCapacity = batteryCapacity;
    }

    public int getBatteryCapacity() {
        return batteryCapacity;
    }
}