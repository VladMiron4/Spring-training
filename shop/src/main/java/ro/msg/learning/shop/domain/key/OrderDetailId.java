package ro.msg.learning.shop.domain.key;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.*;

import java.io.Serializable;
import java.util.UUID;

@Embeddable
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Getter
@Setter
@Builder
public class OrderDetailId implements Serializable {
    @Column(name = "product")
    private UUID productId;
    @Column(name = "\"order\"")
    private UUID orderId;
}
