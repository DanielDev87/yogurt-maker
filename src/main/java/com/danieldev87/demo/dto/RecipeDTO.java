package com.danieldev87.demo.dto;

import java.util.List;

import com.danieldev87.demo.domain.model.Recipe;

import io.swagger.v3.oas.annotations.media.Schema;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(name = "RecipeDTO", description = "Payload used to create or update a yogurt recipe")
public class RecipeDTO {
    @Schema(description = "Recipe name displayed to operators", example = "Classic Natural Yogurt")
    private String name;
    @Schema(description = "Detailed explanation of the recipe and intended yogurt profile", example = "Traditional yogurt with smooth texture and mild acidity")
    private String description;
    @Schema(description = "Default milk volume used by the recipe in liters", example = "5.0")
    private Double defaultMilkVolume;
    @Schema(description = "Default starter culture amount in tablespoons", example = "3.0")
    private Double defaultStarterAmount;
    @Schema(description = "Target temperature for the heating stage in Celsius", example = "85.0")
    private Double heatingTemperature;
    @Schema(description = "Heating duration at target temperature in minutes", example = "30")
    private Integer heatingDuration;
    @Schema(description = "Temperature required before inoculating the starter culture in Celsius", example = "43.0")
    private Double inoculationTemperature;
    @Schema(description = "Target incubation temperature in Celsius", example = "42.0")
    private Double incubationTemperature;
    @Schema(description = "Minimum incubation time in hours", example = "6")
    private Integer minIncubationTime;
    @Schema(description = "Maximum incubation time in hours", example = "8")
    private Integer maxIncubationTime;
    @Schema(description = "Recommended refrigeration time in hours", example = "12")
    private Integer refrigerationTime;
    @Schema(description = "Difficulty level required to execute the recipe consistently", example = "BEGINNER")
    private Recipe.DifficultyLevel difficulty;
    @Schema(description = "Operational tips or quality notes for the recipe", example = "Do not stir vigorously after inoculation")
    private String tips;
    @Schema(description = "Ingredient list required by the recipe")
    private List<IngredientDTO> ingredients;
}