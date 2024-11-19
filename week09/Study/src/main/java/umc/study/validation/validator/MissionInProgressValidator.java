package umc.study.validation.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import umc.study.domain.enums.MissionStatus;
import umc.study.repository.MemberMissionRepository.MemberMissionRepository;

import org.springframework.beans.factory.annotation.Autowired;
import umc.study.validation.annotation.MissionInProgress;

public class MissionInProgressValidator implements ConstraintValidator<MissionInProgress, Long> {

    @Autowired
    private MemberMissionRepository memberMissionRepository; // 도전 중인 미션 조회용 Repository

    @Override
    public boolean isValid(Long missionId, ConstraintValidatorContext context) {
        // missionId가 이미 도전 중인지 확인
        if (missionId == null) {
            return true; // missionId가 없으면 유효 (Optional 처리)
        }
        return !memberMissionRepository.existsByMissionIdAndStatus(missionId, MissionStatus.IN_PROGRESS);
    }
}
