package com.example.BookingService.Entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Builder
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "total")
    private Long totalPrice;

    @Column(name = "quantity")
    private Long ticketCount;

    @CreationTimestamp
    @Column(name = "placed_at")
    private LocalDateTime placed_at;

    @Column(name = "event_id")
    private Long eventId;

    @Column(name = "customer_id")
    private Long customerId;
}
