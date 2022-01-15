package main.gestionhotel.Authentification.EspaceClient;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import main.gestionhotel.ClassesPersistants.Client;
import main.gestionhotel.IMetier.IMetier;
import main.gestionhotel.IMetier.IMetierImpl;

public class NouveauCltController {

  @FXML private AnchorPane rootPane;

  @FXML private TextField cin;

  @FXML private TextField nom;

  @FXML private TextField prenom;

  @FXML private TextField telephone;

  @FXML private TextField email;

  @FXML private TextField adresse;

  @FXML
  void annuler(ActionEvent event) {
    Stage stage = (Stage) rootPane.getScene().getWindow();
    stage.close();
  }

  @FXML
  void NouveauClt(ActionEvent event) {
    try {
      IMetier metier = new IMetierImpl();
      Client c =
          new Client(
              this.cin.getText(),
              this.nom.getText(),
              this.prenom.getText(),
              this.telephone.getText(),
              this.email.getText(),
              this.adresse.getText());
      metier.addClient(c);
      this.cin.clear();
      this.nom.clear();
      this.prenom.clear();
      this.telephone.clear();
      this.email.clear();
      this.adresse.clear();
    } catch (Exception ex) {
      ex.printStackTrace();
    }
  }
}
