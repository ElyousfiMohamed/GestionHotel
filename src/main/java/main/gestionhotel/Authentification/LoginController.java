package main.gestionhotel.Authentification;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import main.gestionhotel.IMetier.IMetierImpl;

public class LoginController {

  @FXML private Button login;

  @FXML private AnchorPane rootPane;

  @FXML private TextField email;

  @FXML private TextField password;

  @FXML
  void annuler(ActionEvent event) {
    Stage stage = (Stage) rootPane.getScene().getWindow();
    stage.close();
  }

  @FXML
  void seConnecter(ActionEvent event) {
    if (IMetierImpl.connect(this.email.getText(), this.password.getText())) {
      try{
        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("GestionCltEmpMenu.fxml"));
        Scene scene = new Scene(loader.load());
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("Menu");
        stage.setScene(scene);
        stage.getIcons().add(new Image("https://img.icons8.com/emoji/344/hotel-emoji.png"));
        stage.show();
      }catch (Exception e){
        e.printStackTrace();
      }
      Stage stage = (Stage) rootPane.getScene().getWindow();
      stage.close();
    } else {
      Alert alert = new Alert(Alert.AlertType.ERROR);
      alert.setContentText("Not Connected");
      alert.show();
    }
  }
}
