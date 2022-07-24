package io.github.bluething.playground.java.dateandtime.date;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class TimeZoneTest {
    @Test
    public void get() {
        TimeZone timeZoneDefault = TimeZone.getDefault();
        System.out.println(timeZoneDefault);

        TimeZone timeZoneUTC = TimeZone.getTimeZone("UTC");
        System.out.println(timeZoneUTC);

        String[] availableTimeZones = TimeZone.getAvailableIDs();
        Arrays.stream(availableTimeZones).forEach(availableTimeZone -> System.out.println(availableTimeZone));
    }

    @Test
    public void createDate() {
        Date date = new Date();
        System.out.println(date.toString());
        System.out.println(date.toGMTString());
    }

    @Test
    public void createCalendar() {
        Calendar defaultTimeZone = Calendar.getInstance();
        Calendar utcCalendar = Calendar.getInstance(TimeZone.getTimeZone("UTC"));

        // Using local timeZone
        System.out.println(defaultTimeZone.getTime());
        System.out.println(utcCalendar.getTime());

        System.out.println(defaultTimeZone.get(Calendar.HOUR_OF_DAY));
        System.out.println(utcCalendar.get(Calendar.HOUR_OF_DAY));
    }
}
