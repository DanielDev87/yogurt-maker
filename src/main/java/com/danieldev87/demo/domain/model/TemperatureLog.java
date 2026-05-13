package com.danieldev87.demo.domain.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "temperature_logs")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(name = "TemperatureLog", description = "Temperature measurement recorded for a yogurt batch during production")
public class TemperatureLog {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "Unique temperature log identifier", example = "500")
    private Long id;
    
    @ManyToOne
    @JoinColumn(name = "batch_id", nullable = false)
    @Schema(description = "Batch associated with the temperature record")
    private YogurtBatch batch;
    
    @Column(nullable = false)
    @Schema(description = "Measured temperature in Celsius", example = "41.9")
    private Double temperature; // °C
    
    @Column(nullable = false)
    @Schema(description = "Date and time when the temperature was recorded", example = "2026-04-28T12:15:00")
    private LocalDateTime recordedAt;
    
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    @Schema(description = "Production stage associated with the record", example = "INCUBATION")
    private LogType type;
    
    @Schema(description = "Additional operator note attached to the temperature record", example = "Manual check after thermostat adjustment")
    private String notes;
    
    public enum LogType {
        HEATING, COOLING, INCUBATION, REFRIGERATION, MANUAL
    }
}
