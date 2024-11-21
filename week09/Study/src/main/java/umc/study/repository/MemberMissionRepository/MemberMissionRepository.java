package umc.study.repository.MemberMissionRepository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import umc.study.domain.enums.MissionStatus;
import umc.study.domain.mapping.MemberMission;

@Repository
public interface MemberMissionRepository extends JpaRepository<MemberMission, Long> {
    boolean existsByMissionIdAndStatus(Long missionId, MissionStatus status);
    Page<MemberMission> findByMemberId(Long memberId, Pageable pageable);
}
