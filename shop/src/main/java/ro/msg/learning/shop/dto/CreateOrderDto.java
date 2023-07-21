package ro.msg.learning.shop.dto;

import lombok.*;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateOrderDto {
    private UUID orderId;
    private UUID customerId;
    private String addressCity;
    private String addressCountry;
    private String adressStreet;
    private String adressCounty;
    private LocalDate date;
    private List<OrderProductDto> orderProductDtoList;
}
