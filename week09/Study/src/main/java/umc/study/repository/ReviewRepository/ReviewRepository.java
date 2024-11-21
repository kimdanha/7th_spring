package umc.study.repository.ReviewRepository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import umc.study.domain.Review;
import umc.study.domain.Member;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {
    // Member 기반으로 Review 조회
    Page<Review> findByMemberId(Long memberId, Pageable pageable);

    // Store ID 기반으로 Review 조회
    Page<Review> findByStoreId(Long storeId, Pageable pageable);
}
