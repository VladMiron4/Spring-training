package ro.msg.learning.shop.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.antlr.v4.runtime.misc.NotNull;
import org.hibernate.annotations.UuidGenerator;

import java.time.LocalDate;
import java.util.Set;
import java.util.UUID;

@Data
@Entity
@Table(name="order")
@AllArgsConstructor
@Builder
public class Order{

    @Id
    @Column(name="id")
    @UuidGenerator
    private UUID orderId;

    @Column(name="customer")
    private UUID customerId;

    @NotNull
    @Column(name="createdat")
    private LocalDate date;

    @NotNull
    @Column(name="address_city")
    private String addressCity;

    @NotNull
    @Column(name="address_county")
    private String addressCounty;

    @NotNull
    @Column(name="address_country")
    private String addressCountry;

    @NotNull
    @Column(name="address_streetaddress")
    private String addressStreet;

    @OneToMany(mappedBy = "order")
    Set<OrderDetail> orderDetail;

    @OneToOne(mappedBy = "order")
    private Customer Customer;
}
