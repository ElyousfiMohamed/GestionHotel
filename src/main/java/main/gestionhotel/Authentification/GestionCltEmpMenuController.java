package main.gestionhotel.Authentification;


import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class GestionCltEmpMenuController {

    @FXML
    void ClientMenuDisplay(MouseEvent event) {
        try{
            Stage stage = new Stage();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("ClientMenu.fxml"));
            Scene scene = new Scene(loader.load());
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setTitle("Menu Client");
            stage.setScene(scene);
            stage.show();
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void EmployeMenuDisplay(MouseEvent event) {
        try{
            Stage stage = new Stage();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("EmployeMenu.fxml"));
            Scene scene = new Scene(loader.load());
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setTitle("Menu Employe");
            stage.setScene(scene);
            stage.show();
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

}
