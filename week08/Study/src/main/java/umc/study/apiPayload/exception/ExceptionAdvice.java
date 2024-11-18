package umc.study.apiPayload.exception;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import umc.study.apiPayload.ApiResponse;
import umc.study.apiPayload.code.ErrorReasonDTO;
import umc.study.apiPayload.code.status.ErrorStatus;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;

@Slf4j
@RestControllerAdvice(annotations = {RestController.class})
public class ExceptionAdvice extends ResponseEntityExceptionHandler {

    // 1. 미션 진행 중 예외 처리
    @ExceptionHandler(MissionAlreadyInProgressException.class)
    public ResponseEntity<Object> handleMissionAlreadyInProgress(MissionAlreadyInProgressException e, HttpServletRequest request) {
        log.warn("MissionAlreadyInProgressException 발생: Mission ID = {}, Request URI = {}",
                e.getMissionId(),
                request.getRequestURI());

        // ErrorReasonDTO 생성
        ErrorReasonDTO errorReason = ErrorReasonDTO.builder()
                .code("CONFLICT409")
                .message("이미 진행 중인 미션입니다.")
                .httpStatus(HttpStatus.CONFLICT)
                .build();

        // 응답 생성
        return createErrorResponse(e, errorReason, request);
    }

    // 2. 일반적인 예외 처리
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> exception(Exception e, WebRequest request) {
        log.error("예기치 못한 오류 발생", e);

        // 상태 코드 구분
        HttpStatus status = (e instanceof IllegalArgumentException) ? HttpStatus.BAD_REQUEST : HttpStatus.INTERNAL_SERVER_ERROR;
        ErrorReasonDTO errorReason = ErrorReasonDTO.builder()
                .code(ErrorStatus._INTERNAL_SERVER_ERROR.getCode())
                .message(ErrorStatus._INTERNAL_SERVER_ERROR.getMessage())
                .httpStatus(status)
                .build();

        return createErrorResponse(e, errorReason, request);
    }

    // 3. ConstraintViolationException 처리 (추가)
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<Object> validation(ConstraintViolationException e, WebRequest request) {
        String errorMessage = e.getConstraintViolations().stream()
                .map(constraintViolation -> constraintViolation.getMessage())
                .findFirst()
                .orElse("Validation Error");

        ErrorReasonDTO errorReason = ErrorReasonDTO.builder()
                .code("VALIDATION_ERROR")
                .message(errorMessage)
                .httpStatus(HttpStatus.BAD_REQUEST)
                .build();

        return createErrorResponse(e, errorReason, request);
    }

    // 4. 공통 응답 생성 메서드
    private ResponseEntity<Object> createErrorResponse(Exception e, ErrorReasonDTO reason, HttpServletRequest request) {
        ApiResponse<Object> body = ApiResponse.onFailure(reason.getCode(), reason.getMessage(), null);
        WebRequest webRequest = new ServletWebRequest(request);
        return super.handleExceptionInternal(
                e,
                body,
                new HttpHeaders(),
                reason.getHttpStatus(),
                webRequest
        );
    }

    private ResponseEntity<Object> createErrorResponse(Exception e, ErrorReasonDTO reason, WebRequest request) {
        ApiResponse<Object> body = ApiResponse.onFailure(reason.getCode(), reason.getMessage(), null);
        return super.handleExceptionInternal(
                e,
                body,
                new HttpHeaders(),
                reason.getHttpStatus(),
                request
        );
    }
}
