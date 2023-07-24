package ro.msg.learning.shop.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ro.msg.learning.shop.domain.ProductCategory;
import ro.msg.learning.shop.dto.ProductCategoryDto;
import ro.msg.learning.shop.exception.ProductCategoryNotFoundException;
import ro.msg.learning.shop.exception.ProductNotFoundException;
import ro.msg.learning.shop.mapper.ProductCategoryMapper;
import ro.msg.learning.shop.message.Messages;
import ro.msg.learning.shop.repository.ProductCategoryRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static ro.msg.learning.shop.message.Messages.PATCHED_SUCCESSFULLY;

@Service
@AllArgsConstructor
public class ProductCategoryService {

    private final ProductCategoryRepository productCategoryRepository;

    private final ProductCategoryMapper productCategoryMapper;


    public ProductCategoryDto createProductCategory(ProductCategoryDto productCategoryDto) {

        productCategoryRepository.save(productCategoryMapper.toEntity(productCategoryDto));
        return productCategoryDto;
    }

    public List<ProductCategoryDto> findAllProductCategories() {
        List<ProductCategory> foundProductCategories = productCategoryRepository.findAll();
        List<ProductCategoryDto> productCategoryDtos = new ArrayList<>();
        foundProductCategories.forEach(productCategory ->
        {
            productCategoryDtos.add(productCategoryMapper.toDto(productCategory));
        });
        return productCategoryDtos;
    }


    public String deleteProductCategory(UUID productCategoryId) throws ProductCategoryNotFoundException {

        if (productCategoryRepository.findById(productCategoryId).isEmpty()) {
            throw new ProductCategoryNotFoundException();
        }
        ProductCategory foundProductCategory = productCategoryRepository.findById(productCategoryId).get();
        productCategoryRepository.delete(foundProductCategory);
        if (productCategoryRepository.findById(productCategoryId).isPresent())
            return Messages.DELETE_ERROR;
        return Messages.DELETED_SUCCESSFULLY;
    }


    public String putProductCategory(UUID productCategoryId, ProductCategoryDto productCategoryDto) throws ProductNotFoundException {
        if (productCategoryRepository.findById(productCategoryId).isEmpty()) {
            throw new ProductNotFoundException();
        }
        ProductCategory foundProductCategory = productCategoryRepository.findById(productCategoryId).get();
        if (productCategoryDto.getDescription() != null && !productCategoryDto.getDescription().isEmpty()) {
            foundProductCategory.setDescription(productCategoryDto.getDescription());
        }
        if (productCategoryDto.getName() != null && !productCategoryDto.getName().isEmpty()) {
            foundProductCategory.setName(productCategoryDto.getName());
        }
        productCategoryRepository.save(foundProductCategory);
        return PATCHED_SUCCESSFULLY;
    }
}
