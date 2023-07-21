package ro.msg.learning.shop.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LoginCustomerDto {
    private String email;
    private String password;
}
