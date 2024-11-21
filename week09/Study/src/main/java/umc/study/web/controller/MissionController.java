package umc.study.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import umc.study.apiPayload.ApiResponse;
import umc.study.converter.MissionConverter;
import umc.study.domain.Mission;
import umc.study.service.MissionService.MissionService;
import umc.study.validation.annotation.CheckPage;
import umc.study.validation.annotation.StoreExists;
import umc.study.web.dto.MissionAddRequestDTO;
import umc.study.web.dto.MissionAddResponseDTO;
import umc.study.web.dto.MissionResponseDTO;

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

    @Operation(summary = "특정 가게의 미션 조회", description = "특정 가게의 미션 목록을 페이징 처리하여 반환합니다.")
    @Parameters({
            @Parameter(name = "storeId", description = "가게 ID", required = true),
            @Parameter(name = "page", description = "페이지 번호 (1 이상)", required = true)
    })
    @GetMapping("/store/{storeId}")
    public ApiResponse<MissionResponseDTO.MissionPreViewListDTO> getMissionsByStore(
            @PathVariable("storeId") Long storeId,
            @CheckPage @RequestParam("page") Integer page) {
        Page<Mission> missionPage = missionService.getMissionsByStore(storeId, page-1, 10); // 10개씩 페이징
        MissionResponseDTO.MissionPreViewListDTO response = MissionConverter.toMissionPreViewListDTO(missionPage);
        return ApiResponse.onSuccess(response);
    }
}

