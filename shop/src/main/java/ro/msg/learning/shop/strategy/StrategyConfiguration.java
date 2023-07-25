package ro.msg.learning.shop.strategy;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ro.msg.learning.shop.exception.StrategyNotFoundException;
import ro.msg.learning.shop.mapper.OrderDetailMapper;
import ro.msg.learning.shop.mapper.OrderMapper;
import ro.msg.learning.shop.mapper.StockMapper;
import ro.msg.learning.shop.repository.*;

@Configuration
@RequiredArgsConstructor
public class StrategyConfiguration {
    private final StockRepository stockRepository;
    private final LocationRepository locationRepository;


    @Value("${strategy}")
    private String strategy;


    @Bean
    public OrderLocationStrategy configure() throws StrategyNotFoundException {
        if (!strategy.equals("single") && !strategy.equals("abundant")) {
            throw new StrategyNotFoundException();
        }
        OrderLocationStrategy orderLocationStrategy;
        if (strategy.equals("single")) {
            orderLocationStrategy = new SingleLocationOrder(stockRepository,locationRepository);
        } else {
            orderLocationStrategy = new MostAbundantLocation(stockRepository,locationRepository);
        }
        return orderLocationStrategy;
    }
}
