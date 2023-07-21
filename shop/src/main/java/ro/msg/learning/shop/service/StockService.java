package ro.msg.learning.shop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.msg.learning.shop.domain.Location;
import ro.msg.learning.shop.domain.Product;
import ro.msg.learning.shop.domain.Stock;
import ro.msg.learning.shop.domain.key.StockId;
import ro.msg.learning.shop.dto.StockDto;
import ro.msg.learning.shop.exception.LocationNotFoundException;
import ro.msg.learning.shop.exception.ProductNotFoundException;
import ro.msg.learning.shop.mapper.StockMapper;
import ro.msg.learning.shop.repository.LocationRepository;
import ro.msg.learning.shop.repository.ProductRepository;
import ro.msg.learning.shop.repository.StockRepository;

import java.util.List;
import java.util.UUID;

@Service
public class StockService {

    
    private final StockRepository stockRepository;
    
    private final ProductRepository productRepository;
    
    private final LocationRepository locationRepository;
    private final StockMapper stockMapper;
    public StockService(StockRepository stockRepository,ProductRepository productRepository,LocationRepository locationRepository
    ,StockMapper stockMapper){
        this.stockRepository=stockRepository;
        this.productRepository=productRepository;
        this.locationRepository=locationRepository;
        this.stockMapper=stockMapper;
    }

    public List<Stock> findStocksByProductId(UUID productId) {
        return stockRepository.findAllByProductProductId(productId);
    }

    public StockDto createStock(UUID productId, UUID locationId, Integer quantity) throws ProductNotFoundException, LocationNotFoundException {
        if (locationRepository.findById(locationId).isEmpty()) {
            throw new LocationNotFoundException();
        }
        if (productRepository.findById(productId).isEmpty()) {
            throw new ProductNotFoundException();
        }
        Location foundLocation = locationRepository.findById(locationId).get();
        Product foundProduct = productRepository.findById(productId).get();

        StockId stockId = StockId.builder()
                .productId(foundProduct.getProductId())
                .locationId(foundLocation.getLocationId())
                .build();
        StockDto stockDto = StockDto.builder()
                .location(foundLocation)
                .product(foundProduct)
                .stockId(stockId)
                .quantity(quantity)
                .build();
        stockRepository.save(stockMapper.toEntity(stockDto));
        return stockDto;
    }
}
