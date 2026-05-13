package com.danieldev87.demo.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

public class BatchDTO {
    
    @Data
    @Schema(name = "StartBatchRequest", description = "Payload used to create a new yogurt batch from a recipe")
    public static class StartBatchRequest {
        @Schema(description = "Unique identifier of the recipe used to start the batch", example = "1")
        private Long recipeId;
        @Schema(description = "Optional custom milk volume in liters; if omitted the recipe default is used", example = "10.0")
        private Double customMilkVolume;
        @Schema(description = "Optional custom starter amount in tablespoons; if omitted the recipe default is used", example = "4.0")
        private Double customStarterAmount;
    }
    
    @Data
    @Schema(name = "FailBatchRequest", description = "Payload used to mark a yogurt batch as failed")
    public static class FailRequest {
        @Schema(description = "Reason explaining why the batch failed", example = "Incubation temperature dropped below the safe threshold")
        private String reason;
    }
}
