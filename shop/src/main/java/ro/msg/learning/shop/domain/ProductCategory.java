package ro.msg.learning.shop.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;
import org.hibernate.annotations.UuidGenerator;

import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "productcategory")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductCategory {

    @Id
    @Column(name = "id")
    @UuidGenerator
    private UUID productCategoryId;


    private String name;


    private String description;

    public ProductCategory(String name, String description) {
        this.name = name;
        this.description = description;
    }

}
