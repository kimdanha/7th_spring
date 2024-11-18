package umc.study.web.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import umc.study.apiPayload.ApiResponse;
import umc.study.converter.ReviewConverter;
import umc.study.domain.Review;
import umc.study.service.ReviewService.ReviewService;
import umc.study.web.dto.ReviewRequestDTO;
import umc.study.web.dto.ReviewResponseDTO;

@RestController
@RequiredArgsConstructor
@RequestMapping("/review")
public class ReviewRestController {
    private final ReviewService reviewService;

    @PostMapping("{memberId}/{shopId}")
    public ApiResponse<ReviewResponseDTO.JoinResultDTO> join(@PathVariable("memberId") Long memberId, @PathVariable("shopId") Long shopId , @RequestBody @Valid ReviewRequestDTO.JoinDto request) {
        Review review = reviewService.joinReview(request, memberId, shopId);
        return ApiResponse.onSuccess(ReviewConverter.toJoinResultDTO(review));
    }

}
