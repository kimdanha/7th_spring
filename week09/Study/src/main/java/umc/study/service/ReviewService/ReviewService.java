package umc.study.service.ReviewService;

import umc.study.web.dto.ReviewRequestDTO;
import umc.study.web.dto.ReviewResponseDTO;

public interface ReviewService {

    /**
     * 리뷰 작성 메서드
     *
     * @param request  리뷰 작성 요청 DTO
     * @param memberId 작성자의 ID
     * @param shopId   가게의 ID
     * @return 저장된 리뷰의 ID와 생성 날짜를 반환
     */
    ReviewResponseDTO.JoinResultDTO joinReview(ReviewRequestDTO.JoinDto request, Long memberId, Long shopId);

    /**
     * 특정 가게의 리뷰 목록 조회 메서드
     *
     * @param storeId 가게의 ID
     * @param page    페이지 번호 (0부터 시작)
     * @return 가게의 리뷰 목록 및 페이징 정보
     */
    ReviewResponseDTO.ReviewPreViewListDTO getReviewList(Long storeId, Integer page);

    /**
     * 특정 사용자가 작성한 리뷰 목록 조회 메서드
     *
     * @param memberId 사용자 ID
     * @param page   페이지 번호 (0부터 시작)
     * @return 사용자가 작성한 리뷰 목록 및 페이징 정보
     */
    ReviewResponseDTO.ReviewPreViewListDTO getUserReviewList(Long memberId, Integer page);
}
