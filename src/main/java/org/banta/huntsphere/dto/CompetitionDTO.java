package org.banta.huntsphere.dto;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.banta.huntsphere.domain.enums.SpeciesType;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class CompetitionDTO {
    private UUID id;

    @NotBlank
    private String location;

    @Future
    private LocalDateTime date;

    @NotNull
    private SpeciesType speciesType;

    @Min(1)
    private Integer minParticipants;

    @Min(1)
    private Integer maxParticipants;
}