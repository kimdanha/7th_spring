package umc.study.validation.annotation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import umc.study.validation.validator.StoreExistsValidator;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = StoreExistsValidator.class)
@Target({ElementType.FIELD, ElementType.PARAMETER}) // 필드와 메서드 매개변수에서 사용 가능
@Retention(RetentionPolicy.RUNTIME)
public @interface StoreExists {
    String message() default "해당 가게가 존재하지 않습니다.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}

