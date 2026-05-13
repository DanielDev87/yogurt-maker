package com.danieldev87.demo.dto;

import java.time.LocalDateTime;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(name = "ApiErrorResponse", description = "Standard error response returned when the request cannot be processed")
public class ApiErrorResponse {

	@Schema(description = "Date and time when the error was generated", example = "2026-04-28T10:15:30")
	private LocalDateTime timestamp;

	@Schema(description = "Human-readable error message", example = "Recipe not found")
	private String message;

	@Schema(description = "HTTP status code associated with the error", example = "404")
	private Integer status;
}