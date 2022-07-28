package io.github.bluething.playground.java.dateandtime.dbpostgresql;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.sql.*;
import java.time.*;
import java.time.format.DateTimeFormatter;

public class GetDataTest {

    private static Connection connection;

    @BeforeAll
    public static void beforeAll() throws SQLException {
        connection = DataSource.getConnection();
    }

    @AfterAll
    public static void afterAll() throws SQLException {
        if (!connection.isClosed()) {
            connection.close();
        }
    }

    @Test
    public void getTimeStampDataIsSameWithTimestampGeneratedFromString() throws SQLException {
        LocalDateTime expectedDateOfBirth = LocalDate.parse("2000-01-01").atStartOfDay();
        LocalDateTime actualDateOfBirth = null;
        try (PreparedStatement preparedStatement = connection.prepareStatement("SELECT name, date_of_birth FROM person WHERE id = 1");
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                actualDateOfBirth = resultSet.getTimestamp("date_of_birth").toLocalDateTime();
            }
        }

        Assertions.assertEquals(expectedDateOfBirth, actualDateOfBirth);
    }

    @Test
    public void getTimeStampDataConvertedLocalDateIsSameWithLocalDateGeneratedFromString() throws SQLException {
        LocalDate expectedDateOfBirth = LocalDate.of(2000, 1, 1);
        LocalDate actualDateOfBirth = null;
        try (PreparedStatement preparedStatement = connection.prepareStatement("SELECT name, date_of_birth FROM person WHERE id = 1");
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
        try (PreparedStatement preparedStatement = connection.prepareStatement("SELECT name, date_of_birth FROM person WHERE id = 1");
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
        try (PreparedStatement preparedStatement = connection.prepareStatement("SELECT name, date_of_birth FROM person WHERE id = 1");
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
        try (PreparedStatement preparedStatement = connection.prepareStatement("SELECT name, date_of_birth FROM person WHERE id = 1");
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                timestamp = resultSet.getTimestamp("date_of_birth");
                actualDateOfBirth = timestamp.getTime();
            }
        }

        Assertions.assertNotEquals(expectedDateOfBirth, actualDateOfBirth);
    }

    @Test
    public void getTimeStampDataAfterInsertConvertToEpochMillisIsSameWithTheInput() throws SQLException {
        String expectedDateOfBirth = "2000-10-10";
        String actualDateOfBirth = "";
        final long id = 9L;
        String sqlInsert = "INSERT INTO person (id, name, date_of_birth) VALUES (?, ?, ?)";
        String sqlSelect = "SELECT name, date_of_birth FROM person WHERE id = ?";
        String sqlDelete = "DELETE FROM person WHERE id = ?";
        Timestamp timestamp = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection.setAutoCommit(false);
            preparedStatement = connection.prepareStatement(sqlInsert);
            preparedStatement.setLong(1, id);
            preparedStatement.setString(2, "fulan 9");
            preparedStatement.setTimestamp(3, new Timestamp(LocalDate.parse(expectedDateOfBirth).atStartOfDay(ZoneId.of("UTC")).toInstant().toEpochMilli()));
            preparedStatement.executeUpdate();

            preparedStatement = connection.prepareStatement(sqlSelect);
            preparedStatement.setLong(1, id);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                timestamp = resultSet.getTimestamp("date_of_birth");
                actualDateOfBirth = timestamp.toLocalDateTime().toLocalDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            }

            preparedStatement = connection.prepareStatement(sqlDelete);
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException sqlException) {
            connection.rollback();
            throw new SQLException(sqlException);
        } finally {
            connection.commit();

            if (!resultSet.isClosed()) {
                resultSet.close();
            }

            if (!preparedStatement.isClosed()) {
                preparedStatement.close();
            }
        }

        Assertions.assertEquals(expectedDateOfBirth, actualDateOfBirth);
    }

    @Test
    public void getTimeStampDataWithRangeFilterReturnDataWithExpectedSize() throws SQLException {
        LocalDateTime from = LocalDate.parse("2000-01-01").atStartOfDay();
        LocalDateTime to = LocalDate.parse("2000-01-31").atStartOfDay().plusSeconds(86399);
        int expectedDataSize = 8;
        int actualDataSize = 0;
        try (PreparedStatement preparedStatement = connection.prepareStatement("SELECT id, date_of_birth FROM person WHERE date_of_birth BETWEEN ? AND ?")) {
            preparedStatement.setObject(1, from);
            preparedStatement.setObject(2, to);
            try (ResultSet resultSet = preparedStatement.executeQuery();) {
                while (resultSet.next()) {
                    actualDataSize++;
                }
            }
        }

        Assertions.assertEquals(expectedDataSize, actualDataSize);
    }

    @Test
    public void getTimeStampDataWithRangeFilterButWithoutExtraSecondReturnDataWithDifferentSize() throws SQLException {
        LocalDateTime from = LocalDate.parse("2000-01-01").atStartOfDay();
        LocalDateTime to = LocalDate.parse("2000-01-31").atStartOfDay();
        int expectedDataSize = 8;
        int actualDataSize = 0;
        try (PreparedStatement preparedStatement = connection.prepareStatement("SELECT id, date_of_birth FROM person WHERE date_of_birth BETWEEN ? AND ?")) {
            preparedStatement.setObject(1, from);
            preparedStatement.setObject(2, to);
            try (ResultSet resultSet = preparedStatement.executeQuery();) {
                while (resultSet.next()) {
                    actualDataSize++;
                }
            }
        }

        Assertions.assertNotEquals(expectedDataSize, actualDataSize);
    }

    @Test
    public void getTimeStampDataWithRangeFilterUsingTimestampAsBindingParamReturnDataWithDifferentSize() throws SQLException {
        Timestamp from = new Timestamp(LocalDate.parse("2000-01-01").atStartOfDay().toInstant(ZoneOffset.UTC).toEpochMilli());
        Timestamp to = new Timestamp(LocalDate.parse("2000-01-31").atStartOfDay().toInstant(ZoneOffset.UTC).toEpochMilli());
        int expectedDataSize = 8;
        int actualDataSize = 0;
        try (PreparedStatement preparedStatement = connection.prepareStatement("SELECT id, date_of_birth FROM person WHERE date_of_birth BETWEEN ? AND ?")) {
            preparedStatement.setTimestamp(1, from);
            preparedStatement.setTimestamp(2, to);
            try (ResultSet resultSet = preparedStatement.executeQuery();) {
                while (resultSet.next()) {
                    actualDataSize++;
                }
            }
        }

        Assertions.assertNotEquals(expectedDataSize, actualDataSize);
    }

    @Test
    public void getTimeStampDataWithRangeFilterUsingTimestampAsBindingParamWithExtraSecondReturnDataWithDifferentSize() throws SQLException {
        Timestamp from = new Timestamp(LocalDate.parse("2000-01-01").atStartOfDay().toInstant(ZoneOffset.UTC).toEpochMilli());
        Timestamp to = new Timestamp(LocalDate.parse("2000-01-31").atStartOfDay().toInstant(ZoneOffset.UTC).plusSeconds(86399).toEpochMilli());
        int expectedDataSize = 8;
        int actualDataSize = 0;
        try (PreparedStatement preparedStatement = connection.prepareStatement("SELECT id, date_of_birth FROM person WHERE date_of_birth BETWEEN ? AND ?")) {
            preparedStatement.setTimestamp(1, from);
            preparedStatement.setTimestamp(2, to);
            try (ResultSet resultSet = preparedStatement.executeQuery();) {
                while (resultSet.next()) {
                    actualDataSize++;
                }
            }
        }

        Assertions.assertNotEquals(expectedDataSize, actualDataSize);
    }
}
