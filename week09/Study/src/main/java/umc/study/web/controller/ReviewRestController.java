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
import umc.study.validation.annotation.StoreExists;
import umc.study.web.dto.ReviewRequestDTO;
import umc.study.web.dto.ReviewResponseDTO;

@RestController
@RequiredArgsConstructor
@RequestMapping("/review")
public class ReviewRestController {
    private final ReviewService reviewService;

    @PostMapping("{memberId}/{shopId}")
    public ApiResponse<ReviewResponseDTO.JoinResultDTO> join(@PathVariable("memberId") Long memberId,
                                                             @PathVariable("shopId") Long shopId,
                                                             @RequestBody @Valid ReviewRequestDTO.JoinDto request) {
        ReviewResponseDTO.JoinResultDTO joinResultDTO = reviewService.joinReview(request, memberId, shopId);
        return ApiResponse.onSuccess(joinResultDTO);
    }


    @GetMapping("/{storeId}/reviews")
    @Operation(summary = "특정 가게의 리뷰 목록 조회 API", description = "특정 가게의 리뷰 목록을 조회하는 API입니다. 페이징 처리를 위해 쿼리 파라미터로 페이지 번호를 입력하세요.")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200", description = "OK, 성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH003", description = "Access 토큰이 필요합니다.", content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH004", description = "Access 토큰 만료", content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH006", description = "Access 토큰 형식이 잘못되었습니다.", content = @Content(schema = @Schema(implementation = ApiResponse.class))),
    })
    @Parameters({
            @Parameter(name = "storeId", description = "가게의 ID (Path Variable)"),
            @Parameter(name = "page", description = "페이지 번호 (Query Parameter)")
    })
    public ApiResponse<ReviewResponseDTO.ReviewPreViewListDTO> getReviewList(@StoreExists @PathVariable(name = "storeId") Long storeId,
                                                                             @RequestParam(name = "page") Integer page) {
        ReviewResponseDTO.ReviewPreViewListDTO reviewListDTO = reviewService.getReviewList(storeId, page);
        return ApiResponse.onSuccess(reviewListDTO);
    }
}
