package com.sk.learned;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class LocalDateTimeTest {

    public static void main(String[] args) {
        localDateTest();
    }

    private static void localDateTest() {
        LocalDate localDate = LocalDate.now();
        String strLocalDate = localDate.format(DateTimeFormatter.BASIC_ISO_DATE);
        System.out.println(strLocalDate);
        
        String strDate = "2023-01-09";
        LocalDateTime.parse(strDate);
        		
    }
}
