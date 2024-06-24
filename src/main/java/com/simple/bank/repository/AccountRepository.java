package com.simple.bank.repository;

import com.simple.bank.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, Long > {
    Optional<Account> findByAccountNumber(String fromAccountNumber);
}
