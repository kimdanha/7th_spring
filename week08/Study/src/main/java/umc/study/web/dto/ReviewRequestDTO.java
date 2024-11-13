package umc.study.web.dto;

import lombok.Getter;

import java.util.List;

public class ReviewRequestDTO {

    @Getter
    public static class JoinDto{
        String body;
        Float grade;
        List<String> imageList;
    }
}
