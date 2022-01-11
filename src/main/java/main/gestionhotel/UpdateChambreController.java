package main.gestionhotel.GestionChambre;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import main.gestionhotel.ClassesPersistants.Chambre;
import main.gestionhotel.IMetier.IMetier;
import main.gestionhotel.IMetier.IMetierImpl;

import java.net.URL;
import java.util.ResourceBundle;

public class UpdateChambreController implements Initializable {

    @FXML
    private Pane rootPane;

    @FXML
    private RadioButton dispoFalse;

    @FXML
    private TextField descriptionChambre;

    @FXML
    private TextField type;

    @FXML
    private RadioButton dispoTrue;

    @FXML
    private TextField numeroChambre;

    @FXML
    private ToggleGroup disponible;



    public void apdateChambre(ActionEvent actionEvent) {
        try {
            IMetier metier = new IMetierImpl();
            IMetierImpl.chambre.setId_type(Integer.parseInt(this.type.getText()));
            IMetierImpl.chambre.setNum_chmbr(Integer.parseInt(this.numeroChambre.getText()));
            IMetierImpl.chambre.setDesq_chmbr(this.descriptionChambre.getText());
            IMetierImpl.chambre.setDispo_chmbr(Boolean.parseBoolean(((RadioButton)this.disponible.getSelectedToggle()).getText()));
            IMetierImpl.updateChambre();
        } catch (Exception ex) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setContentText(ex.getMessage());
            alert.show();
        }
    }

    public void annulerChambre(ActionEvent actionEvent) {
        Stage stage = (Stage)rootPane.getScene().getWindow();
        stage.close();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.type.setText(String.valueOf(IMetierImpl.chambre.getId_type()));
        this.numeroChambre.setText(String.valueOf(IMetierImpl.chambre.getNum_chmbr()));
        this.descriptionChambre.setText(IMetierImpl.chambre.getDesq_chmbr());
    }
}
