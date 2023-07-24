package ro.msg.learning.shop.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ro.msg.learning.shop.dto.ProductCategoryDto;
import ro.msg.learning.shop.exception.ProductCategoryNotFoundException;
import ro.msg.learning.shop.exception.ProductNotFoundException;
import ro.msg.learning.shop.service.ProductCategoryService;

import java.util.List;
import java.util.UUID;

@RequestMapping("/product/categories")
@Validated
@RestController
@AllArgsConstructor
public class ProductCategoryController {


    ProductCategoryService productCategoryService;

    @PostMapping
    public ResponseEntity<ProductCategoryDto> create(@RequestBody ProductCategoryDto productCategoryDto) {
        return new ResponseEntity<>(productCategoryService.createProductCategory(productCategoryDto), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<ProductCategoryDto>> getAll() {
        return new ResponseEntity<>(productCategoryService.findAllProductCategories(), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") UUID productCategoryId) throws ProductCategoryNotFoundException {
        return new ResponseEntity<>(productCategoryService.deleteProductCategory(productCategoryId), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> put(@PathVariable("id") UUID productCategoryId, @RequestBody ProductCategoryDto productCategoryDto) throws ProductNotFoundException {
        return new ResponseEntity<>(productCategoryService.putProductCategory(productCategoryId, productCategoryDto), HttpStatus.OK);
    }
}
