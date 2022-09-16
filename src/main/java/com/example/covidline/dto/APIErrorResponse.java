package com.example.covidline.dto;

import com.example.covidline.constant.ErrorCode;
import lombok.*;

import java.io.EOFException;

@Getter
@ToString
@EqualsAndHashCode
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public class APIErrorResponse {

    private final Boolean success;
    private final Integer errorCode;
    private final String message;

    public static APIErrorResponse of (Boolean success, Integer errorCode, String message) {
        return new APIErrorResponse(success, errorCode, message);
    }

    public static APIErrorResponse of (Boolean success, ErrorCode errorCode) {
        return new APIErrorResponse(success, errorCode.getCode(), errorCode.getMessage());
    }

    public static APIErrorResponse of (Boolean success, ErrorCode errorCode, Exception e) {
        return new APIErrorResponse(success, errorCode.getCode(), errorCode.getMessage(e));
    }

    public static APIErrorResponse of (Boolean success, ErrorCode errorCode, String message) {
        return new APIErrorResponse(success, errorCode.getCode(), errorCode.getMessage(message));
    }

}
