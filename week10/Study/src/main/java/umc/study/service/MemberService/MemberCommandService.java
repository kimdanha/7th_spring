package umc.study.service.MemberService;

import org.springframework.stereotype.Service;
import umc.study.domain.Member;
import umc.study.web.dto.MemberRequestDTO;

@Service
public interface MemberCommandService {
    Member joinMember(MemberRequestDTO.JoinDto request);
}
