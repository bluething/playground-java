package io.github.bluething.playground.java.dateandtime.dbpostgresql;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.sql.*;
import java.text.DateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;

public class GetDataTest {

    @Test
    public void getTimeStampDataIsSameWithTimestampGeneratedFromString() throws SQLException {
        Timestamp expectedDateOfBirth = Timestamp.valueOf("2000-01-01 00:00:00");
        Timestamp actualDateOfBirth = null;
        try (Connection connection = DataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("SELECT name, date_of_birth FROM person WHERE id = 1");
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                actualDateOfBirth = resultSet.getTimestamp("date_of_birth");
            }
        }

        Assertions.assertEquals(expectedDateOfBirth, actualDateOfBirth);
    }

    @Test
    public void getTimeStampDataConvertedLocalDateIsSameWithLocalDateGeneratedFromString() throws SQLException {
        LocalDate expectedDateOfBirth = LocalDate.of(2000, 1, 1);
        LocalDate actualDateOfBirth = null;
        try (Connection connection = DataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("SELECT name, date_of_birth FROM person WHERE id = 1");
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                actualDateOfBirth = resultSet.getTimestamp("date_of_birth").toLocalDateTime().toLocalDate();
            }
        }

        Assertions.assertEquals(expectedDateOfBirth, actualDateOfBirth);
    }

    @Test
    public void getTimeStampDataConvertedToStringValueIsSameWithTheStringValue() throws SQLException {
        String expectedDateOfBirth = "2000-01-01";
        String actualDateOfBirth = "";
        try (Connection connection = DataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("SELECT name, date_of_birth FROM person WHERE id = 1");
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                actualDateOfBirth = resultSet.getTimestamp("date_of_birth").toLocalDateTime().toLocalDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            }
        }

        Assertions.assertEquals(expectedDateOfBirth, actualDateOfBirth);
    }

    @Test
    public void getTimeStampDataConvertedToEpochMillisIsSameWithEpochMillisFromInstant() throws SQLException {
        long expectedDateOfBirth = LocalDate.parse("2000-01-01").atStartOfDay().toInstant(ZoneOffset.UTC).toEpochMilli();
        long actualDateOfBirth = 0L;
        try (Connection connection = DataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("SELECT name, date_of_birth FROM person WHERE id = 1");
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                actualDateOfBirth = resultSet.getTimestamp("date_of_birth").toLocalDateTime().toInstant(ZoneOffset.UTC).toEpochMilli();
            }
        }

        Assertions.assertEquals(expectedDateOfBirth, actualDateOfBirth);
    }

    @Test
    public void getTimeStampDataConvertedToEpochMillisUsingGetTimeIsDifferentWithEpochMillisFromInstant() throws SQLException {
        long expectedDateOfBirth = LocalDate.parse("2000-01-01").atStartOfDay().toInstant(ZoneOffset.UTC).toEpochMilli();
        long actualDateOfBirth = 0L;
        Timestamp timestamp = null;
        try (Connection connection = DataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("SELECT name, date_of_birth FROM person WHERE id = 1");
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                timestamp = resultSet.getTimestamp("date_of_birth");
                actualDateOfBirth = timestamp.getTime();
            }
        }

        Assertions.assertNotEquals(expectedDateOfBirth, actualDateOfBirth);
    }
}
