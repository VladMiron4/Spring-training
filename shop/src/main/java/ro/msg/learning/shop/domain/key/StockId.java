package ro.msg.learning.shop.domain.key;


import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Embeddable
@Data
@Builder
public class StockId implements Serializable {

    @Column(name = "product")
    private UUID productId;
    @Column(name = "location")
    private UUID locationId;

    @Override
    public boolean equals(Object obj) {
        if (this.getClass() != obj.getClass()) {
            return false;
        }
        StockId stockObject = (StockId) obj;
        if (!stockObject.locationId.equals(this.locationId)) {
            return false;
        }
        return stockObject.productId.equals(this.productId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(productId, locationId);
    }
}
