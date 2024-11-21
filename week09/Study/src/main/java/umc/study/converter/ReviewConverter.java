package umc.study.converter;


import org.springframework.data.domain.Page;
import umc.study.domain.Member;
import umc.study.domain.Review;
import umc.study.domain.ReviewImage;
import umc.study.web.dto.ReviewRequestDTO;
import umc.study.web.dto.ReviewResponseDTO;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class ReviewConverter {
    public static ReviewResponseDTO.JoinResultDTO toJoinResultDTO(Review review) {
        return ReviewResponseDTO.JoinResultDTO.builder()
                .reviewId(review.getId())
                .createdAt(LocalDateTime.now())
                .build();
    }


    public static ReviewImage toReviewImage(String url) {
        ReviewImage reviewImage = ReviewImage.builder()
                .imageUrl(url)
                .build();
        return reviewImage;
    }

    // Review 객체를 ReviewPreViewDTO로 변환
    public static ReviewResponseDTO.ReviewPreViewDTO reviewPreViewDTO(Review review) {
        return ReviewResponseDTO.ReviewPreViewDTO.builder()
                .ownerNickname(review.getMember().getName()) // 리뷰 작성자 이름
                .score(review.getScore())                   // 점수
                .body(review.getBody())                     // 리뷰 내용
                .createdAt(review.getCreatedAt().toLocalDate()) // 작성 날짜 (LocalDate로 변환)
                .build();
    }

    // Page<Review>를 ReviewPreViewListDTO로 변환
    public static ReviewResponseDTO.ReviewPreViewListDTO reviewPreViewListDTO(Page<Review> reviewPage) {
        // Review 리스트를 ReviewPreViewDTO 리스트로 변환
        List<ReviewResponseDTO.ReviewPreViewDTO> reviewPreViewDTOList = reviewPage.stream()
                .map(ReviewConverter::reviewPreViewDTO)
                .collect(Collectors.toList());

        return ReviewResponseDTO.ReviewPreViewListDTO.builder()
                .reviewList(reviewPreViewDTOList)            // 변환된 Review DTO 리스트
                .listSize(reviewPreViewDTOList.size())       // 현재 페이지의 리뷰 수
                .totalPage(reviewPage.getTotalPages())       // 전체 페이지 수
                .totalElements(reviewPage.getTotalElements()) // 전체 리뷰 수
                .isFirst(reviewPage.isFirst())               // 첫 페이지 여부
                .isLast(reviewPage.isLast())                 // 마지막 페이지 여부
                .build();
    }
}
