package com.example.BookingService.Response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class BookingResponse {
    private Long eventId;
    private Long userId;
    private Long ticketCount;
    private BigDecimal ticketPrice;
}
