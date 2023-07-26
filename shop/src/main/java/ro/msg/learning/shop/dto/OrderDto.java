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
    private String orderId;
    private String customerId;
    private String addressCity;
    private String addressCountry;
    private String addressStreet;
    private String addressCounty;
    private LocalDate date;
}
