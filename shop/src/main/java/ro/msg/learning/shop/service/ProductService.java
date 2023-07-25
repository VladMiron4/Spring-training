package ro.msg.learning.shop.service;


import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ro.msg.learning.shop.domain.Product;
import ro.msg.learning.shop.domain.ProductCategory;
import ro.msg.learning.shop.domain.Stock;
import ro.msg.learning.shop.dto.ProductDto;
import ro.msg.learning.shop.exception.BadRequestException;
import ro.msg.learning.shop.exception.NotFoundException;
import ro.msg.learning.shop.mapper.ProductMapper;
import ro.msg.learning.shop.mapper.StockMapper;
import ro.msg.learning.shop.repository.LocationRepository;
import ro.msg.learning.shop.repository.ProductCategoryRepository;
import ro.msg.learning.shop.repository.ProductRepository;
import ro.msg.learning.shop.repository.StockRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static ro.msg.learning.shop.message.Messages.*;

@Service
@AllArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    private final ProductCategoryRepository productCategoryRepository;

    private final StockRepository stockRepository;

    private final LocationRepository locationRepository;


    private final ProductMapper productMapper;

    private final StockMapper stockMapper;


    public ProductDto createProduct(ProductDto productDto) throws BadRequestException {

        if (productCategoryRepository.findById(UUID.fromString(productDto.getCategory())).isEmpty()) {
            throw new BadRequestException(BAD_PRODUCT_CATEGORY);
        }
        ProductCategory productCategory = productCategoryRepository.findById(UUID.fromString(productDto.getCategory())).get();
        productDto.setCategory(productCategory.getProductCategoryId().toString());
        productRepository.save(productMapper.toEntity(productDto));
        return productDto;
    }


    public String deleteProduct(UUID productId) throws NotFoundException {
        if (productRepository.findById(productId).isEmpty()) {
            throw new NotFoundException(PRODUCT_NOT_FOUND);
        }
        Product foundProduct = productRepository.findById(productId).get();

        List<Stock> stockList = stockRepository.findAllByProductProductId(productId);
        productRepository.delete(foundProduct);
        stockList.forEach(stock -> {
            stockRepository.delete(stock);
        });
        productRepository.delete(foundProduct);
        if (productRepository.findById(productId).isPresent()) {
            return DELETE_ERROR;
        }
        return DELETED_SUCCESSFULLY;
    }


    public List<ProductDto> getAllProducts() {
        List<Product> foundProducts = productRepository.findAll();
        List<ProductDto> productDtos = new ArrayList<>();
        foundProducts.forEach(product -> {
            productDtos.add(productMapper.toDto(product));
        });
        return productDtos;
    }


    public String patchProduct(UUID productId, ProductDto productDto) throws NotFoundException {
        if (productRepository.findById(productId).isEmpty()) {
            throw new NotFoundException(PRODUCT_NOT_FOUND);
        }
        Product foundProduct = productRepository.findById(productId).get();
        if (productDto.getCategory() != null) {
            foundProduct.setCategory(UUID.fromString(productDto.getCategory()));
        }
        if (productDto.getName() != null && !productDto.getName().equals("")) {
            foundProduct.setName(productDto.getName());
        }
        if (productDto.getSupplier() != null && !productDto.getSupplier().equals("")) {
            foundProduct.setSupplier(productDto.getSupplier());
        }
        if (productDto.getImageUrl() != null && !productDto.getImageUrl().equals("")) {
            foundProduct.setImageUrl(productDto.getImageUrl());
        }
        if (productDto.getWeight() != null) {
            foundProduct.setWeight(productDto.getWeight());
        }
        if (productDto.getPrice() != null) {
            foundProduct.setPrice(productDto.getPrice());
        }
        productRepository.save(foundProduct);
        return PATCHED_SUCCESSFULLY;
    }
}
