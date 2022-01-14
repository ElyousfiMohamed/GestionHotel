package main.gestionhotel.Authentification.EspaceEmploye;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import main.gestionhotel.IMetier.IMetier;
import main.gestionhotel.IMetier.IMetierImpl;

import java.net.URL;
import java.util.ResourceBundle;

public class UpdateChambreController implements Initializable {

    @FXML
    private AnchorPane rootPane;

    @FXML
    private TextField numCbr;

    @FXML
    private TextField description;

    @FXML
    private ToggleGroup DISPONIBILITE;

    @FXML
    void Nouveau(ActionEvent event) {
        try {
            Boolean b = ((RadioButton) this.DISPONIBILITE.getSelectedToggle()).getText().equals("OUI");
            IMetier metier = new IMetierImpl();
            IMetierImpl.chambre.setNum_chmbr(Integer.parseInt(this.numCbr.getText()));
            IMetierImpl.chambre.setDesq_chmbr(this.description.getText());
            IMetierImpl.chambre.setDispo_chmbr(b);
            IMetierImpl.updateChambre();
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

    @FXML
    void selectType(ActionEvent event) {
        try {
            Stage stage = new Stage();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("SelectType.fxml"));
            Scene scene = new Scene(loader.load());
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setTitle("Selectioner le type de chambre");
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.numCbr.setText(String.valueOf(IMetierImpl.chambre.getNum_chmbr()));
        this.description.setText(IMetierImpl.chambre.getDesq_chmbr());
    }
}
