package umc.study.service.ReviewService;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import umc.study.converter.MemberConverter;
import umc.study.converter.ReviewConverter;
import umc.study.domain.Member;
import umc.study.domain.Review;
import umc.study.domain.ReviewImage;
import umc.study.domain.Store;
import umc.study.repository.MemberRepository.MemberRepository;
import umc.study.repository.ReviewRepository.ReviewRepository;
import umc.study.repository.StoreRepository.StoreRepository;
import umc.study.web.dto.ReviewRequestDTO;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService{

    private final ReviewRepository reviewRepository;
    private final MemberRepository memberRepository;
    private final StoreRepository storeRepository;

    @Override
    @Transactional
    public Review joinReview(ReviewRequestDTO. JoinDto request, Long memberId, Long shopId) {

        Member members = memberRepository.findById(memberId).orElse(null);
        Store store = storeRepository.findById(request.getShopId())
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 가게입니다."));

        List<ReviewImage> reviewImageList = new ArrayList<>();
        for(int i = 0; i < request.getImageList().size() ; i++) {
            ReviewImage reviewImage = ReviewConverter.toReviewImage(request.getImageList().get(i));
            reviewImageList.add(reviewImage);
        }
        Review review = Review.builder()
                .body(request.getBody())
                .score(request.getGrade())
                .member(members)
                .store(store)
                .reviewImageList(reviewImageList)
                .build();

        return reviewRepository.save(review);
    }
}
