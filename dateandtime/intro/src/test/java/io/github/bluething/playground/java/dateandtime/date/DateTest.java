package io.github.bluething.playground.java.dateandtime.date;

import org.junit.jupiter.api.Test;

import java.util.Date;

public class DateTest {
    @Test
    public void create() {
        Date date1 = new Date();
        Date date2 = new Date(System.currentTimeMillis());

        System.out.println(date1);
        System.out.println(date2);
    }
}
