package umc.study.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import umc.study.apiPayload.ApiResponse;
import umc.study.converter.MissionConverter;
import umc.study.domain.Mission;
import umc.study.domain.mapping.MemberMission;
import umc.study.service.MemberMissionService.MemberMissionService;
import umc.study.validation.annotation.CheckPage;
import umc.study.validation.annotation.MissionExists;
import umc.study.web.dto.MissionResponseDTO;

@RestController
@RequestMapping("/api/members")
@RequiredArgsConstructor
public class MemberMissionController {

    private final MemberMissionService memberMissionService;

    @PostMapping("{memberId}/{missionId}")
    public ResponseEntity<?> addMissionToOngoing(
            @PathVariable("memberId")  Long memberId, // 회원 존재 여부 검증
            @PathVariable("missionId") @MissionExists Long missionId // 미션 존재 여부 검증
    ) {
        // Service를 통해 미션 도전 추가
        MemberMission memberMission = memberMissionService.addMissionToOngoing(memberId, missionId);
        return ResponseEntity.ok(memberMission);
    }

    @Operation(summary = "특정 사용자의 미션 조회", description = "특정 사용자가 수행한 미션 목록을 페이징 처리하여 반환합니다.")
    @Parameters({
            @Parameter(name = "memberId", description = "사용자 ID", required = true),
            @Parameter(name = "page", description = "페이지 번호 (1 이상)", required = true)
    })
    @GetMapping("/{memberId}/missions")
    public ApiResponse<MissionResponseDTO.MissionPreViewListDTO> getMissionsByMember(
            @PathVariable("memberId") Long memberId,
            @CheckPage @RequestParam("page") Integer page) {

        // Service에서 미션 목록 조회
        Page<Mission> missionPage = memberMissionService.getMissionsByMember(memberId, page-1, 10);

        // Converter를 통해 DTO 변환
        MissionResponseDTO.MissionPreViewListDTO response = MissionConverter.toMissionPreViewListDTO(missionPage);

        // 응답 반환
        return ApiResponse.onSuccess(response);
    }
}
