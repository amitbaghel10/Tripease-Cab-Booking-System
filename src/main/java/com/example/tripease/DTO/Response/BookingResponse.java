package com.example.tripease.DTO.Response;

import com.example.tripease.Enum.TripStatus;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class BookingResponse {

    String pickup;

    String destination;

    double tripDistanceInKm;

    TripStatus tripStatus;

    double billAmount;

    Date bookedAt;

    Date lastUpdateAt;

    CustomerResponse customer;
    CabResponse cab;
}
