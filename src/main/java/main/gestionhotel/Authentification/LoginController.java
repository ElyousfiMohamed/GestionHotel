package main.gestionhotel.Authentification;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class LoginController {

    @FXML
    private Button login;

    @FXML
    void annuler(ActionEvent event) {

    }

    @FXML
    void register(ActionEvent event) {
        try {
            Stage stage = new Stage();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("Register.fxml"));
            Scene scene = new Scene(loader.load());
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setTitle("S'inscrire");
            stage.setScene(scene);
            stage.show();
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void seConnecter(ActionEvent event) {

    }

}
