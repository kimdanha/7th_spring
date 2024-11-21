package umc.study.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import umc.study.apiPayload.ApiResponse;
import umc.study.converter.ReviewConverter;
import umc.study.domain.Review;
import umc.study.service.ReviewService.ReviewService;
import umc.study.validation.annotation.CheckPage;
import umc.study.validation.annotation.StoreExists;
import umc.study.web.dto.ReviewRequestDTO;
import umc.study.web.dto.ReviewResponseDTO;

@RestController
@RequiredArgsConstructor
@RequestMapping("/review")
public class ReviewRestController {
    private final ReviewService reviewService;

    @PostMapping("{memberId}/{shopId}")
    public ApiResponse<ReviewResponseDTO.JoinResultDTO> join(
            @PathVariable("memberId") Long memberId,
            @PathVariable("shopId") Long shopId,
            @RequestBody @Valid ReviewRequestDTO.JoinDto request) {
        ReviewResponseDTO.JoinResultDTO joinResultDTO = reviewService.joinReview(request, memberId, shopId);
        return ApiResponse.onSuccess(joinResultDTO);
    }

    // 특정 사용자의 리뷰 조회
    @GetMapping("/{memberId}/member-reviews")
    public ApiResponse<ReviewResponseDTO.ReviewPreViewListDTO> getUserReviews(
            @PathVariable("memberId") Long memberId,  // 파라미터 이름 명시
            @RequestParam("page") Integer page) {    // 파라미터 이름 명시
        ReviewResponseDTO.ReviewPreViewListDTO userReviewList = reviewService.getUserReviewList(memberId, page);
        return ApiResponse.onSuccess(userReviewList);
    }

    // 특정 가게의 리뷰 조회
    @GetMapping("/{storeId}/reviews")
    public ApiResponse<ReviewResponseDTO.ReviewPreViewListDTO> getStoreReviews(
            @PathVariable("storeId") Long storeId,  // 파라미터 이름 명시
            @RequestParam("page") Integer page) {   // 파라미터 이름 명시
        ReviewResponseDTO.ReviewPreViewListDTO reviewList = reviewService.getReviewList(storeId, page);
        return ApiResponse.onSuccess(reviewList);
    }
}