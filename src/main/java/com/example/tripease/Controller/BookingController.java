package com.example.tripease.Controller;

import com.example.tripease.DTO.Request.BookingRequest;
import com.example.tripease.DTO.Response.BookingResponse;
import com.example.tripease.Service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/booking")
public class BookingController {

    @Autowired BookingService bookingService;


    @PostMapping("/book/customer/{customerId}")

    public BookingResponse bookCab(@RequestBody BookingRequest bookingRequest,
                                   @PathVariable("customerId") int customerId){

        return bookingService.bookCab(bookingRequest, customerId);
    }
}
