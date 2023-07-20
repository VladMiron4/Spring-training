package ro.msg.learning.shop.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ro.msg.learning.shop.domain.ProductCategory;

import java.math.BigDecimal;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductDto {
    private UUID productId;
    private String name;
    private BigDecimal price;
    private Double weight;
    private String imageUrl;
    private UUID category;
    private String supplier;
    public ProductDto(UUID productId,String name,BigDecimal price,Double weight, String imageUrl, String supplier,UUID category){
        this.productId=productId;
        this.name=name;
        this.price=price;
        this.weight=weight;
        this.imageUrl=imageUrl;
        this.supplier=supplier;
        this.category=category;
    }
}
