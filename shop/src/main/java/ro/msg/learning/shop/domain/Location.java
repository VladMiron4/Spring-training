package ro.msg.learning.shop.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.antlr.v4.runtime.misc.NotNull;
import org.hibernate.annotations.UuidGenerator;

import java.util.UUID;
import java.util.Set;

@Data
@AllArgsConstructor
@Entity
@Table(name="location")
@Builder
public class Location {
    @Id
    @Column(name="id")
    @UuidGenerator
    private UUID locationId;

    @NotNull
    @Column(name="name")
    private String name;

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

    @OneToMany(mappedBy = "location")
    Set<Stock> stock;
}
