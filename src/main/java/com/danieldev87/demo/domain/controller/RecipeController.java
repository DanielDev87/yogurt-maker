package com.danieldev87.demo.domain.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.danieldev87.demo.domain.model.Recipe;
import com.danieldev87.demo.domain.service.RecipeService;
import com.danieldev87.demo.dto.ApiErrorResponse;
import com.danieldev87.demo.dto.RecipeDTO;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/recipes")
@RequiredArgsConstructor
@Tag(name = "Recipes", description = "Endpoints for creating, updating, querying and enabling yogurt recipes")
public class RecipeController {
    
    private final RecipeService recipeService;
    
    @PostMapping
    @Operation(summary = "Create recipe", description = "Creates a new yogurt recipe with ingredient, temperature and timing settings.")
    @ApiResponses({
        @ApiResponse(responseCode = "201", description = "Recipe created successfully", content = @Content(schema = @Schema(implementation = Recipe.class))),
        @ApiResponse(responseCode = "400", description = "Invalid recipe data or duplicate recipe name", content = @Content(schema = @Schema(implementation = ApiErrorResponse.class)))
    })
    public ResponseEntity<Recipe> createRecipe(@RequestBody RecipeDTO recipeDTO) {
        Recipe recipe = recipeService.createRecipe(recipeDTO);
        return new ResponseEntity<>(recipe, HttpStatus.CREATED);
    }
    
    @PutMapping("/{id}")
    @Operation(summary = "Update recipe", description = "Updates the configuration and ingredient list of an existing yogurt recipe.")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Recipe updated successfully", content = @Content(schema = @Schema(implementation = Recipe.class))),
        @ApiResponse(responseCode = "404", description = "Recipe not found", content = @Content(schema = @Schema(implementation = ApiErrorResponse.class)))
    })
    public ResponseEntity<Recipe> updateRecipe(@PathVariable Long id, @RequestBody RecipeDTO recipeDTO) {
        Recipe recipe = recipeService.updateRecipe(id, recipeDTO);
        return ResponseEntity.ok(recipe);
    }
    
    @GetMapping("/{id}")
    @Operation(summary = "Get recipe by ID", description = "Returns the detail of a single recipe identified by its unique ID.")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Recipe retrieved successfully", content = @Content(schema = @Schema(implementation = Recipe.class))),
        @ApiResponse(responseCode = "404", description = "Recipe not found", content = @Content(schema = @Schema(implementation = ApiErrorResponse.class)))
    })
    public ResponseEntity<Recipe> getRecipe(@PathVariable Long id) {
        Recipe recipe = recipeService.getRecipe(id);
        return ResponseEntity.ok(recipe);
    }
    
    @GetMapping
    @Operation(summary = "List active recipes", description = "Returns all active recipes available for starting new yogurt batches.")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Recipe list retrieved successfully", content = @Content(array = @ArraySchema(schema = @Schema(implementation = Recipe.class)))),
        @ApiResponse(responseCode = "400", description = "Unable to list recipes due to invalid request state", content = @Content(schema = @Schema(implementation = ApiErrorResponse.class)))
    })
    public ResponseEntity<List<Recipe>> getAllRecipes() {
        return ResponseEntity.ok(recipeService.getAllActiveRecipes());
    }
    
    @GetMapping("/search")
    @Operation(summary = "Search recipes", description = "Searches active recipes by keyword against their name or descriptive information.")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Matching recipes retrieved successfully", content = @Content(array = @ArraySchema(schema = @Schema(implementation = Recipe.class)))),
        @ApiResponse(responseCode = "400", description = "Search keyword is invalid or empty", content = @Content(schema = @Schema(implementation = ApiErrorResponse.class)))
    })
    public ResponseEntity<List<Recipe>> searchRecipes(@RequestParam String keyword) {
        return ResponseEntity.ok(recipeService.searchRecipes(keyword));
    }
    
    @PatchMapping("/{id}/deactivate")
    @Operation(summary = "Deactivate recipe", description = "Marks a recipe as inactive so it can no longer be used to start new batches.")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Recipe deactivated successfully"),
        @ApiResponse(responseCode = "404", description = "Recipe not found", content = @Content(schema = @Schema(implementation = ApiErrorResponse.class)))
    })
    public ResponseEntity<Void> deactivateRecipe(@PathVariable Long id) {
        recipeService.deactivateRecipe(id);
        return ResponseEntity.ok().build();
    }
    
    @PatchMapping("/{id}/activate")
    @Operation(summary = "Activate recipe", description = "Reactivates a recipe so it becomes available again for future yogurt batches.")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Recipe activated successfully"),
        @ApiResponse(responseCode = "404", description = "Recipe not found", content = @Content(schema = @Schema(implementation = ApiErrorResponse.class)))
    })
    public ResponseEntity<Void> activateRecipe(@PathVariable Long id) {
        recipeService.activateRecipe(id);
        return ResponseEntity.ok().build();
    }
}
