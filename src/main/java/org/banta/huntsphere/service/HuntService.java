package org.banta.huntsphere.service;

import org.banta.huntsphere.dto.HuntDTO;

import java.util.List;
import java.util.UUID;

public interface HuntService {
    HuntDTO recordHunt(HuntDTO huntDTO);
    List<HuntDTO> getParticipationHunts(UUID participationId);
}
