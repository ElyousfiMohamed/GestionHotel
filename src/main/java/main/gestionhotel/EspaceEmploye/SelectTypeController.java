package main.gestionhotel.EspaceEmploye;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import main.gestionhotel.ClassesPersistants.Type_Chambre;
import main.gestionhotel.IMetier.IMetier;
import main.gestionhotel.IMetier.IMetierImpl;

import java.net.URL;
import java.util.ResourceBundle;

public class SelectTypeController implements Initializable {

    @FXML
    private AnchorPane rootPane;

    @FXML
    private TableView<Type_Chambre> tableView;

    @FXML
    private TableColumn<Type_Chambre, Integer> id;

    @FXML
    private TableColumn<Type_Chambre, String> INTITULE;

    @FXML
    private TableColumn<Type_Chambre, Integer> CAPACITE;

    @FXML
    private TableColumn<Type_Chambre, Float> PRIX;

    @FXML
    private TextField rechercher;

    @FXML
    private Button Enregistrer;

    @FXML
    private Button annuler;

    private ObservableList<Type_Chambre> types = FXCollections.observableArrayList();


    @FXML
    void annuler(ActionEvent event) {
        Stage stage = (Stage) rootPane.getScene().getWindow();
        stage.close();
    }

    @FXML
    void rechercher(KeyEvent event) {
        String keyWord = rechercher.getText();
        tableView.getItems().clear();

        IMetier m = new IMetierImpl();
        types.addAll(m.searchType(keyWord));
        tableView.setItems(types);
    }

    @FXML
    void save(ActionEvent event) {
        int indice = tableView.getSelectionModel().getSelectedIndex();
        if (indice >= 0) {
            try {
                IMetierImpl.chambre.setType_chambre(tableView.getItems().get(indice));
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setContentText("Veuillez sélectionner un élément ");
            alert.show();
        }
        Stage stage = (Stage) rootPane.getScene().getWindow();
        stage.close();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        IMetier metier = new IMetierImpl();
        types.addAll(metier.getAllTypes());
        id.setCellValueFactory(new PropertyValueFactory<>("id_type"));
        INTITULE.setCellValueFactory(new PropertyValueFactory<>("intitule"));
        CAPACITE.setCellValueFactory(new PropertyValueFactory<>("capacité"));
        PRIX.setCellValueFactory(new PropertyValueFactory<>("prix"));
        tableView.setItems(types);
    }
}
