package com.simple.bank.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Customer extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "customer_seq")
    @Column(name = "CUSTOMER_ID")
    private Long customerId;

    @Column(name = "FIRST_NAME")
    private String firstName;

    @Column(name = "MIDDLE_NAME")
    private String middleName;

    @Column(name = "LAST_NAME")
    private String lastName;

    @Column(name = "CUSTOMER_NUMBER")
    private Long customerNumber;

    @ManyToOne(cascade = CascadeType.ALL)
    private Address address;

    @OneToOne(cascade = CascadeType.ALL)
    private Contact contact;
}
