package main.gestionhotel.Authentification;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import main.gestionhotel.ClassesPersistants.Employe;
import main.gestionhotel.IMetier.IMetier;
import main.gestionhotel.IMetier.IMetierImpl;

public class RegisterController {

  @FXML private AnchorPane rootPane;

  @FXML private TextField cin;

  @FXML private TextField nom;

  @FXML private TextField prenom;

  @FXML private TextField telephone;

  @FXML private TextField email;

  @FXML private PasswordField password;

  @FXML private RadioButton Admin;

  @FXML private ToggleGroup fonction;

  @FXML private RadioButton Caissier;

  @FXML
  void annuler(ActionEvent event) {
    Stage stage = (Stage) rootPane.getScene().getWindow();
    stage.close();
  }

  @FXML
  void register(ActionEvent event) {
    try {
      IMetier metier = new IMetierImpl();
      Employe e =
          new Employe(
              this.cin.getText(),
              this.nom.getText(),
              this.prenom.getText(),
              this.telephone.getText(),
              this.email.getText(),
              this.password.getText(),
              ((RadioButton) this.fonction.getSelectedToggle()).getText());
      metier.addEmploye(e);
    } catch (Exception ex) {
      ex.printStackTrace();
    }
  }
}
