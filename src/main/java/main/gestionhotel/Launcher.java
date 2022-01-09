package main.gestionhotel;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public class Launcher extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("Authentification/Login.fxml"));
        Scene scene = new Scene(loader.load());
        stage.setTitle("Se connecter");
        stage.setScene(scene);
        stage.getIcons().add(new Image("https://cdn-icons-png.flaticon.com/512/2933/2933772.png"));
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}