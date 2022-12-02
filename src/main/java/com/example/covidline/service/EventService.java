package com.example.covidline.service;

import com.example.covidline.constant.ErrorCode;
import com.example.covidline.constant.EventStatus;
import com.example.covidline.dto.EventDTO;
import com.example.covidline.exception.GeneralException;
import com.example.covidline.repository.EventRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Service
public class EventService {

    private final EventRepository eventRepository;

    public List<EventDTO> getEvents(
            Long placeId,
            String eventName,
            EventStatus eventStatus,
            LocalDateTime eventStartDatetime,
            LocalDateTime eventEndDatetime)
    {
        try {
            log.debug("관찰 - placeId: {}", placeId);
            return eventRepository.findEvents(
                    placeId,
                    eventName,
                    eventStatus,
                    eventStartDatetime,
                    eventEndDatetime
            );
        }
        catch (Exception e) {
            throw new GeneralException(ErrorCode.DATA_ACCESS_ERROR, e);
        }
    }

    public Optional<EventDTO> getEvent(Long eventId) {
        return eventRepository.findEvent(eventId);
    }

    public boolean createEvent(EventDTO eventDTO) {
        return eventRepository.insertEvent(eventDTO);
    }

    public boolean modifyEvent(Long eventId, EventDTO eventDTO) {
        return eventRepository.updateEvent(eventId, eventDTO);
    }

    public boolean removeEvent(Long eventId) {
        return eventRepository.deleteEvent(eventId);
    }
}
