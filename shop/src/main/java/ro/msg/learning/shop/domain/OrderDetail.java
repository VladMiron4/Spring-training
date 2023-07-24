package ro.msg.learning.shop.domain;

import jakarta.persistence.*;
import lombok.*;
import ro.msg.learning.shop.domain.key.OrderDetailId;

import java.util.UUID;


@Getter
@Setter
@Entity
@Table(name = "orderdetail")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderDetail {


    @EmbeddedId
    private OrderDetailId orderDetailId;


    private Integer quantity;

    @Column(name = "shippedfrom")
    private UUID locationId;

    @ManyToOne
    @MapsId("productId")
    @JoinColumn(name = "product")
    private Product product;

    @ManyToOne
    @MapsId("orderId")
    @JoinColumn(name = "\"order\"")
    private Order order;

}
