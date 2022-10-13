package io.github.bluething.playground.java.dateandtime.dateandtimeinspringmvc.request;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

@RestController
public class LocalTimeController {
    @GetMapping("/localtime/{time}")
    public String getStringFromLocalTimeUsingISODateFormatter(@PathVariable @DateTimeFormat(pattern = "HH:mm") LocalTime time) {
        return time.format(DateTimeFormatter.ofPattern("HH:mm"));
    }

    @GetMapping("/integerastime/{time}")
    public String getStringFromIntegerUsingPatternDateFormatter(@PathVariable @DateTimeFormat(pattern = "HH:mm") Integer time) {
        return LocalTime.ofSecondOfDay(time).format(DateTimeFormatter.ofPattern("HH:mm"));
    }
}
