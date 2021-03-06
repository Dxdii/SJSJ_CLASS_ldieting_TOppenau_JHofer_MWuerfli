package Auswerten;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class MainAuswerten extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sampleAuswerten.fxml"));

        primaryStage.setTitle("Polling Project - Auswertung - Tim Oppenauer");
        primaryStage.setScene(new Scene(root, 900, 475)); //600, 475
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }


/*
    public static void main(String[] args) {

        System.out.println("Auswerten");

    }
 */

}
