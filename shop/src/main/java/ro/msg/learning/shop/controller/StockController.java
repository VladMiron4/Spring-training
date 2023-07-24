package ro.msg.learning.shop.controller;


import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ro.msg.learning.shop.dto.StockDto;
import ro.msg.learning.shop.service.StockService;

import java.util.UUID;

@RestController()
@RequestMapping("/stocks")
@AllArgsConstructor
public class StockController {


    private final StockService stockService;

    @PostMapping
    public ResponseEntity<StockDto> post(@RequestParam UUID productId, @RequestParam UUID locationId, @RequestParam Integer quantity) {
        return new ResponseEntity<>(stockService.createStock(productId, locationId, quantity), HttpStatus.OK);
    }

}
