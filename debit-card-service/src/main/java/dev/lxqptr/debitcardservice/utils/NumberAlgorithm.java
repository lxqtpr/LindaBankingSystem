package dev.lxqptr.debitcardservice.utils;

import java.util.Random;

public class NumberAlgorithm {

    private static final String PAYMENT_SYSTEM_IDENTIFIER = "5";

    public String generateCardNumber() {

        Random random = new Random();
        StringBuilder cardNumber = new StringBuilder();
        cardNumber.append(PAYMENT_SYSTEM_IDENTIFIER);
        for (int i = 0; i < 14; i++) {
            cardNumber.append(random.nextInt(10));
        }
        cardNumber.append(getLuhnDigit(cardNumber.toString()));
        return cardNumber.toString();
    }

    private int getLuhnDigit(String cardNumber) {

        int sum = 0;
        boolean alternate = false;
        for (int i = cardNumber.length() - 1; i >= 0; i--) {
            int digit = Integer.parseInt(cardNumber.substring(i, i + 1));
            if (alternate) {
                digit *= 2;
                if (digit > 9) {
                    digit -= 9;
                }
            }
            sum += digit;
            alternate = !alternate;
        }
        return (10 - (sum % 10)) % 10;
    }

}
