package ro.msg.learning.shop.mapper;

import org.springframework.stereotype.Component;
import ro.msg.learning.shop.domain.Product;
import ro.msg.learning.shop.dto.ProductDto;

@Component
public class ProductMapper {
    public ProductDto toDto(Product product) {

        return ProductDto.builder()
                .productId(product.getProductId())
                .category(product.getCategory())
                .price(product.getPrice())
                .imageUrl(product.getImageUrl())
                .supplier(product.getSupplier())
                .name(product.getName())
                .weight(product.getWeight())
                .build();
    }

    public Product toEntity(ProductDto productDto) {
        return Product.builder()
                .productId(productDto.getProductId())
                .category(productDto.getCategory())
                .name(productDto.getName())
                .supplier(productDto.getSupplier())
                .price(productDto.getPrice())
                .weight(productDto.getWeight())
                .imageUrl(productDto.getImageUrl())
                .build();
    }
}
