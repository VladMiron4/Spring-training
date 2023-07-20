package ro.msg.learning.shop.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.antlr.v4.runtime.misc.NotNull;
import org.hibernate.annotations.UuidGenerator;

import java.util.UUID;

@Entity
@Data
@Table(name="customer")
@AllArgsConstructor
@Builder
public class Customer {

    @Id
    @Column(name="id")
    @UuidGenerator
    private UUID customerId;

    @NotNull
    @Column(name="firstname")
    private String firstName;

    @NotNull
    @Column(name="lastname")
    private String lastName;

    @NotNull
    @Column(name="username")
    private String userName;

    @NotNull
    @Column(name="emailaddress")
    private String email;

    @NotNull
    @Column(name="password")
    private String password;

    @OneToOne
    @JoinColumn(name="id")
    private Order order;

}
