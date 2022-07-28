package io.github.bluething.playground.java.dateandtime.dbpostgresql;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.sql.*;
import java.time.LocalDate;

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
}
