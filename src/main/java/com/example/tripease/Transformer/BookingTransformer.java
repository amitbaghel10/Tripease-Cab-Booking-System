package com.example.tripease.Transformer;

import com.example.tripease.DTO.Request.BookingRequest;
import com.example.tripease.DTO.Response.BookingResponse;
import com.example.tripease.Enum.TripStatus;
import com.example.tripease.Model.Booking;
import com.example.tripease.Model.Cab;
import com.example.tripease.Model.Customer;
import com.example.tripease.Model.Driver;

public class BookingTransformer {

    public static Booking bookingRequestToBooking(BookingRequest bookingRequest, double perKmRate){
        return Booking.builder()
                .pickup(bookingRequest.getPickup())
                .tripDistanceInKm(bookingRequest.getTripDistanceInKm())
                .destination(bookingRequest.getDestination())
                .tripStatus(TripStatus.IN_PROGRESS)
                .billAmount(bookingRequest.getTripDistanceInKm()* perKmRate)
                .build();
    }

    public static BookingResponse bookingToBookingResponse(Booking booking,
                                                           Customer customer,
                                                           Cab cab,
                                                           Driver driver){
        return BookingResponse.builder()
                .pickup(booking.getPickup())
                .destination(booking.getDestination())
                .tripDistanceInKm(booking.getTripDistanceInKm())
                .billAmount(booking.getBillAmount())
                .tripStatus(booking.getTripStatus())
                .bookedAt(booking.getBookedAt())
                .lastUpdateAt(booking.getLastUpdateAt())
                .customer(CustomerTransfomer.customerToCustomerResponse(customer))
                .cab(CabTransformer.cabToCabResponse(cab, driver))
                .build();
    }
}
