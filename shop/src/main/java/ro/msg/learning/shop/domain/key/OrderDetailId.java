package ro.msg.learning.shop.domain.key;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.*;

import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

@Embeddable
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Data
@Builder
public class OrderDetailId implements Serializable {
    @Column(name = "product")
    private UUID productId;
    @Column(name = "\"order\"")
    private UUID orderId;
}
