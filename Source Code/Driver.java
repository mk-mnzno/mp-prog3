package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Driver extends Application {

    @Override
    public void start(Stage primaryStage) {

        try {
            Parent root = FXMLLoader.load(getClass().getResource("playerView.fxml"));
            Scene scene = new Scene (root);

            primaryStage.setTitle("myEmpire");
            primaryStage.setScene(scene);
            scene.getStylesheets().add("/sample/images.css");
            primaryStage.show();
            primaryStage.setResizable(false);

        } catch (Exception e)
        {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {
        launch(args);
    }
}