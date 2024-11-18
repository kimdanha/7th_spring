package umc.study.web.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import umc.study.apiPayload.ApiResponse;
import umc.study.service.MissionService.MissionService;
import umc.study.web.dto.MissionAddRequestDTO;
import umc.study.web.dto.MissionAddResponseDTO;

@RestController
@RequestMapping("/api/stores")
@RequiredArgsConstructor
public class MissionController {
    private final MissionService missionService;

    @PostMapping("/{storeId}/missions")
    public ResponseEntity<ApiResponse<MissionAddResponseDTO>> addMissionToStore(
            @PathVariable Long storeId,
            @Valid @RequestBody MissionAddRequestDTO missionRequestDTO) {

        MissionAddResponseDTO response = missionService.addMissionToStore(storeId, missionRequestDTO);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(ApiResponse.onSuccess(
                        "SUCCESS201",
                        "미션이 성공적으로 추가되었습니다.",
                        response
                ));
    }
}

