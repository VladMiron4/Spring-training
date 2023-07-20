package ro.msg.learning.shop.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.msg.learning.shop.domain.Location;
import ro.msg.learning.shop.domain.Product;
import ro.msg.learning.shop.domain.ProductCategory;
import ro.msg.learning.shop.domain.key.StockId;
import ro.msg.learning.shop.dto.ProductCategoryDto;
import ro.msg.learning.shop.dto.ProductDto;
import ro.msg.learning.shop.dto.StockDto;
import ro.msg.learning.shop.exception.LocationNotFoundException;
import ro.msg.learning.shop.exception.ProductCategoryNotFoundException;
import ro.msg.learning.shop.exception.ProductNotFoundException;
import ro.msg.learning.shop.mapper.ProductCategoryMapper;
import ro.msg.learning.shop.mapper.ProductMapper;
import ro.msg.learning.shop.mapper.StockMapper;
import ro.msg.learning.shop.message.Messages;
import ro.msg.learning.shop.repository.LocationRepository;
import ro.msg.learning.shop.repository.ProductCategoryRepository;
import ro.msg.learning.shop.repository.ProductRepository;
import ro.msg.learning.shop.repository.StockRepository;
import ro.msg.learning.shop.service.ProductService;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static ro.msg.learning.shop.message.Messages.*;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    ProductRepository productRepository;
    @Autowired
    ProductCategoryRepository productCategoryRepository;
    @Autowired
    StockRepository stockRepository;
    @Autowired
    LocationRepository locationRepository;


   @Autowired
   ProductMapper productMapper;
   @Autowired
   StockMapper stockMapper;
   @Autowired

    @Override
    public ProductDto createProduct(ProductDto productDto, String productCategoryName,String locationName,Integer quantity) throws ProductCategoryNotFoundException, LocationNotFoundException {

        ProductCategory productCategory=productCategoryRepository.findOneByNameIs(productCategoryName);
        if (productCategory==null){
            throw new ProductCategoryNotFoundException();
        }
        Location foundLocation=locationRepository.findOneByNameIs(locationName);
        if (foundLocation==null){
            throw new LocationNotFoundException();
        }
        productDto.setCategory(productCategory.getProductCategoryId());
        Product savedProduct=productRepository.save(productMapper.toEntity(productDto));
        StockId stockId=StockId.builder()
                .productId(savedProduct.getProductId())
                .locationId(foundLocation.getLocationId())
                .build();
        StockDto stockDto= StockDto.builder()
                .stockId(stockId)
                .quantity(quantity)
                .build();
        stockRepository.save(stockMapper.toEntity(stockDto));
        return productDto;
    }

    @Override
    public String deleteProduct(UUID productId) throws ProductNotFoundException {
        if (productRepository.findById(productId).isEmpty()){
            throw new ProductNotFoundException();
        }
        Product foundProduct=productRepository.findById(productId).get();
        productRepository.delete(foundProduct);
        if (productRepository.findById(productId).isPresent()) {
            return DELETE_ERROR;
        }
        return DELETED_SUCCESSFULLY;
    }

    @Override
    public List<ProductDto> getAllProducts() {
        List<Product> foundProducts=productRepository.findAll();
        List<ProductDto>productDtos=new ArrayList<>();
        foundProducts.forEach(product-> {
            productDtos.add(productMapper.toDto(product));
        });
        return productDtos;
    }

    @Override
    public String patchProduct(UUID productId, ProductDto productDto) throws ProductNotFoundException {
        if (productRepository.findById(productId).isEmpty()){
            throw new ProductNotFoundException();
        }
        Product foundProduct=productRepository.findById(productId).get();
        if (productDto.getCategory()!=null){
            foundProduct.setCategory(productDto.getCategory());
        }
        if (productDto.getName()!=null && !productDto.getName().equals("")){
            foundProduct.setName(productDto.getName());
        }
        if (productDto.getSupplier()!=null && !productDto.getSupplier().equals("")){
            foundProduct.setSupplier(productDto.getSupplier());
        }
        if (productDto.getImageUrl()!=null && !productDto.getImageUrl().equals("")){
            foundProduct.setImageUrl(productDto.getImageUrl());
        }
        if (productDto.getWeight()!=null) {
            foundProduct.setWeight(productDto.getWeight());
        }
        if (productDto.getPrice()!=null){
            foundProduct.setPrice(productDto.getPrice());
        }
        productRepository.save(foundProduct);
        return PATCHED_SUCCESSFULLY;
    }
}
