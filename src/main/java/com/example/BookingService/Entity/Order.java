package com.example.BookingService.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;
import java.sql.Timestamp;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Builder
@Table(name = "orders")
public class Order {
    @Id
    @Column(name = "id")
    private int id;

    @Column(name = "total")
    private Long total;

    @Column(name = "quantity")
    private Long quantity;

    @Column(name = "placed_at")
    private Timestamp placed_at;
}
