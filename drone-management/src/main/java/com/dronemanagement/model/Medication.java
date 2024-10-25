package com.dronemanagement.model;
 
import javax.persistence.*;
import javax.validation.constraints.*;
 
@Entity
public class Medication {
 
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
 
    @NotBlank
    @Pattern(regexp = "^[a-zA-Z0-9-_]+$")
    private String name;
 
    @NotNull
    @Max(500)
    private int weight;
 
    @NotBlank
    @Pattern(regexp = "^[A-Z0-9_]+$")
    private String code;
 
    @Lob
    private byte[] image; // Assuming it's stored as a byte array
 
    // Getters and setters
}