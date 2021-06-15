package ru.calculator.db;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public interface PreparedStatementExecutor <T>{
    T execute(PreparedStatement statement) throws SQLException;
}
