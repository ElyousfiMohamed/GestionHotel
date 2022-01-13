package main.gestionhotel.EspaceEmploye;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import main.gestionhotel.ClassesPersistants.Chambre;
import main.gestionhotel.ClassesPersistants.Client;
import main.gestionhotel.IMetier.IMetier;
import main.gestionhotel.IMetier.IMetierImpl;

public class NouveauChambreController {

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
            Chambre c =
                    new Chambre(
                            Integer.parseInt(this.numCbr.getText()),
                            this.description.getText(),
                            b);
            metier.addChambre(c);
        } catch (Exception ex) {
            ex.printStackTrace();
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


}
