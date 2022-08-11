package io.github.bluething.playground.java.dateandtime.date;

import org.junit.jupiter.api.Test;

import java.time.*;

public class ClockTest {
    @Test
    public void create() {
        Clock clockUTC = Clock.systemUTC();
        System.out.println(clockUTC);

        Clock clockDefault = Clock.systemDefaultZone();
        System.out.println(clockDefault);

        Clock clockCustomZone = Clock.system(ZoneId.of("Asia/Jakarta"));
        System.out.println(clockCustomZone);
    }

    @Test
    public void instant() throws InterruptedException {
        Clock clock = Clock.system(ZoneId.of("Asia/Jakarta"));

        Instant instant1  = clock.instant();
        System.out.println(instant1);

        Thread.sleep(1000);
        Instant instant2 = clock.instant();
        System.out.println(instant2);
    }

    // A clock providing access to the current instant, date and time using a time-zone.
    // Best practice for applications is to pass a Clock into any method that requires the current instant.
    @Test
    public void fromClock() {
        Clock clock = Clock.system(ZoneId.of("Asia/Jakarta"));

        Year year = Year.now(clock);
        System.out.println(year);

        YearMonth yearMonth = YearMonth.now(clock);
        System.out.println(yearMonth);

        MonthDay monthDay = MonthDay.now(clock);
        System.out.println(monthDay);

        LocalTime localTime = LocalTime.now(clock);
        System.out.println(localTime);

        LocalDateTime localDateTime = LocalDateTime.now(clock);
        System.out.println(localDateTime);

        OffsetTime offsetTime = OffsetTime.now(clock);
        System.out.println(offsetTime);

        OffsetDateTime offsetDateTime = OffsetDateTime.now(clock);
        System.out.println(offsetDateTime);

        ZonedDateTime zonedDateTime = ZonedDateTime.now(clock);
        System.out.println(zonedDateTime);
    }
}
