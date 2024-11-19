package umc.study.web.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import umc.study.domain.mapping.MemberMission;
import umc.study.service.MemberMissionService.MemberMissionService;
import umc.study.validation.annotation.MissionExists;

@RestController
@RequestMapping("/api/members/{memberId}/missions")
@RequiredArgsConstructor
public class MemberMissionController {

    private final MemberMissionService memberMissionService;

    @PostMapping("/{missionId}")
    public ResponseEntity<?> addMissionToOngoing(
            @PathVariable("memberId")  Long memberId, // 회원 존재 여부 검증
            @PathVariable("missionId") @MissionExists Long missionId // 미션 존재 여부 검증
    ) {
        // Service를 통해 미션 도전 추가
        MemberMission memberMission = memberMissionService.addMissionToOngoing(memberId, missionId);
        return ResponseEntity.ok(memberMission);
    }
}
