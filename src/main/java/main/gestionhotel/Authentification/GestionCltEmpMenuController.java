package main.gestionhotel.Authentification;


import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import main.gestionhotel.IMetier.IMetierImpl;

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
            if(IMetierImpl.ROLE.equals("Admin")) {
                Stage stage = new Stage();
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("EmployeMenu.fxml"));
                Scene scene = new Scene(loader.load());
                stage.initModality(Modality.APPLICATION_MODAL);
                stage.setTitle("Menu Employe");
                stage.setScene(scene);
                stage.show();
            }else{
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setContentText("ESPACE RESERVER A L'ADMIN");
                alert.show();
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

}
