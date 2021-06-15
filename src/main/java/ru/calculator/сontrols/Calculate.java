package ru.calculator.сontrols;

import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import ru.calculator.Test;
import ru.calculator.db.ModelExpression;
import ru.calculator.db.OperationsDb;

public class Calculate {
    private static int position;
    private static int symbol;
    private static String expression;

    static void calculate(String expressionString, TextField resultField, TextField expressionField, int startPosition) {
        expression = expressionString;
        position = startPosition;
        double resultCalc = getResult();
        resultField.clear();
        resultField.appendText(String.valueOf(resultCalc));
        expressionField.appendText("=");
        OperationsDb.insert(new ModelExpression(0, expressionString + "=", resultCalc));

    }

    private static void nextChar() {
        symbol = (++position < expression.length()) ? expression.charAt(position) : -1;
    }

    private static boolean checkSymbol(int symbolCheck) {
        while (symbol == ' ') nextChar();
        if (symbol == symbolCheck) {
            nextChar();
            return true;
        }
        return false;
    }

    private static double getResult() {
        nextChar();
        double x = getPlusMinus();
        if (position < expression.length()) {
            GetElement.getAlert("Неизвестный символ в выражении:\n"+ (char) symbol, "Ошибка вычисления");
            throw new RuntimeException("Неизвестный символ: " + (char) symbol);
        }
        return x;
    }

    private static double getPlusMinus() {
        double x = getMulDiv();
        for (; ; ) {
            if (checkSymbol('+')) x += getMulDiv();
            else if (checkSymbol('-')) x -= getMulDiv();
            else return x;
        }
    }

    private static double getMulDiv() {
        double x = getFactor();
        for (; ; ) {
            if (checkSymbol('*')) x *= getFactor();
            else if (checkSymbol('/')) x /= getFactor();
            else return x;
        }

    }

    private static double getFactor() {
        if (checkSymbol('+')) return getFactor();
        if (checkSymbol('-')) return -getFactor();

        double x;
        int startPosition = position;
        if (checkSymbol('(')) {
            x = getPlusMinus();
            checkSymbol(')');
        } else if ((symbol >= '0' && symbol <= '9') || symbol == '.') {
            while ((symbol >= '0' && symbol <= '9') || symbol == '.') nextChar();
            x = Double.parseDouble(expression.substring(startPosition, position));
        } else if (symbol >= 'a' && symbol <= 'z') {
            while (symbol >= 'a' && symbol <= 'z') nextChar();
            String function = expression.substring(startPosition, position);
            x = getFactor();
            if (function.equals("sqrt")) x = Math.sqrt(x);
        } else {
            GetElement.getAlert("Неизвестный символ в выражении:\n"+ (char) symbol, "Ошибка вычисления");
            throw new RuntimeException("Неизвестный символ: " + (char) symbol);

        }

        if (checkSymbol('^')) x = Math.pow(x, getFactor());

        return x;
    }


}
