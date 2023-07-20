package ro.msg.learning.shop.service;

import ro.msg.learning.shop.domain.Product;
import ro.msg.learning.shop.dto.ProductDto;
import ro.msg.learning.shop.exception.LocationNotFoundException;
import ro.msg.learning.shop.exception.ProductCategoryNotFoundException;
import ro.msg.learning.shop.exception.ProductNotFoundException;

import java.util.List;
import java.util.UUID;

public interface ProductService {
     ProductDto createProduct(ProductDto productDto, String productCategoryName,String locationName,Integer quantity) throws ProductCategoryNotFoundException, LocationNotFoundException;

     String deleteProduct(UUID productId) throws ProductNotFoundException;

     List<ProductDto> getAllProducts();

     String patchProduct(UUID productId,ProductDto productDto) throws ProductNotFoundException;
}
