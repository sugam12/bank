package com.simple.bank.utility;


import java.time.Year;

public class AccountNumberUtils {
    public String generate() {
        Year currentYear = Year.now();
        int min = 100000;
        int max = 999999;

        int random = (int) Math.floor(Math.random() * (max - min + 1));
        String year = String.valueOf(currentYear);
        String randomNumber = String.valueOf(random);
        return year + randomNumber;
    }
}
