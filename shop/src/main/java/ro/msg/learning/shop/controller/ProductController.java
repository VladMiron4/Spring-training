package ro.msg.learning.shop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ro.msg.learning.shop.dto.ProductDto;
import ro.msg.learning.shop.exception.ProductCategoryNotFoundException;
import ro.msg.learning.shop.exception.ProductNotFoundException;
import ro.msg.learning.shop.service.ProductService;

import java.util.List;
import java.util.UUID;

@RequestMapping("/product")
@Validated
@RestController
public class ProductController {
    @Autowired
   private ProductService productService;

    @PostMapping("/new")
    public ResponseEntity<ProductDto> createProduct(@RequestBody @Validated ProductDto productDto,@RequestParam String productCategoryName) throws ProductCategoryNotFoundException {

        return new ResponseEntity<ProductDto>(productService.createProduct(productDto,productCategoryName), HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public String deleteProduct(@PathVariable("id") UUID productId) throws ProductNotFoundException {
        return productService.deleteProduct(productId);
    }
    @GetMapping
    public ResponseEntity<List<ProductDto>>getAllProducts() {
        return new ResponseEntity<>(productService.getAllProducts(),HttpStatus.OK);
    }
    @PutMapping("/{id}")
    public String putProduct(@PathVariable ("id") UUID productId, @RequestBody @Validated ProductDto productDto) throws ProductNotFoundException {
        return productService.patchProduct(productId,productDto);
    }

}
