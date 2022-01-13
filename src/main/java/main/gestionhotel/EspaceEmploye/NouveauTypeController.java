package main.gestionhotel.EspaceEmploye;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import main.gestionhotel.ClassesPersistants.Chambre;
import main.gestionhotel.ClassesPersistants.Type_Chambre;
import main.gestionhotel.IMetier.IMetier;
import main.gestionhotel.IMetier.IMetierImpl;

public class NouveauTypeController {

    @FXML
    private AnchorPane rootPane;

    @FXML
    private TextField INTITULE;

    @FXML
    private TextField CAPACITE;

    @FXML
    private TextField PRIX;

    @FXML
    void Nouveau(ActionEvent event) {
        try {
            IMetier metier = new IMetierImpl();
            Type_Chambre c =
                    new Type_Chambre(
                            this.INTITULE.getText(),
                            Integer.parseInt(this.CAPACITE.getText()),
                            Float.parseFloat(this.PRIX.getText()));
            metier.addType(c);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @FXML
    void annuler(ActionEvent event) {
        Stage stage = (Stage) rootPane.getScene().getWindow();
        stage.close();
    }

}
