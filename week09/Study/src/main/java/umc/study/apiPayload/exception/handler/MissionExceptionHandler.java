package umc.study.apiPayload.exception.handler;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import umc.study.apiPayload.ApiResponse;
import umc.study.apiPayload.code.ErrorReasonDTO;
import umc.study.apiPayload.exception.MissionAlreadyInProgressException;

@Slf4j
@RestControllerAdvice
public class MissionExceptionHandler {

    @ExceptionHandler(MissionAlreadyInProgressException.class)
    public ResponseEntity<Object> handleMissionAlreadyInProgress(MissionAlreadyInProgressException e, HttpServletRequest request) {
        // 로그 출력
        log.warn("MissionAlreadyInProgressException 발생: Mission ID = {}, Request URI = {}",
                e.getMissionId(),
                request.getRequestURI());

        // ErrorReasonDTO 생성
        ErrorReasonDTO errorReason = ErrorReasonDTO.builder()
                .code("CONFLICT409")
                .message("이미 진행 중인 미션입니다.")
                .httpStatus(HttpStatus.CONFLICT)
                .build();

        // ApiResponse 생성 및 반환
        return ResponseEntity.status(errorReason.getHttpStatus())
                .body(ApiResponse.onFailure(
                        errorReason.getCode(),
                        errorReason.getMessage(),
                        e.getMissionId() // 결과로 Mission ID 반환 (선택 사항)
                ));
    }

    // Optional: 기타 관련 예외 처리 (예: 비즈니스 로직 예외 등)
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleGenericException(Exception e, HttpServletRequest request) {
        log.error("예기치 못한 예외 발생: Request URI = {}", request.getRequestURI(), e);

        // 일반적인 에러 응답
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(ApiResponse.onFailure(
                        "COMMON500",
                        "서버 내부 오류입니다. 관리자에게 문의하세요.",
                        null
                ));
    }
}
