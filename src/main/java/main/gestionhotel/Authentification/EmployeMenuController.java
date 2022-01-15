package main.gestionhotel.Authentification;


import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class EmployeMenuController {

    @FXML
    void chambre(MouseEvent event) {
        try{
            Stage stage = new Stage();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("EspaceEmploye/GestionChambre.fxml"));
            Scene scene = new Scene(loader.load());
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setTitle("Gestion Chambre");
            stage.setScene(scene);
            stage.getIcons().add(new Image("https://img.icons8.com/emoji/344/hotel-emoji.png"));
            stage.show();
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void employe(MouseEvent event) {
        try{
            Stage stage = new Stage();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("EspaceEmploye/GestionEmploye.fxml"));
            Scene scene = new Scene(loader.load());
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setTitle("Gestion Employe");
            stage.setScene(scene);
            stage.getIcons().add(new Image("https://img.icons8.com/emoji/344/hotel-emoji.png"));
            stage.show();
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void type(MouseEvent event) {
        try{
            Stage stage = new Stage();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("EspaceEmploye/GestionType.fxml"));
            Scene scene = new Scene(loader.load());
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setTitle("Gestion Type");
            stage.setScene(scene);
            stage.getIcons().add(new Image("https://img.icons8.com/emoji/344/hotel-emoji.png"));
            stage.show();
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

}
