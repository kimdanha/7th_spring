package umc.study.repository.ReviewImageRepository;

import org.springframework.data.jpa.repository.JpaRepository;
import umc.study.domain.ReviewImage;

public interface ReviewImageRepository extends JpaRepository<ReviewImage, Long> {
}
