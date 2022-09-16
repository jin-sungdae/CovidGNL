package com.example.covidline.domain;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class AdminPlaceMap {

    private Long id;

    private Long adminId;
    private Long PlaceId;

    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;
}
