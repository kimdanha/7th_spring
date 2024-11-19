package umc.study.service.ReviewService;

import umc.study.web.dto.ReviewRequestDTO;
import umc.study.web.dto.ReviewResponseDTO;

public interface ReviewService {
    ReviewResponseDTO.JoinResultDTO joinReview(ReviewRequestDTO.JoinDto request, Long memberId, Long shopId);

    ReviewResponseDTO.ReviewPreViewListDTO getReviewList(Long storeId, Integer page);
}
