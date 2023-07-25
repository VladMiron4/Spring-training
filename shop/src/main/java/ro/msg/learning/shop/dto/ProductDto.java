package ro.msg.learning.shop.dto;

import lombok.*;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductDto {
    private String productId;
    private String name;
    private BigDecimal price;
    private Double weight;
    private String imageUrl;
    private String category;
    private String supplier;
}
