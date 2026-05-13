package com.danieldev87.demo.dto;

import java.util.Map;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

public class MonitoringDTO {
    
    @Data
    @Builder
    @Schema(name = "TemperatureSummary", description = "Aggregated temperature indicators for a yogurt batch")
    public static class TemperatureSummary {
        @Schema(description = "Latest temperature recorded for the batch in Celsius", example = "42.1")
        private Double currentTemperature;
        @Schema(description = "Highest temperature recorded for the batch in Celsius", example = "85.0")
        private Double maximumTemperature;
        @Schema(description = "Lowest temperature recorded for the batch in Celsius", example = "4.5")
        private Double minimumTemperature;
        @Schema(description = "Average incubation temperature recorded for the batch in Celsius", example = "41.8")
        private Double averageTemperature;
    }
    
    @Data
    @Builder
    @Schema(name = "Dashboard", description = "Operational dashboard summary for yogurt production")
    public static class Dashboard {
        @Schema(description = "Batch counts grouped by status")
        private Map<String, Long> batchCounts;
        @Schema(description = "Total number of batches currently in active processing stages", example = "4")
        private Long activeBatchesCount;
        @Schema(description = "Number of batches completed during the current day", example = "7")
        private Integer completedToday;
    }
}
