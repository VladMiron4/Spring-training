package ro.msg.learning.shop.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ro.msg.learning.shop.domain.Customer;

import java.time.LocalDate;
import java.util.UUID;

@Data
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
