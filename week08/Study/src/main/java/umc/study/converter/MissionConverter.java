package umc.study.converter;

import umc.study.domain.Mission;
import umc.study.domain.enums.MissionStatus;
import umc.study.web.dto.MissionResponseDTO;

public class MissionConverter {
    public static MissionResponseDTO.JoinResultDTO toJoinResultDTO(Mission mission) {
        return MissionResponseDTO.JoinResultDTO.builder()
                .missionId(mission.getId())
                .status(MissionStatus.IN_PROGRESS)
                .build();

    }
}
