package main.gestionhotel.EspaceEmploye;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import main.gestionhotel.IMetier.IMetier;
import main.gestionhotel.IMetier.IMetierImpl;

import java.net.URL;
import java.util.ResourceBundle;

public class UpdateTypeController implements Initializable {

    @FXML
    private AnchorPane rootPane;

    @FXML
    private TextField INTITULE;

    @FXML
    private TextField CAPACITE;

    @FXML
    private TextField PRIX;

    @FXML
    void Enregister(ActionEvent event) {
        try {
            IMetier metier = new IMetierImpl();
            IMetierImpl.type.setIntitule(this.INTITULE.getText());
            IMetierImpl.type.setCapacité(Integer.parseInt(this.CAPACITE.getText()));
            IMetierImpl.type.setPrix(Float.parseFloat(this.PRIX.getText()));;
            IMetierImpl.updateType();
        } catch (Exception ex) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setContentText(ex.getMessage());
            alert.show();
        }
    }

    @FXML
    void annuler(ActionEvent event) {
        Stage stage = (Stage) rootPane.getScene().getWindow();
        stage.close();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.INTITULE.setText(IMetierImpl.type.getIntitule());
        this.CAPACITE.setText(String.valueOf(IMetierImpl.type.getCapacité()));
        this.PRIX.setText(String.valueOf(IMetierImpl.type.getPrix()));
    }
}
