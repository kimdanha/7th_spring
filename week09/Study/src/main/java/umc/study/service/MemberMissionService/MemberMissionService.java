package umc.study.service.MemberMissionService;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.study.apiPayload.exception.MissionAlreadyInProgressException;
import umc.study.domain.Member;
import umc.study.domain.Mission;
import umc.study.domain.mapping.MemberMission;
import umc.study.domain.enums.MissionStatus;
import umc.study.repository.MemberMissionRepository.MemberMissionRepository;
import umc.study.repository.MemberRepository.MemberRepository;
import umc.study.repository.MissionRepository.MissionRepository;

@Service
@RequiredArgsConstructor
public class MemberMissionService {

    private final MemberMissionRepository memberMissionRepository;
    private final MissionRepository missionRepository;
    private final MemberRepository memberRepository;

    @Transactional
    public MemberMission addMissionToOngoing(Long memberId, Long missionId) {
        if (memberMissionRepository.existsByMissionIdAndStatus(missionId, MissionStatus.IN_PROGRESS)) {
            // 기존 IllegalArgumentException 대신 MissionAlreadyInProgressException 사용
            throw new MissionAlreadyInProgressException(String.valueOf(missionId));
        }

        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 회원입니다."));
        Mission mission = missionRepository.findById(missionId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 미션입니다."));

        MemberMission memberMission = MemberMission.builder()
                .member(member)
                .mission(mission)
                .status(MissionStatus.IN_PROGRESS)
                .build();

        return memberMissionRepository.save(memberMission);
    }

    public Page<Mission> getMissionsByMember(Long memberId, int page, int size) {
        Page<MemberMission> memberMissionPage = memberMissionRepository.findByMemberId(memberId, PageRequest.of(page, size));
        // MemberMission -> Mission 변환
        return memberMissionPage.map(MemberMission::getMission);
    }
}

