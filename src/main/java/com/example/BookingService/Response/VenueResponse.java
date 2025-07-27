package com.example.BookingService.Response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class VenueResponse {
    private Long id;
    private String name;
    private Long total_capacity;
    private String address;
}
