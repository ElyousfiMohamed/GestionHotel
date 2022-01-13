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
import main.gestionhotel.IMetier.IMetier;
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
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  @FXML
  void seConnecter(ActionEvent event) {
    if (IMetierImpl.connect(this.email.getText(), this.password.getText())) {
      Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
      alert.setContentText("Connected");
      alert.show();
    } else {
      Alert alert = new Alert(Alert.AlertType.ERROR);
      alert.setContentText("Not Connected");
      alert.show();
    }
  }
}
