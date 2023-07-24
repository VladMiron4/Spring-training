package ro.msg.learning.shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ro.msg.learning.shop.domain.Location;
import ro.msg.learning.shop.domain.Stock;
import ro.msg.learning.shop.domain.key.StockId;

import java.util.List;
import java.util.UUID;

@Repository
public interface StockRepository extends JpaRepository<Stock, StockId> {
    List<Stock> findAllByProductProductId(UUID productId);

    List<Stock> findAllByQuantity(Integer quantity);

    @Query(value="SELECT stock.location FROM stock\n" +
            "   WHERE  (stock.product in (:products) AND stock.quantity >=(:minimalQuantity)) \n" +
            "   GROUP BY stock.location",nativeQuery = true)
    List<UUID> findSuitableLocation(@Param("products") UUID product ,@Param("minimalQuantity")Integer minimalQuantity);
}
