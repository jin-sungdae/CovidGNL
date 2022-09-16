package com.example.covidline.dto;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@ToString
@EqualsAndHashCode(callSuper = true)
public class APIDataResponse  extends com.example.covidline.dto.APIErrorResponse{

    private final Object data;

    private APIDataResponse(boolean success, Integer errorCode, String message, Object data) {
        super(success, errorCode, message);
        this.data = data;
    }

    public static APIDataResponse of (boolean success, Integer errorCode, String message, Object data) {
        return new APIDataResponse(success, errorCode, message, data);
    }
}
