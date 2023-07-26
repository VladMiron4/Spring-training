package ro.msg.learning.shop.domain;

import jakarta.persistence.*;
import lombok.*;
import ro.msg.learning.shop.domain.key.StockId;

@Getter
@Setter
@Entity
@Table(name = "stock")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Stock {

    @EmbeddedId
    private StockId Id;


    private Integer quantity;

    @ManyToOne
    @MapsId("locationId")
    @JoinColumn(name = "location")
    private Location location;

    @ManyToOne
    @MapsId(value = "productId")
    @JoinColumn(name = "product")
    private Product product;
}
