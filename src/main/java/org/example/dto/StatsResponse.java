package org.example.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import java.io.Serializable;

@AllArgsConstructor
@Getter
@Setter
public class StatsResponse implements Serializable {

    @JsonProperty("count_mutant_dna")
    private long countMutantDna;

    @JsonProperty("count_human_dna")
    private long countHumanDna;

    private double ratio;
}