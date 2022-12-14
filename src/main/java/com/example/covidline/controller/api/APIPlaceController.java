package com.example.covidline.controller.api;

import com.example.covidline.constant.PlaceType;
import com.example.covidline.dto.APIDataResponse;
import com.example.covidline.dto.PlaceRequest;
import com.example.covidline.dto.PlaceResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/*
        @RequestMapping
        - name : 뷰 템플릿에서 식별할 때 쓰는 이름
        - value, path : URL
        - method : HTTP method (ex : GET, POST, ...)
        - params : 파라미터 검사 ("test=true") - 파라미터 있는 요청만
        - headers : 헤더 검사 ("header-auth=stupidPassword") - header-auth 헤더값 있는 요청만
        - consumes : 헤더의 Content-Type 검사 (MediaType.APPLICATION_JSON_VALUE) - json으로 데이터 주는 요청만
        - produces : 헤더의 Accept 검사
 */
@RequestMapping("/api")
@RestController
public class APIPlaceController {

    @GetMapping("/places")
    public APIDataResponse<List<PlaceResponse>> getPlaces() {
        return APIDataResponse.of(List.of(PlaceResponse.of(
            PlaceType.COMMON,
            "랄라베드민턴장",
            "서울시 강남구 강남대로 1234",
            "010-1234-5678",
            30,
                "신장개업"
        )));
    }


    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/places")
    public APIDataResponse<Void> createPlace(@RequestBody PlaceRequest placeRequest) {
        return APIDataResponse.empty();
    }
    
    @GetMapping("/places/{placeId}")
    public APIDataResponse<PlaceResponse> getPlace(@PathVariable Long placeId) {
        if (placeId.equals(2L)) {
            return APIDataResponse.empty();
        }

        return APIDataResponse.of(PlaceResponse.of(
                PlaceType.COMMON,
                "랄라베드민턴장",
                "서울시 강남구 강남대로 1234",
                "010-1234-5678",
                30,
                "신장개업"
        ));
    }
    
    @PutMapping("/places/{placeId}")
    public Boolean modifyPlace(@PathVariable Integer placeId) {
        return true;
    }
    
    @DeleteMapping("/places/{placeId}")
    public Boolean removePlace(@PathVariable Integer placeId) {
        return true;
    }
}
