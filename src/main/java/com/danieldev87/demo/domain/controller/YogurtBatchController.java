package com.danieldev87.demo.domain.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.danieldev87.demo.domain.model.YogurtBatch;
import com.danieldev87.demo.domain.service.YogurtMakingService;
import com.danieldev87.demo.dto.ApiErrorResponse;
import com.danieldev87.demo.dto.BatchDTO;
import com.danieldev87.demo.dto.TemperatureRecordDTO;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/batches")
@RequiredArgsConstructor
@Tag(name = "Batches", description = "Endpoints for creating batches and advancing the yogurt production lifecycle")
public class YogurtBatchController {
    
    private final YogurtMakingService yogurtMakingService;
    
    @PostMapping
    @Operation(summary = "Start new batch", description = "Creates a new yogurt production batch from a recipe and optional custom quantities.")
    @ApiResponses({
        @ApiResponse(responseCode = "201", description = "Batch created successfully", content = @Content(schema = @Schema(implementation = YogurtBatch.class))),
        @ApiResponse(responseCode = "400", description = "Invalid batch configuration or recipe unavailable", content = @Content(schema = @Schema(implementation = ApiErrorResponse.class)))
    })
    public ResponseEntity<YogurtBatch> startNewBatch(@RequestBody BatchDTO.StartBatchRequest request) {
        YogurtBatch batch = yogurtMakingService.startNewBatch(
            request.getRecipeId(), 
            request.getCustomMilkVolume(), 
            request.getCustomStarterAmount()
        );
        return new ResponseEntity<>(batch, HttpStatus.CREATED);
    }
    
