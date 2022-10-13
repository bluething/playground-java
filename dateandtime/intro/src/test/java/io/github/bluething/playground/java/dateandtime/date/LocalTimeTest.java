package io.github.bluething.playground.java.dateandtime.date;

import org.junit.jupiter.api.Test;

import java.time.LocalTime;

public class LocalTimeTest {

    @Test
    public void create() {
        LocalTime localTime1 = LocalTime.now();
        LocalTime localTime2 = LocalTime.parse("20:20");
        LocalTime localTime3 = LocalTime.of(22, 22);
        LocalTime localTime4 = LocalTime.ofSecondOfDay(86399);

        System.out.println(localTime1);
        System.out.println(localTime2);
        System.out.println(localTime3);
        System.out.println(localTime4);
    }

    @Test
    public void modify() {
        LocalTime localTime = LocalTime.now();
        LocalTime localTime1 = localTime.plusHours(2);
        LocalTime localTime2 = localTime.plusHours(2).minusMinutes(10).minusSeconds(10);

        System.out.println(localTime);
        System.out.println(localTime1);
        System.out.println(localTime2);
    }

    @Test
    public void change() {
        LocalTime localTime = LocalTime.now();
        LocalTime localTime1 = localTime.withHour(10);
        LocalTime localTime2 = localTime.withMinute(0);

        System.out.println(localTime);
        System.out.println(localTime1);
        System.out.println(localTime2);
    }

    @Test
    public void get() {
        LocalTime localTime = LocalTime.now();

        System.out.println(localTime);
        System.out.println("Hour: " + localTime.getHour());
        System.out.println("Minute: " + localTime.getMinute());
        System.out.println("Second: " + localTime.getSecond());
        System.out.println("Nanos: " + localTime.getNano());
    }

    @Test
    public void transform() {
        LocalTime localTime = LocalTime.now();
        System.out.println(localTime);
        System.out.println(localTime.toSecondOfDay());
    }
}
