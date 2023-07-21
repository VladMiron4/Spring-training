package ro.msg.learning.shop.domain;

import jakarta.persistence.*;
import lombok.*;
import org.antlr.v4.runtime.misc.NotNull;
import org.hibernate.annotations.UuidGenerator;

import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "location")
@Builder
public class Location {
    @OneToMany(mappedBy = "location")
    Set<Stock> stock;
    @Id
    @Column(name = "id")
    @UuidGenerator
    private UUID locationId;

    @NotNull
    @Column(name = "name")
    private String name;

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
}
