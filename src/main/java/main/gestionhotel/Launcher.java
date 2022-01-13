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
        //loader.setLocation(getClass().getResource("EspaceEmploye/GestionChambre.fxml"));
        //loader.setLocation(getClass().getResource("EspaceEmploye/GestionType.fxml"));
        //loader.setLocation(getClass().getResource("EspaceClient/GestionClient.fxml"));
        loader.setLocation(getClass().getResource("EspaceClient/GestionReservation.fxml"));
        Scene scene = new Scene(loader.load());
        stage.setTitle("Se connecter");
        stage.setScene(scene);
        stage.getIcons().add(new Image("https://img.icons8.com/emoji/344/hotel-emoji.png"));
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}