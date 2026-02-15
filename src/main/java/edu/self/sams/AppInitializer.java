package edu.self.sams;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class AppInitializer extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        Parent load =  FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/view/AdminDashboard.fxml")));
        Scene scene = new Scene(load);
        stage.setScene(scene);
        stage.setTitle("SAMS");
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}