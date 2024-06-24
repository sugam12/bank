package com.simple.bank.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Entity
public class Contact extends BaseEntity implements Serializable {

    @Serial
    private static final long serialVersionUID = -250240490560673028L;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "contact_seq")
    @Column(name="CONTACT_ID")
    private Long id;

    @Column(name="EMAIL_ID")
    @Email
    private String emailId;

    @Column(name="HOME_PHONE")
    private String homePhone;

    @Column(name="WORK_PHONE")
    private String workPhone;
}
