package ro.msg.learning.shop.service;

import ro.msg.learning.shop.domain.ProductCategory;
import ro.msg.learning.shop.dto.ProductCategoryDto;
import ro.msg.learning.shop.exception.ProductCategoryNotFoundException;
import ro.msg.learning.shop.exception.ProductNotFoundException;

import java.util.List;
import java.util.UUID;

public interface ProductCategoryService {
    public ProductCategoryDto createProductCategory(ProductCategoryDto productCategoryDto);
    public List<ProductCategoryDto>findAllProductCategories();
    public String deleteProductCategory(UUID productCategoryId) throws ProductCategoryNotFoundException;
    public String putProductCategory(UUID productCategoryId,ProductCategoryDto productCategoryDto) throws ProductNotFoundException;

}
