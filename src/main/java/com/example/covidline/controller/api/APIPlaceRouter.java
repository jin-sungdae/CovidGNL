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
//        return route()
//                .GET("/api/places", req -> ServerResponse.ok().body(List.of("place1", "place2")))
//                /*
//                @GetMapping("/places")
//                public List<String> getPlaces() { return List.of("place1", "place2"); }
//                 */
//                .POST("/api/places", req -> ServerResponse.ok().body(true))
//                /*
//                @PostMappint("/places")
//                public Boolean createPlace() { return true; }
//                 */
//                .GET("/api/places/{placeId}", req -> ServerResponse.ok().body("place " + req.pathVariable("placeId")))
//                /*
//                @GetMapping("/places/{placeId}")
//                public String getPlace(@PathVariable Integer placeId) { return "place " + placeId; }
//                 */
//                .PUT("/api/places/{placeId}", req -> ServerResponse.ok().body(true))
//                /*
//                @PutMapping("/places/{placeId}")
//                public Boolean modifyPlace(@PathVariable Integer placeId) { return true; }
//                 */
//                .DELETE("/api/places", req -> ServerResponse.ok().body(true))
//                /*
//                @DeleteMapping("/places/{placeId}")
//                public Boolean removePlace(@PathVariable Integer placeId) { return true; }
//                 */
//                .build();
//    }


    /*
     *  route().nest() -> nest()에 공통 path를 넣어 줘서 길이를 줄일 수 있다.
     */

//    @Bean
//    public RouterFunction<ServerResponse> placeRouter() {
//        return route().nest(path("/api/places"), builder -> builder
//                .GET("", req -> ServerResponse.ok().body(List.of("place1","place2")))
//                .POST("", req -> ServerResponse.ok().body(true))
//                .GET("/{placeId}", req -> ServerResponse.ok().body("place " + req.pathVariable("placeId")))
//                .PUT("/{placeId}", req -> ServerResponse.ok().body(true))
//                .DELETE("/{placeId}", req -> ServerResponse.ok().body(true)))
//                .build();
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
