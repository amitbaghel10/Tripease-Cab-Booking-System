package com.example.tripease.DTO.Request;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class BookingRequest {

    private String pickup;

    private String destination;

    private double tripDistanceInKm;


}
