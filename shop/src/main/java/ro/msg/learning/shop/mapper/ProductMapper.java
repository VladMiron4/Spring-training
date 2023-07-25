package ro.msg.learning.shop.mapper;

import org.springframework.stereotype.Component;
import ro.msg.learning.shop.domain.Product;
import ro.msg.learning.shop.dto.ProductDto;

import java.util.UUID;

@Component
public class ProductMapper {
    public ProductDto toDto(Product product) {

        return ProductDto.builder()
                .productId(product.getProductId().toString())
                .category(product.getCategory().toString())
                .price(product.getPrice())
                .imageUrl(product.getImageUrl())
                .supplier(product.getSupplier())
                .name(product.getName())
                .weight(product.getWeight())
                .build();
    }

    public Product toEntity(ProductDto productDto) {
        return Product.builder()
                .category(UUID.fromString(productDto.getCategory()))
                .name(productDto.getName())
                .supplier(productDto.getSupplier())
                .price(productDto.getPrice())
                .weight(productDto.getWeight())
                .imageUrl(productDto.getImageUrl())
                .build();
    }
}
