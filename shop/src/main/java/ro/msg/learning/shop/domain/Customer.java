package ro.msg.learning.shop.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.UuidGenerator;

import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Setter
@Table(name = "customer")
@AllArgsConstructor
@Builder
public class Customer {

    @Id
    @Column(name = "id")
    @UuidGenerator
    private UUID customerId;

    @Column(name = "firstname")
    private String firstName;

    @Column(name = "lastname")
    private String lastName;

    @Column(name = "username")
    private String userName;


    @Column(name = "emailaddress")
    private String email;


    private String password;

    @OneToMany
    @JoinColumn(name = "id")
    private List<Order> order;

}
