package com.deadlyllama.discordbingo;

import com.deadlyllama.discordbingo.services.StringValuesService;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.*;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Discord Tech Bingo");
        primaryStage.setScene(new Scene(root, 750, 400));
        primaryStage.show();
    }


    public static void main(String[] args) {
        StringValuesService stringValuesService = new StringValuesService();

        try {
            File file = new File("resources/discordbingo_strings.txt");
            BufferedReader reader = new BufferedReader(new FileReader(file));

            String value;
            while ((value = reader.readLine()) != null) {
                stringValuesService.addOption(value);
            }

            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
            Platform.exit();
        }

        launch(args);
    }
}
