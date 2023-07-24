package ro.msg.learning.shop.controller;

import lombok.AllArgsConstructor;
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

@RequestMapping("/products")
@Validated
@RestController
@AllArgsConstructor
public class ProductController {

    private final ProductService productService;




    @PostMapping
    public ResponseEntity<ProductDto> create(@RequestBody @Validated ProductDto productDto) throws ProductCategoryNotFoundException {
        return new ResponseEntity<ProductDto>(productService.createProduct(productDto), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") UUID productId) throws ProductNotFoundException {
        return new ResponseEntity<>(productService.deleteProduct(productId), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<ProductDto>> getAll() {
        return new ResponseEntity<>(productService.getAllProducts(), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> put(@PathVariable("id") UUID productId, @RequestBody @Validated ProductDto productDto) throws ProductNotFoundException {
        return new ResponseEntity<>(productService.patchProduct(productId, productDto), HttpStatus.OK);
    }
}
