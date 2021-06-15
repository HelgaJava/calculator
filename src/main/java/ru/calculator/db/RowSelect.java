package ru.calculator.db;

import java.sql.ResultSet;
import java.sql.SQLException;

@FunctionalInterface
public interface RowSelect<T> {
    T select(ResultSet resultSet) throws SQLException;
}
