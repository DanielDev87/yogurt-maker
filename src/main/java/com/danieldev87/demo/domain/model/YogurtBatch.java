package com.danieldev87.demo.domain.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Table(name = "yogurt_batches")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(name = "YogurtBatch", description = "Yogurt production batch entity with lifecycle, quantities and timestamps")
public class YogurtBatch {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "Unique batch identifier", example = "100")
    private Long id;
    
    @Column(nullable = false)
    @Schema(description = "Human-readable batch code generated for traceability", example = "YB-1714298423123")
    private String batchCode;
    
    @ManyToOne
    @JoinColumn(name = "recipe_id", nullable = false)
    @Schema(description = "Recipe used to produce the batch")
    private Recipe recipe;
    
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    @Schema(description = "Current processing status of the batch", example = "INCUBATING")
    private BatchStatus status;
    
    @Column(nullable = false)
    @Schema(description = "Milk volume used in the batch in liters", example = "5.0")
    private Double milkVolume; // en litros
    
    @Column(nullable = false)
    @Schema(description = "Starter culture amount used in tablespoons", example = "3.0")
    private Double starterAmount; // en cucharadas
    
    @Column(nullable = false)
    @Schema(description = "Target temperature for the current process stage in Celsius", example = "42.0")
    private Double targetTemperature; // temperatura objetivo en °C
    
    @Column(nullable = false)
    @Schema(description = "Configured incubation time in hours", example = "8")
    private Integer incubationTime; // tiempo en horas
    
    @Schema(description = "Date and time when the batch started", example = "2026-04-28T08:00:00")
    private LocalDateTime startTime;
    @Schema(description = "Date and time when incubation began", example = "2026-04-28T10:00:00")
    private LocalDateTime incubationStartTime;
    @Schema(description = "Date and time when incubation ended", example = "2026-04-28T18:00:00")
    private LocalDateTime incubationEndTime;
    @Schema(description = "Date and time when refrigeration began", example = "2026-04-28T18:30:00")
    private LocalDateTime refrigerationStartTime;
    
    @OneToMany(mappedBy = "batch", cascade = CascadeType.ALL)
    @Builder.Default
    @Schema(description = "Temperature records associated with the batch")
    private List<TemperatureLog> temperatureLogs = new ArrayList<>();
    
    @Schema(description = "Operational notes or failure details for the batch", example = "Batch failed due to unstable incubation temperature")
    private String notes;
    
    @Column(nullable = false)
    @Schema(description = "Date and time when the batch record was created", example = "2026-04-28T08:00:00")
    private LocalDateTime createdAt;
    
    @Schema(description = "Date and time of the last update applied to the batch", example = "2026-04-28T18:30:00")
    private LocalDateTime updatedAt;
    
    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        batchCode = "YB-" + System.currentTimeMillis();
    }
    
    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }
    
    public enum BatchStatus {
        PREPARING, 
        HEATING, 
        COOLING, 
        INOCULATING, 
        INCUBATING, 
        REFRIGERATING, 
        COMPLETED, 
        FAILED
    }
}