    @PostMapping("/{batchId}/heating")
    @Operation(summary = "Start heating phase", description = "Moves a batch to the heating phase and updates its target processing state.")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Batch moved to heating successfully", content = @Content(schema = @Schema(implementation = YogurtBatch.class))),
        @ApiResponse(responseCode = "404", description = "Batch not found", content = @Content(schema = @Schema(implementation = ApiErrorResponse.class)))
    })
    public ResponseEntity<YogurtBatch> startHeating(@PathVariable Long batchId) {
        YogurtBatch batch = yogurtMakingService.startHeating(batchId);
        return ResponseEntity.ok(batch);
    }
    
    @PostMapping("/{batchId}/inoculating")
    @Operation(summary = "Start inoculation phase", description = "Marks the batch as ready for adding starter culture at the correct temperature.")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Batch moved to inoculation successfully", content = @Content(schema = @Schema(implementation = YogurtBatch.class))),
        @ApiResponse(responseCode = "404", description = "Batch not found", content = @Content(schema = @Schema(implementation = ApiErrorResponse.class)))
    })
    public ResponseEntity<YogurtBatch> startInoculating(@PathVariable Long batchId) {
        YogurtBatch batch = yogurtMakingService.startInoculating(batchId);
        return ResponseEntity.ok(batch);
    }
    
    @PostMapping("/{batchId}/incubation")
    @Operation(summary = "Start incubation phase", description = "Starts the incubation period where the batch ferments under controlled temperature.")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Batch moved to incubation successfully", content = @Content(schema = @Schema(implementation = YogurtBatch.class))),
        @ApiResponse(responseCode = "404", description = "Batch not found", content = @Content(schema = @Schema(implementation = ApiErrorResponse.class)))
    })
    public ResponseEntity<YogurtBatch> startIncubation(@PathVariable Long batchId) {
        YogurtBatch batch = yogurtMakingService.startIncubation(batchId);
        return ResponseEntity.ok(batch);
    }
    
    @PostMapping("/{batchId}/refrigeration")
    @Operation(summary = "Start refrigeration phase", description = "Moves the batch into refrigeration to stabilize the yogurt after incubation.")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Batch moved to refrigeration successfully", content = @Content(schema = @Schema(implementation = YogurtBatch.class))),
        @ApiResponse(responseCode = "404", description = "Batch not found", content = @Content(schema = @Schema(implementation = ApiErrorResponse.class)))
    })
    public ResponseEntity<YogurtBatch> startRefrigeration(@PathVariable Long batchId) {
        YogurtBatch batch = yogurtMakingService.startRefrigeration(batchId);
        return ResponseEntity.ok(batch);
    }
    
    @PostMapping("/{batchId}/complete")
    @Operation(summary = "Complete batch", description = "Marks a yogurt batch as completed when the production process finishes successfully.")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Batch completed successfully", content = @Content(schema = @Schema(implementation = YogurtBatch.class))),
        @ApiResponse(responseCode = "404", description = "Batch not found", content = @Content(schema = @Schema(implementation = ApiErrorResponse.class)))
    })
    public ResponseEntity<YogurtBatch> completeBatch(@PathVariable Long batchId) {
        YogurtBatch batch = yogurtMakingService.completeBatch(batchId);
        return ResponseEntity.ok(batch);
    }
    
    @PostMapping("/{batchId}/fail")
    @Operation(summary = "Fail batch", description = "Marks a batch as failed and stores the reason that prevented successful completion.")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Batch marked as failed successfully", content = @Content(schema = @Schema(implementation = YogurtBatch.class))),
        @ApiResponse(responseCode = "400", description = "Failure reason is invalid or batch cannot be failed", content = @Content(schema = @Schema(implementation = ApiErrorResponse.class)))
    })
    public ResponseEntity<YogurtBatch> markAsFailed(
            @PathVariable Long batchId, 
            @RequestBody BatchDTO.FailRequest request) {
        YogurtBatch batch = yogurtMakingService.markAsFailed(batchId, request.getReason());
        return ResponseEntity.ok(batch);
    }
    
    @GetMapping
    @Operation(summary = "List batches", description = "Returns all yogurt batches or filters them by a specific production status.")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Batch list retrieved successfully", content = @Content(array = @ArraySchema(schema = @Schema(implementation = YogurtBatch.class)))),
        @ApiResponse(responseCode = "400", description = "Invalid batch status filter", content = @Content(schema = @Schema(implementation = ApiErrorResponse.class)))
    })
    public ResponseEntity<List<YogurtBatch>> getAllBatches(
            @RequestParam(required = false) YogurtBatch.BatchStatus status) {
        if (status != null) {
            return ResponseEntity.ok(yogurtMakingService.getBatchesByStatus(status));
        }
        return ResponseEntity.ok(yogurtMakingService.getAllBatches());
    }
    
    @GetMapping("/{batchId}")
    @Operation(summary = "Get batch by ID", description = "Returns the full detail of a yogurt batch, including current status and recipe reference.")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Batch retrieved successfully", content = @Content(schema = @Schema(implementation = YogurtBatch.class))),
        @ApiResponse(responseCode = "404", description = "Batch not found", content = @Content(schema = @Schema(implementation = ApiErrorResponse.class)))
    })
    public ResponseEntity<YogurtBatch> getBatch(@PathVariable Long batchId) {
        YogurtBatch batch = yogurtMakingService.getBatch(batchId);
        return ResponseEntity.ok(batch);
    }
    
    @PostMapping("/{batchId}/temperature")
    @Operation(summary = "Record temperature", description = "Stores a temperature reading for the batch in a specific process phase.")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Temperature recorded successfully"),
        @ApiResponse(responseCode = "400", description = "Temperature data is invalid or batch cannot accept readings", content = @Content(schema = @Schema(implementation = ApiErrorResponse.class)))
    })
    public ResponseEntity<Void> recordTemperature(
            @PathVariable Long batchId, 
            @RequestBody TemperatureRecordDTO request) {
        yogurtMakingService.recordTemperature(batchId, request.getTemperature(), request.getType());
        return ResponseEntity.ok().build();
    }
}
