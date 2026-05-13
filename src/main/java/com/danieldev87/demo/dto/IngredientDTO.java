package com.danieldev87.demo.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(name = "IngredientDTO", description = "Ingredient definition included in a yogurt recipe")
public class IngredientDTO {
    @Schema(description = "Ingredient name shown in the recipe", example = "Whole milk")
    private String name;
    @Schema(description = "Ingredient quantity required by the recipe", example = "5.0")
    private Double quantity;
    @Schema(description = "Unit used for the ingredient quantity", example = "liters")
    private String unit;
    @Schema(description = "Additional preparation or handling notes for the ingredient", example = "Use pasteurized milk only")
    private String notes;
    @Schema(description = "Indicates whether the ingredient is optional in the recipe", example = "false")
    private Boolean optional;
}
