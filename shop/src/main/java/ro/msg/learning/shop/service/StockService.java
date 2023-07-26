package ro.msg.learning.shop.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ro.msg.learning.shop.domain.Location;
import ro.msg.learning.shop.domain.Product;
import ro.msg.learning.shop.domain.Stock;
import ro.msg.learning.shop.domain.key.StockId;
import ro.msg.learning.shop.dto.StockDto;
import ro.msg.learning.shop.exception.BadRequestException;
import ro.msg.learning.shop.mapper.StockMapper;
import ro.msg.learning.shop.message.Messages;
import ro.msg.learning.shop.repository.LocationRepository;
import ro.msg.learning.shop.repository.ProductRepository;
import ro.msg.learning.shop.repository.StockRepository;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class StockService {


    private final StockRepository stockRepository;

    private final ProductRepository productRepository;

    private final LocationRepository locationRepository;
    private final StockMapper stockMapper;


    public List<Stock> findStocksByProductId(UUID productId) {
        return stockRepository.findAllByProductProductId(productId);
    }

    public StockDto createStock(UUID productId, UUID locationId, Integer quantity) throws BadRequestException {
        if (locationRepository.findById(locationId).isEmpty()) {
            throw new BadRequestException(Messages.BAD_LOCATION);
        }
        if (productRepository.findById(productId).isEmpty()) {
            throw new BadRequestException(Messages.BAD_PRODUCT);
        }
        Location foundLocation = locationRepository.findById(locationId).get();
        Product foundProduct = productRepository.findById(productId).get();

        StockDto stockDto = StockDto.builder()
                .location(foundLocation)
                .product(foundProduct)
                .productId(productId.toString())
                .locationId(locationId.toString())
                .quantity(quantity)
                .build();
        Stock stock = stockMapper.toEntity(stockDto);
        foundProduct.getStockList().add(stock);
        foundLocation.getStock().add(stock);
        stockDto.setLocation(foundLocation);
        stockDto.setProduct(foundProduct);
        stockRepository.save(stockMapper.toEntity(stockDto));
        locationRepository.save(foundLocation);
        productRepository.save(foundProduct);
        return stockDto;
    }
}
