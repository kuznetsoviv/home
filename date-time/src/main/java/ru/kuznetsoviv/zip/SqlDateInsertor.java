package ru.kuznetsoviv.zip;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;

public class SqlDateInsertor {

    private static final String SQL = "INSERT INTO check_date (first, second, third) VALUES (?, ?, ?)";

    static {
        try {
            Class.forName("org.postgesql.Driver");
        } catch (ClassNotFoundException e) {
            throw new IllegalStateException(e);
        }
    }

    /**
     * Установить sql данные времени и даты.
     * java sql date - используется для хранения в бд только даты
     * java sql time - используется для хранения в бд только времени
     * java sql timestamp - используется для хранения времени и даты
     *
     * @param date дата для установки
     * @throws SQLException ошибка sql
     */
    public void writeDate(Date date) throws SQLException {
        try (Connection connection = getConnection(); PreparedStatement statement = connection.prepareStatement(SQL)) {
            statement.setDate(1, new java.sql.Date(date.getTime()));
            statement.setTime(2, new java.sql.Time(date.getTime()));
            statement.setTimestamp(3, new java.sql.Timestamp(date.getTime()));
            statement.executeUpdate();
        }
    }


    /*

     */
    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection("jbdc:postgresql://127.0.0.1/pantry", "postgres", "postgres");
    }
}
