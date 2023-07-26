package ro.msg.learning.shop.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
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
@NoArgsConstructor
public class Product {

    @Id
    @Column(name = "id")
    @UuidGenerator
    private UUID productId;

    private String name;


    private BigDecimal price;


    private Double weight;


    private String supplier;

    @Column(name = "imageurl")
    private String imageUrl;

    private UUID category;

    @OneToOne
    @JoinColumn(name = "id")
    private ProductCategory productCategory;

    @JsonIgnore
    @OneToMany(mappedBy = "product")
    private List<Stock> stockList;

}
