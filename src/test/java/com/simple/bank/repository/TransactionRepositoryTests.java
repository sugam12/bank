package com.simple.bank.repository;

import com.simple.bank.constant.TransactionTypeEnum;
import com.simple.bank.entity.Transaction;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(value = false)
@TestPropertySource("classpath:test_local.properties")
@RunWith(SpringRunner.class)
public class TransactionRepositoryTests {

    @Autowired
    private TransactionRepository transactionRepository;

    @Test
    public void testAddTransactionToDB() {
        Transaction transaction = new Transaction();
        transaction.setAmount(100.00);
        transaction.setFromAccountNumber("20245255");
        transaction.setToAccountNumber("20245256");
        transaction.setTransactionTypeEnum(TransactionTypeEnum.TRANSFER);
        transaction.setTransactionInitiatedDate(LocalDateTime.now());
        transaction.setTransactionCompletedDate(LocalDateTime.now());

        Transaction savedTransaction = transactionRepository.save(transaction);
        assertThat(savedTransaction).isNotNull();
    }
}
