package org.example.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.dto.DnaRequest;
import org.example.dto.StatsResponse;
import org.example.service.MutantService;
import org.example.service.StatsService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping
@RequiredArgsConstructor
@Tag(name = "Mutant Detector", description = "API para detección de mutantes")
public class MutantController {

    private final MutantService mutantService;
    private final StatsService statsService;

    @Operation(summary = "Detectar si un humano es mutante", description = "Analiza la secuencia de ADN enviada y determina si pertenece a un mutante.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Es Mutante", content = @Content),
            @ApiResponse(responseCode = "403", description = "Es Humano (No Mutante)", content = @Content),
            @ApiResponse(responseCode = "400", description = "ADN inválido (formato incorrecto o caracteres no válidos)", content = @Content)
    })
    @PostMapping("/mutant")
    public ResponseEntity<Void> checkMutant(@Valid @RequestBody DnaRequest dnaRequest) {
        boolean isMutant = mutantService.analyzeDna(dnaRequest.getDna());
        if (isMutant) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
    }

    @Operation(summary = "Obtener estadísticas de verificaciones", description = "Devuelve el conteo de mutantes, humanos y el ratio de mutantes.")
    @ApiResponse(responseCode = "200", description = "Estadísticas obtenidas exitosamente",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = StatsResponse.class)))
    @GetMapping("/stats")
    public ResponseEntity<StatsResponse> getStats() {
        return ResponseEntity.ok(statsService.getStats());
    }
}