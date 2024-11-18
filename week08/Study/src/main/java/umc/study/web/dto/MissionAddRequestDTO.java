package umc.study.web.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@NoArgsConstructor
public class MissionAddRequestDTO {

    @NotNull(message = "Reward is required.")
    private Integer reward;

    @NotNull(message = "Deadline is required.")
    private LocalDate deadline;

    @NotNull(message = "Mission specification is required.")
    private String missionSpec;

    public MissionAddRequestDTO(Integer reward, LocalDate deadline, String missionSpec) {
        this.reward = reward;
        this.deadline = deadline;
        this.missionSpec = missionSpec;
    }
}

