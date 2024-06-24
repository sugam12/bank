package com.simple.bank.repository;

import com.simple.bank.entity.Account;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(value = false)
@TestPropertySource("classpath:test_local.properties")
@RunWith(SpringRunner.class)
public class AccountRepositoryTest {
    @Autowired
    private AccountRepository accountRepository;


    @Test
    public void testAddAccount() {
        Account account = getAccountDataToSave();
        Account savedAccount = accountRepository.save(account);
        assertThat(savedAccount).isNotNull();
        Optional<Account> accountById = accountRepository.findById(account.getAccountId());
        Assertions.assertTrue(accountById.isPresent());
    }

    private Account getAccountDataToSave() {
        Account account = new Account();
        account.setCurrentBalance(100.00);
        account.setCustomerNumber("1234567");
        account.setAccountNumber("2024234345");
        return account;
    }

    @Test
    public void testFindByAccountAccountNumber() {
        Account account = getAccountDataToSave();
        accountRepository.save(account);
        Optional<Account> accountById = accountRepository.findByAccountNumber(account.getAccountNumber());
        Assertions.assertTrue(accountById.isPresent());
    }
}
