package ro.msg.learning.shop.domain.key;


import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.*;

import java.io.Serializable;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Embeddable
@Data
@Builder
public class StockId implements Serializable {
    @Column(name = "product")
    private UUID productId;
    @Column(name = "location")
    private UUID locationId;
}
