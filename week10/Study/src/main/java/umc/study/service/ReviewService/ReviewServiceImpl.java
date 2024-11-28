package umc.study.service.ReviewService;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import umc.study.converter.ReviewConverter;
import umc.study.domain.Member;
import umc.study.domain.Review;
import umc.study.domain.ReviewImage;
import umc.study.domain.Store;
import umc.study.repository.MemberRepository.MemberRepository;
import umc.study.repository.ReviewRepository.ReviewRepository;
import umc.study.repository.StoreRepository.StoreRepository;
import umc.study.web.dto.ReviewRequestDTO;
import umc.study.web.dto.ReviewResponseDTO;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService {

    private final ReviewRepository reviewRepository;
    private final MemberRepository memberRepository;
    private final StoreRepository storeRepository;

    @Override
    @Transactional
    public ReviewResponseDTO.JoinResultDTO joinReview(ReviewRequestDTO.JoinDto request, Long memberId, Long shopId) {
        // 멤버 확인
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 사용자입니다."));

        // 가게 확인
        Store store = storeRepository.findById(shopId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 가게입니다."));

        // 리뷰 이미지 리스트 변환
        List<ReviewImage> reviewImageList = request.getImageList().stream()
                .map(ReviewConverter::toReviewImage)
                .collect(Collectors.toList());

        // 리뷰 생성
        Review review = Review.builder()
                .body(request.getBody())
                .score(request.getGrade())
                .member(member)
                .store(store)
                .reviewImageList(reviewImageList)
                .build();

        // 저장
        Review savedReview = reviewRepository.save(review);

        // 결과 반환
        return ReviewResponseDTO.JoinResultDTO.builder()
                .reviewId(savedReview.getId())
                .createdAt(savedReview.getCreatedAt())
                .build();
    }

    @Override
    @Transactional
    public ReviewResponseDTO.ReviewPreViewListDTO getReviewList(Long storeId, Integer page) {
        Pageable pageable = PageRequest.of(page, 10);

        // 데이터 조회
        Page<Review> reviewPage = reviewRepository.findByStoreId(storeId, pageable);

        // DTO 변환
        List<ReviewResponseDTO.ReviewPreViewDTO> reviewList = reviewPage.getContent().stream()
                .map(review -> ReviewResponseDTO.ReviewPreViewDTO.builder()
                        .ownerNickname(review.getMember().getName())
                        .score(review.getScore())
                        .body(review.getBody())
                        .createdAt(review.getCreatedAt().toLocalDate())
                        .build())
                .collect(Collectors.toList());

        // 결과 반환
        return ReviewResponseDTO.ReviewPreViewListDTO.builder()
                .reviewList(reviewList)
                .listSize(reviewList.size())
                .totalPage(reviewPage.getTotalPages())
                .totalElements(reviewPage.getTotalElements())
                .isFirst(reviewPage.isFirst())
                .isLast(reviewPage.isLast())
                .build();
    }
}
