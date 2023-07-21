package ro.msg.learning.shop.dto;

import lombok.*;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CustomerDto {
    private UUID customerId;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String userName;

}
