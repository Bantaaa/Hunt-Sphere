package org.banta.huntsphere.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;
import org.banta.huntsphere.domain.enums.Difficulty;
import org.banta.huntsphere.domain.enums.SpeciesType;

import java.util.UUID;

@Data
public class SpeciesDTO {
    private UUID id;

    @NotBlank
    private String name;

    @NotNull
    private SpeciesType category;

    @Positive
    private Double minimumWeight;

    @NotNull
    private Difficulty difficulty;

    @Positive
    private Integer points;
}
