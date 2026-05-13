package com.danieldev87.demo.dto;

import com.danieldev87.demo.domain.model.TemperatureLog;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(name = "TemperatureRecordDTO", description = "Payload used to record a temperature reading for a batch")
public class TemperatureRecordDTO {
    @Schema(description = "Temperature value measured in Celsius", example = "42.5")
    private Double temperature;
    @Schema(description = "Process stage associated with the temperature reading", example = "INCUBATION")
    private TemperatureLog.LogType type;
}

