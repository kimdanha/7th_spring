package umc.study.converter;

import umc.study.domain.Mission;
import umc.study.web.dto.MissionAddResponseDTO;

public class MissionAddConverter {
    public static MissionAddResponseDTO toMissionAddResponseDTO(Mission mission) {
        return new MissionAddResponseDTO(
                mission.getId(),
                mission.getStore().getId(),
                mission.getReward(),
                mission.getDeadline(),
                mission.getMissionSpec()
        );
    }
}
