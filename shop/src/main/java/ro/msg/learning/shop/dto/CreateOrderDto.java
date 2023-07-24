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
    private UUID customerId;
    private String addressCity;
    private String addressCountry;
    private String addressStreet;
    private String addressCounty;
    private List<OrderProductDto> orderProductDtoList;
}
