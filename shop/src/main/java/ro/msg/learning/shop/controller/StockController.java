package ro.msg.learning.shop.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ro.msg.learning.shop.dto.StockDto;
import ro.msg.learning.shop.exception.LocationNotFoundException;
import ro.msg.learning.shop.exception.ProductNotFoundException;
import ro.msg.learning.shop.service.StockService;

import java.util.UUID;

@RestController()
@Validated
@RequestMapping("/stocks")
public class StockController {



    private final StockService stockService;
    public StockController(StockService stockService){
        this.stockService=stockService;
    }

    @PostMapping
    public ResponseEntity<StockDto> post(@RequestParam UUID productId, @RequestParam UUID locationId, @RequestParam Integer quantity) throws ProductNotFoundException, LocationNotFoundException {
        return new ResponseEntity<>(stockService.createStock(productId, locationId, quantity), HttpStatus.OK);
    }

}
