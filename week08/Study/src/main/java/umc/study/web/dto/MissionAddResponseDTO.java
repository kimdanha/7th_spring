package umc.study.web.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
public class MissionAddResponseDTO {
    private Long missionId;       // 미션 ID
    private Long storeId;         // 가게 ID
    private Integer reward;       // 보상
    private LocalDate deadline;   // 마감 기한
    private String missionSpec;   // 미션 설명
}
