package io.github.bluething.playground.java.dateandtime.date;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.Month;
import java.time.ZoneId;

public class LocalDateTest {
    @Test
    public void create() {
        LocalDate localDate = LocalDate.now();
        System.out.println(localDate);

        LocalDate localDate1 = LocalDate.parse("2022-07-25");
        System.out.println(localDate1);

        LocalDate localDate2 = LocalDate.of(1985, Month.SEPTEMBER, 1);
        System.out.println(localDate2);
    }

    @Test
    public void modify() {
        LocalDate localDate = LocalDate.now();
        LocalDate localDate1 = localDate.plusYears(100);
        LocalDate localDate2 = localDate.plusDays(100);

        System.out.println(localDate);
        System.out.println(localDate1);
        System.out.println(localDate2);
    }

    @Test
    public void get() {
        LocalDate localDate = LocalDate.now();
        System.out.println(localDate.getYear());
        System.out.println(localDate.getMonth());
        System.out.println(localDate.getDayOfMonth());
        System.out.println(localDate.getDayOfWeek());
        System.out.println(localDate.atStartOfDay(ZoneId.of("UTC")).toInstant().toEpochMilli());
        System.out.println(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant().toEpochMilli());
    }
}
