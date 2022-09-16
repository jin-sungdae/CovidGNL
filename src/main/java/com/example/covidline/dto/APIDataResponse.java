package com.example.covidline.dto;

import com.example.covidline.constant.ErrorCode;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@ToString
@EqualsAndHashCode(callSuper = true)
public class APIDataResponse<T>  extends com.example.covidline.dto.APIErrorResponse{

    private final T data;

    private APIDataResponse(T data) {
        super(true, ErrorCode.OK.getCode(), ErrorCode.OK.getMessage());
        this.data = data;
    }

    public static <T> APIDataResponse<T> of (T data) {
        return new APIDataResponse<>(data);
    }

    public static <T> APIDataResponse<T> empty () {
        return new APIDataResponse<>(null);
    }
}
