package umc.study.web.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import umc.study.validation.annotation.MissionExists;
import umc.study.validation.annotation.MissionInProgress;

@Getter
@NoArgsConstructor
public class MissionRequestDTO {

    public static class JoinDto {

        @NotNull(message = "Mission ID is required.")
        @MissionInProgress // missionId가 실제로 존재하는지 검증
        private Long missionId;

        public JoinDto(Long missionId) {
            this.missionId = missionId;
        }
    }

}
