package ro.msg.learning.shop.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.antlr.v4.runtime.misc.NotNull;
import org.hibernate.annotations.UuidGenerator;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "product")
@Builder
@AllArgsConstructor
public class Product {

    @Id
    @Column(name = "id")
    @UuidGenerator
    private UUID productId;

    @NotNull
    @Column(name = "name")
    private String name;

    @NotNull
    @Column(name = "price")
    private BigDecimal price;

    @NotNull
    @Column(name = "weight")
    private Double weight;

    @NotNull
    @Column(name = "supplier")
    private String supplier;

    @NotNull
    @Column(name = "imageurl")
    private String imageUrl;
    @NotNull
    @Column(name = "category")
    private UUID category;

    @OneToOne
    @JoinColumn(name = "id")
    private ProductCategory productCategory;

    @JsonIgnore
    @OneToMany(mappedBy = "product")
    private List<Stock> stockList;

    public Product() {

    }
}
