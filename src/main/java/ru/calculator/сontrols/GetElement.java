package ru.calculator.сontrols;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.NodeOrientation;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.stage.Popup;
import javafx.stage.Stage;
import ru.calculator.db.ModelExpression;
import ru.calculator.db.OperationsDb;

import java.io.File;
import java.net.MalformedURLException;
import java.util.HashMap;
import java.util.List;

public class GetElement {
    private static final HashMap<String, Button> mapButtons = new HashMap<>();
    private static final String srcDesign = "design/Design.css";

    public static void getButtonsAndFields(BorderPane container, Stage primaryStage, Scene scene) {
        getStyle(scene);

        TextField expressionField = getTextField("");
        expressionField.setPromptText("Здесь отображается арифметическое выражение");

        TextField resultField = getTextField("0");
        resultField.setId("result");
        resultField.getStyleClass().add("result");

        Popup historyPopup = getPopup();
        Button buttonShowHidePopup = getButton("Показать историю операций");
        changeElements(buttonShowHidePopup, historyPopup, primaryStage);

        VBox vBox = new VBox(0, buttonShowHidePopup, expressionField, resultField);

        Button buttonOne = getButton("1");
        getValueButtonAll("1", expressionField, resultField);

        Button buttonTwo = getButton("2");
        getValueButtonAll("2", expressionField, resultField);

        Button buttonThree = getButton("3");
        getValueButtonAll("3", expressionField, resultField);

        Button buttonFour = getButton("4");
        getValueButtonAll("4", expressionField, resultField);

        Button buttonFive = getButton("5");
        getValueButtonAll("5", expressionField, resultField);

        Button buttonSix = getButton("6");
        getValueButtonAll("6", expressionField, resultField);

        Button buttonSeven = getButton("7");
        getValueButtonAll("7", expressionField, resultField);

        Button buttonEight = getButton("8");
        getValueButtonAll("8", expressionField, resultField);

        Button buttonNine = getButton("9");
        getValueButtonAll("9", expressionField, resultField);

        Button buttonZero = getButton("0");
        getValueButtonAll("0", expressionField, resultField);

        Button buttonAddition = getButton("+");
        getValueButtonAll("+", expressionField, resultField);

        Button buttonSubtraction = getButton("-");
        getValueButtonAll("-", expressionField, resultField);

        Button buttonDivision = getButton("/");
        getValueButtonAll("/", expressionField, resultField);

        Button buttonMultiplication = getButton("*");
        getValueButtonAll("*", expressionField, resultField);

        Button buttonDecimal = getButton(",");
        getValueButtonAll(",", expressionField, resultField);

        Button buttonPlusMinus = getButton("+/-");
        getValueButtonAll("+/-", expressionField, resultField);

        Button buttonExponentiation = getButton("x^2");
        getValueButtonAll("x^2", expressionField, resultField);

        Button buttonCoreExtraction = getButton("√");
        getValueButtonAll("√", expressionField, resultField);

        Button buttonResult = getButton("=");
        getValueButtonAll("=", expressionField, resultField);

        Button buttonClear = getButton("C");
        getValueButtonAll("C", expressionField, resultField);

        GridPane gridButtons = getGridPane();
        container.setTop(vBox);
        container.setCenter(gridButtons);


    }

    private static TextField getTextField(String defaultText) {
        TextField textField = new TextField(defaultText);
        textField.setPrefColumnCount(15);
        textField.setNodeOrientation(NodeOrientation.LEFT_TO_RIGHT);
        textField.setEditable(false);
        return textField;
    }

    private static Button getButton(String textButton) {
        Button button = new Button(textButton);
        button.setMaxWidth(Double.MAX_VALUE);
        button.setMaxHeight(Double.MAX_VALUE);
        mapButtons.put(textButton, button);
        return button;
    }

