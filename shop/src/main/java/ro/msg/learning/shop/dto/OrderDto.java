package ro.msg.learning.shop.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ro.msg.learning.shop.domain.Customer;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDto {
    private String addressCity;
    private String addressCountry;
    private String adressStreet;
    private String adressCounty;
    private Customer customer;

}
