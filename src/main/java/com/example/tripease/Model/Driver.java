package com.example.tripease.Model;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity

public class Driver {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int driverId;
//    @Column(name = "driverName")
    private String name;
//    @Column(name = "driverAge")
    private  int age;

    @Column(unique = true, nullable = false)
    private String emailId;


    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "driver_id")
    List<Booking> bookings = new ArrayList<>();

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "cab_id")
    private Cab cab;

}
