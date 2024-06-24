package com.simple.bank.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Entity
@Setter
@Getter
public class Account extends BaseEntity implements Serializable {

    @Serial
    private static final long serialVersionUID = -9151839229156419306L;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "account_seq")
    private Long accountId;

    @Column(name = "CUSTOMER_NUMBER")
    private String customerNumber;

    @Column(name = "ACCOUNT_NUMBER")
    private String accountNumber;

    @Column(name = "ACCOUNT_TYPE")
    private String accountType;

    @Column(name = "CURRENT_BALANCE")
    private double currentBalance;

    @Column(name = "DATE_OPENED")
    private Date dateOpened;

   /* @Version
    private Long version;*/

    @Column(name = "DATE_CLOSED")
    private Date dateClosed;
}
