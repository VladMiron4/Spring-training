package ro.msg.learning.shop.dto;

import lombok.*;

import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderDto {
    private UUID orderId;
    private UUID customerId;
    private String addressCity;
    private String addressCountry;
    private String adressStreet;
    private String adressCounty;
    private LocalDate date;
}
