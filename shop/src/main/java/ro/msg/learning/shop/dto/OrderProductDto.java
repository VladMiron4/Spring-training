package ro.msg.learning.shop.dto;

import lombok.*;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderProductDto {
    private UUID productId;
    private Integer quantity;
}
