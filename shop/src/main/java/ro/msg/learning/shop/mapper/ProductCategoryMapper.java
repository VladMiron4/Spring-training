package ro.msg.learning.shop.mapper;

import org.springframework.stereotype.Component;
import ro.msg.learning.shop.domain.ProductCategory;
import ro.msg.learning.shop.dto.ProductCategoryDto;

@Component
public class ProductCategoryMapper {

    public ProductCategory toEntity(ProductCategoryDto productCategoryDto){
        return new ProductCategory(productCategoryDto.getName(), productCategoryDto.getDescription());
    }
    public ProductCategoryDto toDto(ProductCategory productCategory){
        return new ProductCategoryDto(productCategory.getProductCategoryId(),productCategory.getName(), productCategory.getDescription());
    }
}
