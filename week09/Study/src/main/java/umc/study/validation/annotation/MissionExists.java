package umc.study.validation.annotation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import umc.study.validation.validator.MissionExistsValidator;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = MissionExistsValidator.class) // Validator와 연결
@Target({ElementType.FIELD, ElementType.PARAMETER}) // 필드나 매개변수에서 사용 가능
@Retention(RetentionPolicy.RUNTIME) // 런타임에 유지
public @interface MissionExists {
    String message() default "The mission ID does not exist.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
