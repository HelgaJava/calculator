package ru.calculator.db;

import java.util.LinkedList;
import java.util.List;

public class OperationsDb {
    private static List<ModelExpression> listExpressions = new LinkedList<>();
    private static final String url = "jdbc:sqlite:calcDb.sqlite";

    public static ModelExpression insert(ModelExpression expression) {
        String sql = "insert into expression (text, result) values (?,?)";
        int id = ConnectDb.insertItem(url, sql, statement -> {
            statement.setString(1, expression.getText());
            statement.setDouble(2, expression.getResult());
        });
        expression.setId(id);
        return expression;
    }

    public static List<ModelExpression> select() {
        String sql = "select * from expression order by id desc limit 10;";
        listExpressions = ConnectDb.executeSelect(url, sql,
                resultSet -> new ModelExpression(
                        resultSet.getInt("id"),
                        resultSet.getString("text"),
                        resultSet.getDouble("result")
                )
        );
        return listExpressions;
    }

}