    private static GridPane getGridPane() {
        GridPane gridButtons = new GridPane();
        ColumnConstraints column1 = new ColumnConstraints();
        column1.setPercentWidth(25.);
        ColumnConstraints column2 = new ColumnConstraints();
        column2.setPercentWidth(25.);
        ColumnConstraints column3 = new ColumnConstraints();
        column3.setPercentWidth(25.);
        ColumnConstraints column4 = new ColumnConstraints();
        column4.setPercentWidth(25.);
        gridButtons.getColumnConstraints().addAll(column1, column2, column3, column4);

        RowConstraints row1 = new RowConstraints();
        row1.setPercentHeight(20.);
        RowConstraints row2 = new RowConstraints();
        row2.setPercentHeight(20.);
        RowConstraints row3 = new RowConstraints();
        row3.setPercentHeight(20.);
        RowConstraints row4 = new RowConstraints();
        row4.setPercentHeight(20.);
        RowConstraints row5 = new RowConstraints();
        row5.setPercentHeight(20.);
        gridButtons.getRowConstraints().addAll(row1, row2, row3, row4, row5);
        gridButtons.setGridLinesVisible(false);

        gridButtons.add(mapButtons.get("C"), 0, 0);
        gridButtons.add(mapButtons.get("x^2"), 1, 0);
        gridButtons.add(mapButtons.get("√"), 2, 0);
        gridButtons.add(mapButtons.get("/"), 3, 0);
        gridButtons.add(mapButtons.get("7"), 0, 1);
        gridButtons.add(mapButtons.get("8"), 1, 1);
        gridButtons.add(mapButtons.get("9"), 2, 1);
        gridButtons.add(mapButtons.get("*"), 3, 1);
        gridButtons.add(mapButtons.get("4"), 0, 2);
        gridButtons.add(mapButtons.get("5"), 1, 2);
        gridButtons.add(mapButtons.get("6"), 2, 2);
        gridButtons.add(mapButtons.get("-"), 3, 2);
        gridButtons.add(mapButtons.get("1"), 0, 3);
        gridButtons.add(mapButtons.get("2"), 1, 3);
        gridButtons.add(mapButtons.get("3"), 2, 3);
        gridButtons.add(mapButtons.get("+"), 3, 3);
        gridButtons.add(mapButtons.get("+/-"), 0, 4);
        gridButtons.add(mapButtons.get("0"), 1, 4);
        gridButtons.add(mapButtons.get(","), 2, 4);
        gridButtons.add(mapButtons.get("="), 3, 4);

        return gridButtons;
    }

    private static Popup getPopup() {
        Popup popup = new Popup();
        VBox vBoxHistory = new VBox();
        vBoxHistory.setId("popup");
        getStyle(vBoxHistory);
        vBoxHistory.getStyleClass().add("popup");
        List<ModelExpression> expressionList = OperationsDb.select();
        if (expressionList.size() > 0) {
            for (ModelExpression modelExpression : expressionList) {
                Label labelText = new Label(modelExpression.getText());
                Label labelResult = new Label(modelExpression.getResult() + "\n");
                labelResult.setId("labelResult");
                labelResult.getStyleClass().add("labelResult");

                Label label = new Label("");
                vBoxHistory.getChildren().addAll(labelText, labelResult, label);
                vBoxHistory.setSpacing(2.);
            }
        }

        popup.getContent().add(vBoxHistory);
        return popup;
    }

    private static void getValueButtonAll(String key, TextField expressionField, TextField resultField) {
        mapButtons.get(key).setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if (key.matches("\\d")) {
                    MakeExpression.getValueButton(Integer.parseInt(key), expressionField);
                } else {
                    MakeExpression.getValueButton(key, expressionField, resultField);
                }

            }
        });
    }

    private static void changeElements(Button buttonShowHidePopup, Popup historyPopup, Stage primaryStage) {
        buttonShowHidePopup.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if (!historyPopup.isShowing()) {
                    historyPopup.show(primaryStage);
                    historyPopup.setX(primaryStage.getX() + 425.);
                    historyPopup.setY(primaryStage.getY());
                    buttonShowHidePopup.setText("Скрыть историю операций");
                } else {
                    historyPopup.hide();
                    buttonShowHidePopup.setText("Показать историю операций");
                }
            }
        });

        primaryStage.xProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number oldValue, Number newValue) {
                if (historyPopup.isShowing()) {
                    historyPopup.setX(primaryStage.getX() + 425.);
                }
            }
        });

        primaryStage.yProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number oldValue, Number newValue) {
                if (historyPopup.isShowing()) {
                    historyPopup.setY(primaryStage.getY());
                }
            }
        });

    }

    private static <T> void getStyle(T element) {
        if (element.getClass().equals(Scene.class)) {
            try {
                ((Scene) element).getStylesheets().add((new File(srcDesign)).toURI().toURL().toExternalForm());
            } catch (MalformedURLException e) {
                getAlert(e.getMessage(), "Ошибка загрузки стилей (проверьте папку design)");

            }
        } else if (element.getClass().equals(VBox.class)) {
            try {
                ((VBox) element).getStylesheets().add((new File(srcDesign)).toURI().toURL().toExternalForm());
            } catch (MalformedURLException e) {
                getAlert(e.getMessage(), "Ошибка загрузки стилей (проверьте папку design)");
            }
        } else if (element.getClass().equals(DialogPane.class)){
            try {
                ((DialogPane) element).getStylesheets().add((new File(srcDesign)).toURI().toURL().toExternalForm());
            } catch (MalformedURLException e) {
                getAlert(e.getMessage(), "Ошибка загрузки стилей (проверьте папку design)");

            }
        }

    }

    static void getAlert(String textAlert, String textHeader){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Ошибка!");
        alert.setHeaderText(textHeader);
        alert.setContentText(textAlert);
        Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
        stage.getIcons().add(new Image("file:design/calc.jpg"));
        getStyle(alert.getDialogPane());
        alert.showAndWait();
    }


}
