package com.simple.bank.repository;

import com.simple.bank.entity.Account;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;

import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, Long > {
   // @Lock(LockModeType.OPTIMISTIC_FORCE_INCREMENT)
    Optional<Account> findByAccountNumber(String fromAccountNumber);
}
