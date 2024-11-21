package umc.study.repository.MissionRepository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import umc.study.domain.Mission;
import umc.study.domain.Store;

@Repository
public interface MissionRepository extends JpaRepository<Mission, Long> {

    // 특정 가게의 미션을 페이징 처리하여 조회
    Page<Mission> findByStoreId(Long storeId, Pageable pageable);
}

