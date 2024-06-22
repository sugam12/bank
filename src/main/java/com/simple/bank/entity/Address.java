package com.simple.bank.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Entity
public class Address extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "address_seq")
    @Column(name="ADDRESS_ID")
    private Long id;

    @Column(name="CITY")
    private String city;

    @Column(name="STATE")
    private String state;

    @Column(name="ZIP")
    private String zip;

    @Column(name="COUNTRY")
    private String country;
}
