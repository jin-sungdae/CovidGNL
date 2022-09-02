package com.example.covidline.controller.api;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.function.RouterFunction;
import org.springframework.web.servlet.function.ServerResponse;

import java.util.List;

import static org.springframework.web.servlet.function.RequestPredicates.path;
import static org.springframework.web.servlet.function.RouterFunctions.route;

@Configuration
public class APIPlaceRouter {



//    @Bean
//    public RouterFunction<ServerResponse> placeRouter() {
//        return route().nest(path("/api/places"), builder -> builder
//                .GET("", req -> ServerResponse.ok().body(List.of("place1","place2")))
//                .POST("", req -> ServerResponse.ok().body(true))
//                .GET("/{placeId}", req -> ServerResponse.ok().body("place " + req.pathVariable("placeId")))
//                .PUT("/{placeId}", req -> ServerResponse.ok().body(true))
//                .DELETE("/{placeId}", req -> ServerResponse.ok().body(true))
//                ).build();
//    }

    @Bean
    public RouterFunction<ServerResponse> placeRouter(APIPlaceHandler apiPlaceHandler) {
        return route().nest(path("/api/places"), builder -> builder
                .GET("", apiPlaceHandler::getPlaces)
                .POST("", apiPlaceHandler::createPlaces)
                .GET("/{placeId}", apiPlaceHandler::getPlace)
                .PUT("/{placeId}", apiPlaceHandler::modifyPlace)
                .DELETE("/{placeId}", apiPlaceHandler::removePlace)
        ).build();
    }


}
