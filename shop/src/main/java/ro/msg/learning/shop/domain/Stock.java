package ro.msg.learning.shop.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.antlr.v4.runtime.misc.NotNull;
import ro.msg.learning.shop.domain.key.StockId;

@Data
@Entity
@Table(name="stock")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Stock {

    @EmbeddedId
    private StockId Id;

    @NotNull
    @Column(name="quantity")
    private Integer quantity;

    @ManyToOne
    @MapsId("locationId")
    @JoinColumn(name="location")
    private Location location;

    @ManyToOne
    @MapsId(value = "productId")
    @JoinColumn(name="product")
    private Product product;
}
