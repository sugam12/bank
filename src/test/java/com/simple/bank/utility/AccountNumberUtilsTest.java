package com.simple.bank.utility;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.Year;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AccountNumberUtilsTest {

    @Test
    public void testGenerate() {
        Year currentYear = Year.now();
        AccountNumberUtils accountNumberUtils = new AccountNumberUtils();
        String accountNumber = accountNumberUtils.generate();
        String expectedYear = accountNumber.substring(0,4);
        assertEquals(String.valueOf(currentYear), expectedYear);
    }
}
