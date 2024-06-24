package com.simple.bank;

import com.simple.bank.repository.AccountRepositoryTest;
import com.simple.bank.repository.CustomerRepositoryTest;
import com.simple.bank.repository.TransactionRepositoryTests;
import com.simple.bank.response.BankResponseTest;
import com.simple.bank.service.helper.EntityDtoConversionHelperTest;
import com.simple.bank.utility.AccountNumberUtilsTest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        AccountRepositoryTest.class,
        CustomerRepositoryTest.class,
        TransactionRepositoryTests.class,
        BankResponseTest.class,
        EntityDtoConversionHelperTest.class,
        AccountNumberUtilsTest.class
})
public class BankApplicationTests {
    @Test
    public void contextLoads() {
    }
}
