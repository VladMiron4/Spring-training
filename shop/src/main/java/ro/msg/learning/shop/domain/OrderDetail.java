package ro.msg.learning.shop.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.antlr.v4.runtime.misc.NotNull;
import org.hibernate.annotations.NotFound;
import org.springframework.beans.factory.annotation.Qualifier;
import ro.msg.learning.shop.domain.key.OrderDetailId;

import java.util.UUID;


@Data
@Entity
@Table(name="orderdetail")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderDetail{


    @EmbeddedId
    private OrderDetailId orderDetailId;

    @NotNull
    @Column(name="quantity")
    private Integer quantity;

    @Column(name="shippedfrom")
    private UUID locationId;

    @ManyToOne
    @MapsId("productId")
    @JoinColumn(name="product")
    private Product product;

    @ManyToOne
    @MapsId("orderId")
    @JoinColumn(name="order")
    private Order order;

}
