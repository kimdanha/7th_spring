// ReviewRequestDTO.java
package umc.study.web.dto;

import lombok.Getter;
import umc.study.validation.annotation.StoreExists;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.util.List;

public class ReviewRequestDTO {

    @Getter
    public static class JoinDto {
        @NotNull(message = "리뷰 내용은 필수입니다.")
        private String body;

        @NotNull(message = "평점은 필수입니다.")
        private Float grade;

        @StoreExists(message = "존재하지 않는 가게입니다.")
        private Long shopId;

        @NotNull(message = "회원 ID는 필수입니다.")
        private Long memberId;

        @Size(max = 5, message = "이미지 리스트는 최대 5개까지 가능합니다.")
        private List<String> imageList;
    }
}
