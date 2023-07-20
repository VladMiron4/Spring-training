package ro.msg.learning.shop.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.antlr.v4.runtime.misc.NotNull;
import org.hibernate.annotations.UuidGenerator;

import java.util.Set;
import java.util.UUID;

@Data
@Entity
@Table(name="productcategory")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductCategory{

    @Id
    @Column(name="id")
    @UuidGenerator
    private UUID productCategoryId;

    @NotNull
    @Column(name="name")
    private String name;

    @NotNull
    @Column(name="description")
    private String description;

    public ProductCategory(String name, String description){
        this.name=name;
        this.description=description;
    }

}
