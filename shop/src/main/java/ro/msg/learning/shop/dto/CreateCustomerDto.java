package ro.msg.learning.shop.dto;

import lombok.*;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CreateCustomerDto {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
}
