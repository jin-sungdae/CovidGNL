package com.example.covidline.controller.api;

import com.example.covidline.constant.ErrorCode;
import com.example.covidline.constant.EventStatus;
import com.example.covidline.dto.EventDTO;
import com.example.covidline.dto.EventResponse;
import com.example.covidline.service.EventService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(APIEventController.class)
class APIEventControllerTest {

    private final MockMvc mvc;
    private final ObjectMapper mapper;

    @MockBean private EventService eventService;

    public APIEventControllerTest(@Autowired MockMvc mvc,
                                  @Autowired ObjectMapper mapper) {
        this.mvc = mvc;
        this.mapper = mapper;
    }

   @DisplayName("[API][GET] 이벤트 리스트 조회 + 검색 파라미터")
   @Test
   void givenParam_whenRequestEvents_thenReturnsListOfEventsInstandardResponse() throws Exception {
        // Given
       given(eventService.getEvents(any(), any(), any(), any(), any())).willReturn(List.of(createEventDTO()));

       // When & Then
       mvc.perform(get("/api/events")
                       .queryParam("placeId", "1")
                       .queryParam("eventName", "운동")
                       .queryParam("eventStatus", EventStatus.OPENED.name())
                       .queryParam("eventStartDatetime", "2021-01-01T00:00:00")
                       .queryParam("eventEndDatetime", "2021-01-02T00:00:00")
               )
               .andExpect(status().isOk())
               .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
               .andExpect(jsonPath("$.data").isArray())
               .andExpect(jsonPath("$.data[0].placeId").value(1L))
               .andExpect(jsonPath("$.data[0].eventName").value("오후 운동"))
               .andExpect(jsonPath("$.data[0].eventStatus").value(EventStatus.OPENED.name()))
               .andExpect(jsonPath("$.data[0].eventStartDatetime").value(LocalDateTime
                       .of(2021,1,1,13,0,0)
                       .format(DateTimeFormatter.ISO_LOCAL_DATE_TIME)))
               .andExpect(jsonPath("$.data[0].eventEndDatetime").value(LocalDateTime
                       .of(2021,1,1,16,0,0)
                       .format(DateTimeFormatter.ISO_LOCAL_DATE_TIME)))
               .andExpect(jsonPath("$.data[0].currentNumberOfPeople").value(0))
               .andExpect(jsonPath("$.data[0].capacity").value(24))
               .andExpect(jsonPath("$.data[0].memo").value("마스크 꼭 착용하세요"))
               .andExpect(jsonPath("$.success").value(true))
               .andExpect(jsonPath("$.errorCode").value(ErrorCode.OK.getCode()))
               .andExpect(jsonPath("$.message").value(ErrorCode.OK.getMessage()));
       then(eventService).should().getEvents(any(), any(), any(), any(), any());
   }

    @DisplayName("[API][GET] 이벤트 리스트 조회 + 잘못된 검색 파라미터")
    @Test
    void givenWrongParam_whenRequestEvents_thenReturnsFailedInstandardResponse() throws Exception {
        // Given
        given(eventService.getEvents(any(), any(), any(), any(), any())).willReturn(List.of(createEventDTO()));

        // When & Then
        mvc.perform(get("/api/events")
                        .queryParam("placeId", "11")
                        .queryParam("eventName", "오")
                        .queryParam("eventStatus", EventStatus.OPENED.name())
                        .queryParam("eventStartDatetime", "2021-01-01T00:00:00")
                        .queryParam("eventEndDatetime", "2021-01-02T00:00:00")
                )
                .andExpect(status().isBadRequest())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.success").value(false))
                .andExpect(jsonPath("$.errorCode").value(ErrorCode.VALIDATION_ERROR.getCode()))
                .andExpect(jsonPath("$.message").value(containsString(ErrorCode.VALIDATION_ERROR.getMessage())));
        then(eventService).shouldHaveNoInteractions();
    }

