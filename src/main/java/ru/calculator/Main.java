package ru.calculator;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import ru.calculator.сontrols.GetElement;

import javax.script.ScriptException;

public class Main extends Application {
    public static void main(String[] args) throws ScriptException {
        launch(args);

    }

    @Override
    public void start(Stage stage) throws Exception {
        stage.setTitle("Простой калькулятор");
        stage.setX(755.);
        stage.setY(200.);
        stage.getIcons().add(new Image("file:design/calc.jpg"));

        BorderPane root = new BorderPane();
        Scene scene = new Scene(root, 400., 400.);

        GetElement.getButtonsAndFields(root, stage, scene);

        stage.setScene(scene);
        stage.show();

    }
}
