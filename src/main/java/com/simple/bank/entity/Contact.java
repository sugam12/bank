package com.simple.bank.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Entity
public class Contact {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name="CONTACT_ID")
    private Long id;

    private String emailId;

    private String homePhone;

    private String workPhone;
}
