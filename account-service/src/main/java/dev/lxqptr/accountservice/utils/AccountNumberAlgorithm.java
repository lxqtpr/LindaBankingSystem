package dev.lxqptr.accountservice.utils;

import java.util.Random;

public class AccountNumberAlgorithm {

    static String bin = "427612";

    public String getAccountNumber() {

        Random random = new Random();
        StringBuilder accountNumber = new StringBuilder(bin);

        while (accountNumber.length() < 20) {
            accountNumber.append(random.nextInt(10));
        }

        return accountNumber.toString();
    }
}
