package com.example.BookingService.Service;

import com.example.BookingService.Client.InventoryServiceClient;
import com.example.BookingService.Entity.Customer;
import com.example.BookingService.Repository.CustomerRepository;
import com.example.BookingService.Request.BookingRequest;
import com.example.BookingService.Response.BookingResponse;
import com.example.BookingService.Response.InventoryResponse;
import com.example.BookingService.event.BookingEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Slf4j
@Service
public class BookingService {

    private final CustomerRepository customerRepository;
    private final InventoryServiceClient inventoryServiceClient;
    private final KafkaTemplate<String, BookingEvent> kafkaTemplate;

    @Autowired
    public BookingService(final CustomerRepository customerRepository, final InventoryServiceClient inventoryServiceClient, final KafkaTemplate<String, BookingEvent> kafkaTemplate) {
        this.customerRepository = customerRepository;
        this.inventoryServiceClient = inventoryServiceClient;
        this.kafkaTemplate = kafkaTemplate;
    }

    public BookingResponse createBooking(final BookingRequest request) throws Exception {
        //Check if user exists for booking
        log.info(String.valueOf(request.getUserId()));
/*        final Customer customer= customerRepository.findById(String.valueOf(request.getUserId())).orElse(null);
        if(customer == null){
            throw new Exception("User not found");
        }*/
        //Check if enough inventory available for booking
        final InventoryResponse inventoryResponse= inventoryServiceClient.getInventory(request.getEventId());
        if(request.getTicketCount() > inventoryResponse.getCapacity()){
            throw new Exception("Sorry, event has been sold");
        }
        //Create Booking
        final BookingEvent bookingEvent = createBookinEvent(inventoryResponse,request);
        log.info("Booking sent to kafka, {}", bookingEvent);
        //Send to kafka topic
        kafkaTemplate.send("booking",bookingEvent);
        return BookingResponse.builder()
                .userId(bookingEvent.getUserId())
                .eventId(bookingEvent.getEventId())
                .ticketCount(bookingEvent.getTikcetCount())
                .ticketPrice(bookingEvent.getTikcetPrice())
                .build();
    }

    public BookingEvent createBookinEvent(InventoryResponse inventoryResponse,BookingRequest request){
        return BookingEvent.builder()
                .eventId(request.getEventId())
                .userId(request.getUserId())
                .tikcetCount(request.getTicketCount())
                .tikcetPrice(inventoryResponse.getTicketPrice().multiply(BigDecimal.valueOf(request.getTicketCount())))
                .build();
    }

}
