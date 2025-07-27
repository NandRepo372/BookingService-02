package com.example.BookingService.Controller;

import com.example.BookingService.Request.BookingRequest;
import com.example.BookingService.Response.BookingResponse;
import com.example.BookingService.Service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class BookingServiceController{

    private final BookingService bookingService;

    @Autowired
    public BookingServiceController(BookingService bookingService) {
        this.bookingService = bookingService;
    }


    @PostMapping(consumes = "application/json" ,produces = "application/json" ,path = "/booking")
    public @ResponseBody BookingResponse createBooking(@RequestBody BookingRequest request) throws Exception {
        return bookingService.createBooking(request);
    }

}
