package ro.msg.learning.shop.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ro.msg.learning.shop.dto.CreateOrderDto;
import ro.msg.learning.shop.dto.OrderDto;
import ro.msg.learning.shop.exception.LocationNotFoundException;
import ro.msg.learning.shop.exception.NegativeQuantityException;
import ro.msg.learning.shop.exception.ProductNotFoundException;
import ro.msg.learning.shop.service.OrderService;


@RequestMapping("/orders")
@Validated
@RestController
@AllArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    public ResponseEntity<OrderDto> post(@RequestBody @Validated CreateOrderDto createOrderDto) throws ProductNotFoundException, LocationNotFoundException, NegativeQuantityException {
        return new ResponseEntity<>(orderService.create(createOrderDto), HttpStatus.OK);
    }
}
