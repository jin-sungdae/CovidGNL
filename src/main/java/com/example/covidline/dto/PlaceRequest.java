package com.example.covidline.dto;

import com.example.covidline.constant.EventStatus;
import com.example.covidline.constant.PlaceType;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import java.time.LocalDateTime;

public record PlaceRequest(
        @NotNull PlaceType placeType,
        @NotBlank String placeName,
        @NotNull String address,
        @NotNull String phoneNumber,
        @NotNull @PositiveOrZero Integer capacity,
        String memo
) {

   public static PlaceRequest of( PlaceType placeType,
                                  String placeName,
                                  String address,
                                  String phoneNumber,
                                  Integer capacity,
                                  String memo) {
       return new PlaceRequest(
               placeType,
               placeName,
               address,
               phoneNumber,
               capacity,
               memo
       );
   }
}
