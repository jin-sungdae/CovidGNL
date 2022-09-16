package com.example.covidline.dto;

import com.example.covidline.constant.EventStatus;
import com.example.covidline.constant.PlaceType;

import java.time.LocalDateTime;

public record EventDTO(
        Long id,
        PlaceDTO place,
        String eventName,
        EventStatus eventStatus,
        LocalDateTime eventStartDatetime,
        LocalDateTime eventEndDatetime,
        Integer currentNumberOfPeople,
        Integer capacity,
        String memo
) {
    public static EventDTO of(
            Long id,
            PlaceDTO place,
            String eventName,
            EventStatus eventStatus,
            LocalDateTime eventStartDatetime,
            LocalDateTime eventEndDatetime,
            Integer currentNumberOfPeople,
            Integer capacity,
            String memo
    ) {
        return new EventDTO(id, place, eventName, eventStatus, eventStartDatetime, eventEndDatetime, currentNumberOfPeople, capacity, memo);
    }
}
