package umc.study.apiPayload.code;

import lombok.Builder;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@Builder
public class ErrorReasonDTO {


    HttpStatus httpStatus;

    private final boolean isSuccess;
    private final String code;
    private final String message;

    public ErrorReasonDTO(HttpStatus httpStatus, boolean success, String code, String message) {
        this.httpStatus = httpStatus;
        this.isSuccess = success;
        this.code = code;
        this.message = message;
    }

    public boolean getIsSuccess(){return isSuccess;}
}