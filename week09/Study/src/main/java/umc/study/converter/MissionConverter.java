package umc.study.converter;

import org.springframework.data.domain.Page;
import umc.study.domain.Mission;
import umc.study.domain.enums.MissionStatus;
import umc.study.web.dto.MissionResponseDTO;

import java.util.List;
import java.util.stream.Collectors;

public class MissionConverter {
    public static MissionResponseDTO.JoinResultDTO toJoinResultDTO(Mission mission) {
        return MissionResponseDTO.JoinResultDTO.builder()
                .missionId(mission.getId())
                .status(MissionStatus.IN_PROGRESS)
                .build();

    }

    public static MissionResponseDTO.MissionPreViewDTO toMissionPreViewDTO(Mission mission) {
        return MissionResponseDTO.MissionPreViewDTO.builder()
                .missionName(mission.getName())
                .reward(mission.getReward())
                .deadline(mission.getDeadline())
                .missionSpec(mission.getMissionSpec())
                .createdAt(mission.getCreatedAt())
                .build();
    }


    public static MissionResponseDTO.MissionPreViewListDTO toMissionPreViewListDTO(Page<Mission> missionPage) {
        // Page<Mission> 데이터를 MissionPreViewDTO로 변환
        List<MissionResponseDTO.MissionPreViewDTO> missionList = missionPage.stream()
                .map(MissionConverter::toMissionPreViewDTO)
                .collect(Collectors.toList());

        // MissionPreViewListDTO 빌드
        return MissionResponseDTO.MissionPreViewListDTO.builder()
                .misisonList(missionList) // 필드 타입이 수정되었음
                .listSize(missionList.size())
                .totalPage(missionPage.getTotalPages())
                .totalElements(missionPage.getTotalElements())
                .isFirst(missionPage.isFirst())
                .isLast(missionPage.isLast())
                .build();
    }

}
