package com.simple.bank.entity;

import com.simple.bank.constant.TransactionTypeEnum;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Setter
@Getter
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "transaction_seq")
    private Long transactionId;

    @Column(name = "FROM_ACCOUNT_NUMBER")
    private String fromAccountNumber;

    @Column(name = "TRANSACTION_TYPE")
    private TransactionTypeEnum transactionTypeEnum;

    @Column(name = "AMOUNT")
    private double amount;

    @Column(name = "TO_ACCOUNT_NAME")
    private String toAccountName;

    @Column(name = "TO_ACCOUNT_NUMBER")
    private String toAccountNumber;

    //@Temporal(TemporalType.TIME)
    @Column(name = "TRANSACTION_INITIATED_DATE")
    private LocalDateTime transactionInitiatedDate;

    //@Temporal(TemporalType.TIME)
    @Column(name = "TRANSACTION_COMPLETED_DATE")
    private LocalDateTime transactionCompletedDate;
}
