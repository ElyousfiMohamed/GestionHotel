package main.gestionhotel.GestionChambre;

import javafx.event.ActionEvent;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.fxml.FXML;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import main.gestionhotel.ClassesPersistants.Chambre;
import main.gestionhotel.IMetier.IMetier;
import main.gestionhotel.IMetier.IMetierImpl;

public class AddChambreController {


        @FXML
        private Pane rootPane;

        @FXML
        private TextField descriptionChambre;

        @FXML
        private TextField type;

        @FXML
        private TextField numeroChambre;

       @FXML
       private ToggleGroup disponible;

      @FXML
       private RadioButton dispoTrue;

      @FXML
      private RadioButton dispoFalse;



    public void ajouterChambre(ActionEvent event) {
            try {
                IMetier metier = new IMetierImpl();
                Chambre c = new Chambre(Integer.parseInt(this.type.getText()),Integer.parseInt(this.numeroChambre.getText()),this.descriptionChambre.getText(),Boolean.parseBoolean(((RadioButton)this.disponible.getSelectedToggle()).getText()));
                metier.addChambre(c);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }


        public void annulerChambre(ActionEvent event) {
            Stage stage = (Stage)rootPane.getScene().getWindow();
            stage.close();
        }



}
