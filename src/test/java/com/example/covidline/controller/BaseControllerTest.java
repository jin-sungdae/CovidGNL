package com.example.covidline.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.CoreMatchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@DisplayName("컨트롤러 - Base 컨트롤러 테스트")
@AutoConfigureMockMvc
@SpringBootTest
//@WebMvcTest(BaseController.class)
class BaseControllerTest {

    private final MockMvc mvc;

    public BaseControllerTest(@Autowired MockMvc mvc) {
        this.mvc = mvc;
    }

    @DisplayName("[view][GET] 기본 페이지 요청")
    @Test
    void givenNothing_whenRequestingRootPage_thenReturnsIndexPage() throws Exception {
        //Given

        // When & Then
        mvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.TEXT_HTML))
                .andExpect(content().string(containsString("this is default html")))
                .andExpect(view().name("index"))
                .andDo(print());


        // When
        //ResultActions result = mvc.perform(get("/"));


        // Then
//        result
//                .andExpect(status().isOk())
//                .andExpect(content().contentTypeCompatibleWith(MediaType.TEXT_HTML))
//                .andExpect(content().string(containsString("this is default html")))
//                .andExpect(view().name("index"))
//                .andDo(print());
    }
}