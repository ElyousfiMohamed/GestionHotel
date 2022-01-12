package main.gestionhotel.EspaceClient;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import main.gestionhotel.IMetier.IMetier;
import main.gestionhotel.IMetier.IMetierImpl;

import java.net.URL;
import java.util.ResourceBundle;

import static main.gestionhotel.IMetier.IMetierImpl.client;

public class UpdateCltController implements Initializable {

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
    void modifier(ActionEvent event) {
        try {
            IMetier metier = new IMetierImpl();
            client.setCIN_cl(this.cin.getText());
            client.setNom_cl(this.nom.getText());
            client.setPrenom_cl(this.prenom.getText());
            client.setNumtel_cl(this.telephone.getText());
            client.setEmail_cl(this.email.getText());
            client.setAdresse_cl(this.adresse.getText());
            IMetierImpl.updateClient(client);
        } catch (Exception ex) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setContentText(ex.getMessage());
            alert.show();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.cin.setText(client.getCIN_cl());
        this.nom.setText(client.getNom_cl());
        this.prenom.setText(client.getPrenom_cl());
        this.telephone.setText(client.getNumtel_cl());
        this.email.setText(client.getEmail_cl());
        this.adresse.setText(client.getAdresse_cl());
    }
}
