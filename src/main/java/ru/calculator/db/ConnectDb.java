package ru.calculator.db;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class ConnectDb {

    private static <T> T execute(String url, String sql, PreparedStatementExecutor<T> executor) {
        try (
                Connection connection = DriverManager.getConnection(url);
                PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        ) {
            return executor.execute(statement);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static int insertItem(String url, String sql, PreparedStatementSetter setter) {
        return execute(url, sql, statement -> {
            setter.set(statement);
            statement.executeUpdate();
            try (ResultSet keys = statement.getGeneratedKeys();) {
                if (keys.next()) {
                    return keys.getInt(1);
                }
            }
            throw new RuntimeException();
        });
    }

    public static <T> List<T> selectItemsByParameter(String url, String sql, PreparedStatementSetter setter, RowSelect<T> select) {
        return execute(url, sql, statement -> {
            setter.set(statement);
            try (ResultSet resultSet = statement.executeQuery();) {
                List<T> result = new LinkedList<>();
                while (resultSet.next()) {
                    result.add(select.select(resultSet));
                }
                return result;
            }
        });
    }

    public static <T> List<T> executeSelect(String url, String sql, RowSelect<T> select) {
        try (
                Connection connection = DriverManager.getConnection(url);
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(sql);
        ) {
            List<T> result = new LinkedList<>();
            while (resultSet.next()) {
                result.add(select.select(resultSet));
            }

            return result;
        } catch (SQLException e) {
            throw new RuntimeException(e);

        }
    }
}
