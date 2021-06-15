package ru.calculator.сontrols;

import javafx.scene.control.TextField;

public class MakeExpression {
    private static boolean checkExponentiation(String inputString) {
        boolean found = false;
        int i = (inputString.lastIndexOf(")"));
        if (i != -1) {
            String substring = inputString.substring(inputString.length() - 1);
            if (substring.equals(")")) found = true;
        }
        return found;
    }

    static void getValueButton(int valueButton, TextField expressionField) {
        switch (valueButton) {
            case 0:
                if (checkExponentiation(expressionField.getText())) {
                    expressionField.insertText(expressionField.getLength() - 1, "0");
                } else expressionField.appendText("0");
                break;
            case 1:
                if (checkExponentiation(expressionField.getText())) {
                    expressionField.insertText(expressionField.getLength() - 1, "1");
                } else expressionField.appendText("1");
                break;
            case 2:
                if (checkExponentiation(expressionField.getText())) {
                    expressionField.insertText(expressionField.getLength() - 1, "2");
                } else expressionField.appendText("2");
                break;
            case 3:
                if (checkExponentiation(expressionField.getText())) {
                    expressionField.insertText(expressionField.getLength() - 1, "3");
                } else expressionField.appendText("3");
                break;
            case 4:
                if (checkExponentiation(expressionField.getText())) {
                    expressionField.insertText(expressionField.getLength() - 1, "4");
                } else expressionField.appendText("4");
                break;
            case 5:
                if (checkExponentiation(expressionField.getText())) {
                    expressionField.insertText(expressionField.getLength() - 1, "5");
                } else expressionField.appendText("5");
                break;
            case 6:
                if (checkExponentiation(expressionField.getText())) {
                    expressionField.insertText(expressionField.getLength() - 1, "6");
                } else expressionField.appendText("6");
                break;
            case 7:
                if (checkExponentiation(expressionField.getText())) {
                    expressionField.insertText(expressionField.getLength() - 1, "7");
                } else expressionField.appendText("7");
                break;
            case 8:
                if (checkExponentiation(expressionField.getText())) {
                    expressionField.insertText(expressionField.getLength() - 1, "8");
                } else expressionField.appendText("8");
                break;
            case 9:
                if (checkExponentiation(expressionField.getText())) {
                    expressionField.insertText(expressionField.getLength() - 1, "9");
                } else expressionField.appendText("9");
                break;
        }


    }

    static void getValueButton(String valueButton, TextField expressionField, TextField resultField) {
        String lastSymbol = null;
        String firstSymbol = null;
        if (expressionField.getText().length() > 0) {
            lastSymbol = expressionField.getText().substring(expressionField.getText().length() - 1);
            firstSymbol = expressionField.getText().substring(0, 1);
        }
        switch (valueButton) {
            case "+":
                if (lastSymbol.equals("-")
                        || lastSymbol.equals("*")
                        || lastSymbol.equals("/")
                        || lastSymbol.equals("x^2")
                        || lastSymbol.equals("√")) {
                    String replaceString = expressionField.getText().substring(0, expressionField.getText().length() - 1);
                    expressionField.setText(replaceString + "+");
                } else if (!lastSymbol.equals("+")) {
                    expressionField.appendText("+");
                }

                break;
            case "-":
                if (lastSymbol.equals("+")
                        || lastSymbol.equals("*")
                        || lastSymbol.equals("/")
                        || lastSymbol.equals("x^2")
                        || lastSymbol.equals("√")) {
                    String replaceString = expressionField.getText().substring(0, expressionField.getText().length() - 1);
                    expressionField.setText(replaceString + "-");
                } else if (!lastSymbol.equals("-")) {
                    expressionField.appendText("-");
                }
                break;
            case "/":
                if (lastSymbol.equals("+")
                        || lastSymbol.equals("*")
                        || lastSymbol.equals("-")
                        || lastSymbol.equals("x^2")
                        || lastSymbol.equals("√")) {
                    String replaceString = expressionField.getText().substring(0, expressionField.getText().length() - 1);
                    expressionField.setText(replaceString + "/");
                } else if (!lastSymbol.equals("/")) {
                    expressionField.appendText("/");
                }
                break;
            case "*":
                if (lastSymbol.equals("+")
                        || lastSymbol.equals("/")
                        || lastSymbol.equals("-")
                        || lastSymbol.equals("x^2")
                        || lastSymbol.equals("√")) {
                    String replaceString = expressionField.getText().substring(0, expressionField.getText().length() - 1);
                    expressionField.setText(replaceString + "*");
                } else if (!lastSymbol.equals("*")) {
                    expressionField.appendText("*");
                }
                break;
            case ",":
                expressionField.appendText(".");
                break;
            case "+/-":
                if (!firstSymbol.equals("-") && expressionField.getText().matches("\\d*")) {
                    expressionField.insertText(0, "-");
                } else if (firstSymbol.equals("-") && expressionField.getText().substring(1).matches("\\d*")) {
                    String replaceString = expressionField.getText().substring(1);
                    expressionField.setText(replaceString);
                }

                break;
            case "x^2":
                try {
                    if (lastSymbol.matches("\\d")) expressionField.appendText("^2");
                } catch (NullPointerException e) {

                    GetElement.getAlert("Сначала введите число для возвдения в квадрат", "Ошибка ввода выражения");
                }
                break;
            case "√":
                expressionField.appendText("sqrt()");
                break;
            case "=":
                Calculate.calculate(expressionField.getText(), resultField, expressionField, -1);
                break;
            case "C":
                expressionField.clear();
                resultField.setText("0");
                break;
        }


    }

}
