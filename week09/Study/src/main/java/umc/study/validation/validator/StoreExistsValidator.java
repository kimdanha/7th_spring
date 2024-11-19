package umc.study.validation.validator;


import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import umc.study.repository.StoreRepository.StoreRepository;
import umc.study.validation.annotation.StoreExists;

@Component
public class StoreExistsValidator implements ConstraintValidator<StoreExists, Long> {

    @Autowired
    private StoreRepository storeRepository;

    @Override
    public boolean isValid(Long storeId, ConstraintValidatorContext context) {
        if (storeId == null) {
            return false;
        }
        return storeRepository.existsById(storeId);
    }
}

