package com.example.tripease.Model;

import com.example.tripease.Enum.TripStatus;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Entity
public class Booking{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int bookingId;

    String pickup;

    String destination;

    double tripDistanceInKm;

    @Enumerated(EnumType.STRING)
    TripStatus tripStatus;

    double billAmount;

    @CreationTimestamp
    Date bookedAt;

    @UpdateTimestamp
    Date lastUpdateAt;
}
