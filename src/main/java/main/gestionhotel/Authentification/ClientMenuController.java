package main.gestionhotel.Authentification;


import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class ClientMenuController {

    @FXML
    void clientShow(MouseEvent event) {
        try{
            Stage stage = new Stage();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("EspaceClient/GestionClient.fxml"));
            Scene scene = new Scene(loader.load());
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setTitle("Gestion Client");
            stage.setScene(scene);
            stage.getIcons().add(new Image("https://img.icons8.com/emoji/344/hotel-emoji.png"));
            stage.show();
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void reservationShow(MouseEvent event) {
        try{
            Stage stage = new Stage();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("EspaceClient/GestionReservation.fxml"));
            Scene scene = new Scene(loader.load());
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setTitle("Gestion Reservation");
            stage.setScene(scene);
            stage.getIcons().add(new Image("https://img.icons8.com/emoji/344/hotel-emoji.png"));
            stage.show();
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

}
