package dev.lxqptr.debitcardservice.utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DateAlgorithm {

    public String generateDate() {

        LocalDate currentDate = LocalDate.now();
        LocalDate expirationDate = currentDate.plusYears(4);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/yyyy");
        return expirationDate.format(formatter);

    }

}
