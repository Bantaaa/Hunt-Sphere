package org.banta.huntsphere.dto;

import lombok.Data;

import java.util.UUID;

@Data
public class HuntDTO {
    private UUID id;
    private UUID speciesId;
    private UUID participationId;
    private Double weight;
}
