package umc.study.service.MissionService;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.study.converter.MissionAddConverter;
import umc.study.domain.Mission;
import umc.study.domain.Review;
import umc.study.domain.Store;
import umc.study.repository.MissionRepository.MissionRepository;
import umc.study.repository.StoreRepository.StoreRepository;
import umc.study.web.dto.MissionAddRequestDTO;
import umc.study.web.dto.MissionAddResponseDTO;

@Service
@RequiredArgsConstructor
public class MissionService {
    private final StoreRepository storeRepository;
    private final MissionRepository missionRepository;

    @Transactional
    public MissionAddResponseDTO addMissionToStore(Long storeId, MissionAddRequestDTO missionRequestDTO) {
        // 가게 정보 조회
        Store store = storeRepository.findById(storeId)
                .orElseThrow(() -> new IllegalArgumentException("가게 정보를 찾을 수 없습니다."));

        // 미션 생성 및 저장
        Mission mission = Mission.builder()
                .reward(missionRequestDTO.getReward())
                .deadline(missionRequestDTO.getDeadline())
                .missionSpec(missionRequestDTO.getMissionSpec())
                .store(store)
                .build();

        Mission savedMission = missionRepository.save(mission);
        if (savedMission == null) {
            throw new RuntimeException("Failed to save mission.");
        }

        // MissionAddResponseDTO 반환
        return MissionAddConverter.toMissionAddResponseDTO(savedMission);
    }

    public Page<Mission> getMissionsByStore(Long storeId, int page, int size) {
        PageRequest pageable = PageRequest.of(page, size);
        return missionRepository.findByStoreId(storeId, pageable);
    }

}

