package main.gestionhotel.Authentification;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import main.gestionhotel.IMetier.IMetier;
import main.gestionhotel.IMetier.IMetierImpl;

import java.net.URL;
import java.util.ResourceBundle;

public class UpdateEmpController implements Initializable {

    @FXML
    private AnchorPane rootPane;

    @FXML
    private TextField cin;

    @FXML
    private TextField nom;

    @FXML
    private TextField prenom;

    @FXML
    private TextField telephone;

    @FXML
    private TextField email;

    @FXML
    private RadioButton Admin;

    @FXML
    private ToggleGroup fonction;

    @FXML
    private RadioButton Caissier;

    @FXML
    void annuler(ActionEvent event) {
        Stage stage = (Stage)rootPane.getScene().getWindow();
        stage.close();
    }

    @FXML
    void modifier(ActionEvent event) {
        try {
            IMetier metier = new IMetierImpl();
            IMetierImpl.employe.setCin(this.cin.getText());
            IMetierImpl.employe.setNom(this.nom.getText());
            IMetierImpl.employe.setPrenom(this.prenom.getText());
            IMetierImpl.employe.setTelephone(this.telephone.getText());
            IMetierImpl.employe.setEmail(this.email.getText());
            IMetierImpl.employe.setFonction(((RadioButton)this.fonction.getSelectedToggle()).getText());
            IMetierImpl.updateEmploye();
        } catch (Exception ex) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setContentText(ex.getMessage());
            alert.show();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.cin.setText(IMetierImpl.employe.getCin());
        this.nom.setText(IMetierImpl.employe.getNom());
        this.prenom.setText(IMetierImpl.employe.getPrenom());
        this.telephone.setText(IMetierImpl.employe.getTelephone());
        this.email.setText(IMetierImpl.employe.getEmail());
    }
}
