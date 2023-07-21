package ro.msg.learning.shop.dto;

import lombok.*;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductCategoryDto {
    private UUID productCategoryId;
    private String name;
    private String description;
}
