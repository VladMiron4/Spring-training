package ro.msg.learning.shop.domain;

import jakarta.persistence.*;
import lombok.*;
import org.antlr.v4.runtime.misc.NotNull;
import org.hibernate.annotations.UuidGenerator;

import java.time.LocalDate;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "order")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Order {

    @OneToMany(mappedBy = "order")
    Set<OrderDetail> orderDetail;
    @Id
    @Column(name = "id")
    @UuidGenerator
    private UUID orderId;
    @Column(name = "customer")
    private UUID customerId;
    @NotNull
    @Column(name = "createdat")
    private LocalDate date;
    @NotNull
    @Column(name = "address_city")
    private String addressCity;
    @NotNull
    @Column(name = "address_county")
    private String addressCounty;
    @NotNull
    @Column(name = "address_country")
    private String addressCountry;
    @NotNull
    @Column(name = "address_streetaddress")
    private String addressStreet;
    @ManyToOne
    @MapsId("customerId")
    @JoinColumn(name="id")
    private Customer Customer;
}
