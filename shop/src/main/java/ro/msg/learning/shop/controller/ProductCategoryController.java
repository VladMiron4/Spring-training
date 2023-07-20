package ro.msg.learning.shop.controller;

import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
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

@RequestMapping("/product/category")
@Validated
@RestController
public class ProductCategoryController {

    @Autowired
    ProductCategoryService productCategoryService;
    @PostMapping("/new")
    public ResponseEntity<ProductCategoryDto> createProductCategory(@RequestBody @Validated ProductCategoryDto productCategoryDto){
        return new ResponseEntity<>(productCategoryService.createProductCategory(productCategoryDto), HttpStatus.OK);
    }
    @GetMapping
    public ResponseEntity<List<ProductCategoryDto>>getAllProductCategories(){
        return new ResponseEntity<>(productCategoryService.findAllProductCategories(),HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public String deleteProductCategory(@PathVariable("id") UUID productCategoryId) throws ProductCategoryNotFoundException {
        return productCategoryService.deleteProductCategory(productCategoryId);
    }
    @PutMapping("/{id}")
    public String putProductCategory(@PathVariable("id")UUID productCategoryId, @RequestBody ProductCategoryDto productCategoryDto)throws ProductNotFoundException{
        return productCategoryService.putProductCategory(productCategoryId,productCategoryDto);
    }
}
