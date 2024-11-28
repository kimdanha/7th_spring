package umc.study.domain.enums;

public enum Gender {
    MALE, FEMALE, NONE;

    public static Gender fromString(String value) {
        try {
            return Gender.valueOf(value.toUpperCase());
        } catch (Exception e) {
            return NONE; // 기본값 설정
        }
    }
}
