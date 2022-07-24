package io.github.bluething.playground.java.dateandtime.date;

import org.junit.jupiter.api.Test;

import java.util.Calendar;

public class CalendarTest {
    @Test
    public void create() {
        Calendar calendar = Calendar.getInstance();

        System.out.println(calendar.getTime());
    }

    @Test
    public void modify() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, 2000);
        calendar.set(Calendar.MONTH, Calendar.FEBRUARY);
        calendar.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);

        System.out.println(calendar.getTime());
    }
}
