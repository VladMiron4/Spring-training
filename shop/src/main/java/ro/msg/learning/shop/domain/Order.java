package ro.msg.learning.shop.domain;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.UuidGenerator;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "\"order\"")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Order {



    @Id
    @Column(name = "id")
    @UuidGenerator
    private UUID orderId;

    @Column(name = "customer")
    private UUID customerId;

    @Column(name = "createdat")
    @Builder.Default
    private LocalDate date = LocalDate.now();

    @Column(name = "address_city")
    private String addressCity;

    @Column(name = "address_county")
    private String addressCounty;

    @Column(name = "address_country")
    private String addressCountry;

    @Column(name = "address_streetaddress")
    private String addressStreet;
    @ManyToOne
    @MapsId("customerId")
    @JoinColumn(name = "id")
    private Customer Customer;
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderDetail> orderDetail;
}
