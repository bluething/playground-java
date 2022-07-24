package io.github.bluething.playground.java.dateandtime.date;

import org.junit.jupiter.api.Test;

import java.time.Instant;

public class InstantTest {
    @Test
    public void create() {
        Instant instant = Instant.now();
        System.out.println(instant);

        Instant instant1 = Instant.ofEpochMilli(1L);
        System.out.println(instant1);
    }

    @Test
    public void get() {
        Instant instant = Instant.now();

        System.out.println(instant.toEpochMilli());
        System.out.println(System.currentTimeMillis());
        System.out.println(instant.getEpochSecond());
        System.out.println(instant.getNano());
    }
}
