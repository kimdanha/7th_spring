package umc.study.validation.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import umc.study.repository.MissionRepository.MissionRepository;
import umc.study.validation.annotation.MissionExists;

@Component
public class MissionExistsValidator implements ConstraintValidator<MissionExists, Long> {

    @Autowired
    private MissionRepository missionRepository;

    @Override
    public boolean isValid(Long missionId, ConstraintValidatorContext context) {
        if (missionId == null) {
            return false; // null 값일 경우 유효하지 않음
        }
        return missionRepository.existsById(missionId); // 데이터베이스에 존재하는지 확인
    }
}