   @DisplayName("[API][POST] 이벤트 생성")
   @Test
   void givenEvent_whenCreatingAnEvent_thenReturnsSuccessfulStandardResponse() throws Exception {
        // Given
       EventResponse eventResponse = EventResponse.of(
               1L,
               "오후 운동",
               EventStatus.OPENED,
               LocalDateTime.of(2021,1,1,13,0,0),
               LocalDateTime.of(2021,1,1,16,0,0),
               0,
               24,
               "마스크 꼭 착용하세요"
       );
       given(eventService.createEvent(any())).willReturn(true);

       // When
       mvc.perform(
               post("/api/events")
                       .contentType(MediaType.APPLICATION_JSON)
                       .content(mapper.writeValueAsString(eventResponse))
       )
               .andExpect(status().isCreated())
               .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
               .andExpect(jsonPath("$.data").value(Boolean.TRUE.toString()))
               .andExpect(jsonPath("$.success").value(true))
               .andExpect(jsonPath("$.errorCode").value(ErrorCode.OK.getCode()))
               .andExpect(jsonPath("$.message").value(ErrorCode.OK.getMessage()));

        then(eventService).should().createEvent(any());
       // Then
   }

    @DisplayName("[API][POST] 이벤트 생성 - 잘못된 정보 입력")
    @Test
    void givenWrongEvent_whenCreatingAnEvent_thenReturnsFailedStandardResponse() throws Exception {
        // Given
        EventResponse eventResponse = EventResponse.of(
                -1L,
                "  ",
                null,
                null,
                null,
                -1,
                0,
                "마스크 꼭 착용하세요"
        );

        // When && Then
        mvc.perform(
                        post("/api/events")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(mapper.writeValueAsString(eventResponse))
                )
                .andExpect(status().isBadRequest())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.success").value(false))
                .andExpect(jsonPath("$.errorCode").value(ErrorCode.SPRING_BAD_REQUEST.getCode()))
                .andExpect(jsonPath("$.message").value(containsString(ErrorCode.SPRING_BAD_REQUEST.getMessage())));

       // then(eventService).shouldHaveNoInteractions();
    }

   @DisplayName("[API][GET] 단일 이벤트 조회 - 이벤트 있는 경우, 이벤트 데이터를 담은 표준 API 출력")
   @Test
   void givenEventId_whenRequestingExistentEvent_thenReturnsEventInStandardResponse() throws Exception{
        // Given
        long eventId = 1L;
       given(eventService.getEvent(eventId)).willReturn(Optional.of(createEventDTO()));


        // When
       mvc.perform(get("/api/events/" + eventId))
               .andExpect(status().isOk())
               .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
               .andExpect(jsonPath("$.data").isMap())
               .andExpect(jsonPath("$.data.placeId").value(1L))
               .andExpect(jsonPath("$.data.eventName").value("오후 운동"))
               .andExpect(jsonPath("$.data.eventStatus").value(EventStatus.OPENED.name()))
               .andExpect(jsonPath("$.data.eventStartDatetime").value(LocalDateTime
                       .of(2021,1,1,13,0,0)
                       .format(DateTimeFormatter.ISO_LOCAL_DATE_TIME)))
               .andExpect(jsonPath("$.data.eventEndDatetime").value(LocalDateTime
                       .of(2021,1,1,16,0,0)
                       .format(DateTimeFormatter.ISO_LOCAL_DATE_TIME)))
               .andExpect(jsonPath("$.data.currentNumberOfPeople").value(0))
               .andExpect(jsonPath("$.data.capacity").value(24))
               .andExpect(jsonPath("$.data.memo").value("마스크 꼭 착용하세요"))
               .andExpect(jsonPath("$.success").value(true))
               .andExpect(jsonPath("$.errorCode").value(ErrorCode.OK.getCode()))
               .andExpect(jsonPath("$.message").value(ErrorCode.OK.getMessage()));


       // Then
       then(eventService).should().getEvent(eventId);

   }

    @DisplayName("[API][DELETE] 이벤트 삭제")
    @Test
    void givenEvent_whenDeletingAnEvent_thenReturnsSuccessfulStandardResponse() throws Exception{
        // Given
        long eventId = 1L;

        // When & Then
        mvc.perform(delete("/api/events/" + eventId))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.errorCode").value(ErrorCode.OK.getCode()))
                .andExpect(jsonPath("$.message").value(ErrorCode.OK.getMessage()));
    }

    private EventDTO createEventDTO() {
        return EventDTO.of(
                1L,
                "오후 운동",
                EventStatus.OPENED,
                LocalDateTime.of(2021,1,1,13,0,0),
                LocalDateTime.of(2021,1,1,16,0,0),
                0,
                24,
                "마스크 꼭 착용하세요",
                LocalDateTime.now(),
                LocalDateTime.now()
        );
    }

}