package main.gestionhotel.EspaceEmploye;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import main.gestionhotel.ClassesPersistants.Chambre;
import main.gestionhotel.IMetier.IMetier;
import main.gestionhotel.IMetier.IMetierImpl;

import java.net.URL;
import java.util.ResourceBundle;

public class GestionChambreController implements Initializable {

  @FXML private TableView<Chambre> tableView = new TableView<>();

  @FXML private TableColumn<Chambre, Integer> id;

  @FXML private TableColumn<Chambre, Integer> numcmr;

  @FXML private TableColumn<Chambre, String> desccmr;

  @FXML private TableColumn<Chambre, Boolean> dispocmr;

  @FXML private TextField rechercher;
  private ObservableList<Chambre> chambres = FXCollections.observableArrayList();

  @FXML
  void modifier(ActionEvent event) {
    int indice = tableView.getSelectionModel().getSelectedIndex();
    if (indice >= 0) {
      try {
        IMetierImpl.chambre = tableView.getItems().get(indice);

        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("UpdateChambre.fxml"));
        Scene scene = new Scene(loader.load());
        stage.setScene(scene);
        stage.setTitle("modifier chambre");
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.show();
        stage.setOnCloseRequest(
            new EventHandler<WindowEvent>() {
              @Override
              public void handle(WindowEvent e) {
                tableView.getItems().clear();
                IMetier metier = new IMetierImpl();
                chambres.addAll(metier.getAllChambres());
                id.setCellValueFactory(new PropertyValueFactory<>("id_chmbr"));
                numcmr.setCellValueFactory(new PropertyValueFactory<>("num_chmbr"));
                desccmr.setCellValueFactory(new PropertyValueFactory<>("desq_chmbr"));
                dispocmr.setCellValueFactory(new PropertyValueFactory<>("dispo_chmbr"));
                tableView.setItems(chambres);
              }
            });
      } catch (Exception ex) {
        ex.printStackTrace();
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setContentText(ex.getMessage());
        alert.show();
      }
    } else {
      Alert alert = new Alert(Alert.AlertType.WARNING);
      alert.setContentText("Veuillez sélectionner un élément ");
      alert.show();
    }
  }

  @FXML
  void nouveau(ActionEvent event) {
    try {
      Stage stage = new Stage();
      FXMLLoader loader = new FXMLLoader();
      loader.setLocation(getClass().getResource("NouveauChambre.fxml"));
      Scene scene = new Scene(loader.load());
      stage.initModality(Modality.APPLICATION_MODAL);
      stage.setTitle("Nouvelle chambre");
      stage.setScene(scene);
      stage.show();
      stage.setOnCloseRequest(
              new EventHandler<WindowEvent>() {
                @Override
                public void handle(WindowEvent e) {
                  tableView.getItems().clear();
                  IMetier metier = new IMetierImpl();
                  chambres.addAll(metier.getAllChambres());
                  id.setCellValueFactory(new PropertyValueFactory<>("id_chmbr"));
                  numcmr.setCellValueFactory(new PropertyValueFactory<>("num_chmbr"));
                  desccmr.setCellValueFactory(new PropertyValueFactory<>("desq_chmbr"));
                  dispocmr.setCellValueFactory(new PropertyValueFactory<>("dispo_chmbr"));
                  tableView.setItems(chambres);
                }
              });
    } catch (Exception e) {
      e.printStackTrace();
    }
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
  void supprimer(ActionEvent event) {
    int indice = tableView.getSelectionModel().getSelectedIndex();
    if (indice >= 0) {
      try {
        IMetier metier = new IMetierImpl();
        metier.delChambre(tableView.getItems().get(indice).getId_chmbr());
      } catch (Exception ex) {
        ex.printStackTrace();
      }
      tableView.getItems().remove(indice);
    } else {
      Alert alert = new Alert(Alert.AlertType.WARNING);
      alert.setContentText("Veuillez sélectionner un élément ");
      alert.show();
    }
  }

  @Override
  public void initialize(URL url, ResourceBundle resourceBundle) {
    tableView.getItems().clear();
    IMetier metier = new IMetierImpl();
    chambres.addAll(metier.getAllChambres());
    id.setCellValueFactory(new PropertyValueFactory<>("id_chmbr"));
    numcmr.setCellValueFactory(new PropertyValueFactory<>("num_chmbr"));
    desccmr.setCellValueFactory(new PropertyValueFactory<>("desq_chmbr"));
    dispocmr.setCellValueFactory(new PropertyValueFactory<>("dispo_chmbr"));
    tableView.setItems(chambres);
  }
}
