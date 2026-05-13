package com.danieldev87.demo.domain.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "ingredients")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(name = "Ingredient", description = "Ingredient entity associated with a yogurt recipe")
public class Ingredient {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "Unique ingredient identifier", example = "10")
    private Long id;
    
    @Column(nullable = false)
    @Schema(description = "Ingredient name", example = "Starter culture")
    private String name;
    
    @Schema(description = "Ingredient quantity required by the recipe", example = "3.0")
    private Double quantity;
    
    @Schema(description = "Measurement unit used for the ingredient quantity", example = "tablespoons")
    private String unit; // kg, g, ml, cucharadas, etc.
    
    @ManyToOne
    @JoinColumn(name = "recipe_id", nullable = false)
    @Schema(description = "Recipe to which the ingredient belongs")
    private Recipe recipe;
    
    @Schema(description = "Additional notes for handling the ingredient", example = "Add only when milk has cooled to inoculation temperature")
    private String notes;
    
    @Column(nullable = false)
    @Schema(description = "Indicates whether the ingredient is optional", example = "false")
    private Boolean optional;
}
