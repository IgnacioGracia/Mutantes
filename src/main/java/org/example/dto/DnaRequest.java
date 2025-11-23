package org.example.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.example.validation.ValidDnaSequence;

import java.io.Serializable;

@Getter
@Setter
public class DnaRequest implements Serializable {

    @Schema(
            description = "Secuencia de ADN (Matriz NxN)",
            example = "[\"ATGCGA\",\"CAGTGC\",\"TTATGT\",\"AGAAGG\",\"CCCCTA\",\"TCACTG\"]"
    )
    @NotNull
    @ValidDnaSequence
    private String[] dna;
}