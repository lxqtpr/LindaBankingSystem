package dev.lxqptr.debitcardservice.utils;

import java.util.Random;

public class CvvAlgorithm {

    public String generateCVV() {
        StringBuilder cvv = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < 3; i++) {
            cvv.append(random.nextInt(10));
        }
        return cvv.toString();
    }

}
