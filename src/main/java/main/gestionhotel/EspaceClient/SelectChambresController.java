package main.gestionhotel.EspaceClient;

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
import main.gestionhotel.ClassesPersistants.Chambre;
import main.gestionhotel.ClassesPersistants.Client;
import main.gestionhotel.ClassesPersistants.Type_Chambre;
import main.gestionhotel.IMetier.IMetier;
import main.gestionhotel.IMetier.IMetierImpl;

import java.net.URL;
import java.util.ResourceBundle;

public class SelectChambresController implements Initializable {

  @FXML private AnchorPane rootPane;

  @FXML private TableView<Chambre> tableView;

  @FXML private TableColumn<Chambre, Integer> id;

  @FXML private TableColumn<Chambre, Type_Chambre> TYPE;

  @FXML private TableColumn<Chambre, Integer> NUM_CH;

  @FXML private TableColumn<Chambre, String> DESC_CHAMBRE;

  @FXML private TableColumn<Chambre, Boolean> DISPONIBILITE;

  @FXML private TextField rechercher;

  @FXML private Button Enregistrer;

  @FXML private Button annuler;

  private ObservableList<Chambre> chambres = FXCollections.observableArrayList();

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
    chambres.addAll(m.searchChambre(keyWord));
    tableView.setItems(chambres);
  }

  @FXML
  void save(ActionEvent event) {
    ObservableList<Integer> indices = tableView.getSelectionModel().getSelectedIndices();
    if (indices != null) {
      try {
        for(int i=0;i<indices.size();i++) {
          IMetierImpl.reservation.getChambres().add(tableView.getItems().get(indices.get(i)));
        }
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
    // TODO
    IMetier metier = new IMetierImpl();
    tableView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
    chambres.addAll(metier.getAllChambres());
    id.setCellValueFactory(new PropertyValueFactory<Chambre, Integer>("id_chmbr"));
    TYPE.setCellValueFactory(new PropertyValueFactory<Chambre, Type_Chambre>("type_chambre"));
    NUM_CH.setCellValueFactory(new PropertyValueFactory<Chambre, Integer>("num_chmbr"));
    DESC_CHAMBRE.setCellValueFactory(new PropertyValueFactory<Chambre, String>("desq_chmbr"));
    DISPONIBILITE.setCellValueFactory(new PropertyValueFactory<Chambre, Boolean>("dispo_chmbr"));
    TYPE.setCellValueFactory(new PropertyValueFactory<Chambre, Type_Chambre>("type_chambre"));
    tableView.setItems(chambres);
  }
}
