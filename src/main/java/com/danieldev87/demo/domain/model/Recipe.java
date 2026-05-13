package com.danieldev87.demo.domain.model;

import java.util.ArrayList;
import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "recipes")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(name = "Recipe", description = "Yogurt recipe entity with process settings, ingredients and operational guidance")
public class Recipe {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "Unique recipe identifier", example = "1")
    private Long id;
    
    @Column(nullable = false, unique = true)
    @Schema(description = "Unique recipe name", example = "Classic Natural Yogurt")
    private String name;
    
    @Schema(description = "Detailed recipe description", example = "Recipe for a smooth natural yogurt with balanced acidity")
    private String description;
    
    @OneToMany(mappedBy = "recipe", cascade = CascadeType.ALL)
    @Builder.Default
    @Schema(description = "Ingredients associated with the recipe")
    private List<Ingredient> ingredients = new ArrayList<>();
    
    @Column(nullable = false)
    @Schema(description = "Default milk volume in liters", example = "5.0")
    private Double defaultMilkVolume; // litros
    
    @Column(nullable = false)
    @Schema(description = "Default starter culture amount in tablespoons", example = "3.0")
    private Double defaultStarterAmount; // cucharadas
    
    @Column(nullable = false)
    @Schema(description = "Heating temperature in Celsius", example = "85.0")
    private Double heatingTemperature; // °C
    
    @Column(nullable = false)
    @Schema(description = "Heating duration in minutes", example = "30")
    private Integer heatingDuration; // minutos a temperatura objetivo
    
    @Column(nullable = false)
    @Schema(description = "Inoculation temperature in Celsius", example = "43.0")
    private Double inoculationTemperature; // °C
    
    @Column(nullable = false)
    @Schema(description = "Incubation temperature in Celsius", example = "42.0")
    private Double incubationTemperature; // °C
    
    @Column(nullable = false)
    @Schema(description = "Minimum incubation time in hours", example = "6")
    private Integer minIncubationTime; // horas
    
    @Column(nullable = false)
    @Schema(description = "Maximum incubation time in hours", example = "8")
    private Integer maxIncubationTime; // horas
    
    @Column(nullable = false)
    @Schema(description = "Refrigeration time in hours", example = "12")
    private Integer refrigerationTime; // horas
    
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    @Schema(description = "Difficulty level required to execute the recipe", example = "BEGINNER")
    private DifficultyLevel difficulty;
    
    @Schema(description = "Operational tips for better yogurt quality", example = "Avoid abrupt temperature changes during cooling")
    private String tips;
    
    @Column(nullable = false)
    @Schema(description = "Indicates whether the recipe is currently active", example = "true")
    private Boolean active;
    
    public enum DifficultyLevel {
        BEGINNER, INTERMEDIATE, ADVANCED
    }
}

