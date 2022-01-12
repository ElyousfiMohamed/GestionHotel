package main.gestionhotel.EspaceClient;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import main.gestionhotel.ClassesPersistants.Client;
import main.gestionhotel.IMetier.IMetier;
import main.gestionhotel.IMetier.IMetierImpl;

import java.net.URL;
import java.util.ResourceBundle;

public class GestionClientController implements Initializable {

  @FXML private TableView<Client> tableView = new TableView<Client>();

  @FXML private TableColumn<Client, Integer> id;

  @FXML private TableColumn<Client, String> cin;

  @FXML private TableColumn<Client, String> nom;

  @FXML private TableColumn<Client, String> prenom;

  @FXML private TableColumn<Client, String> telephone;

  @FXML private TableColumn<Client, String> email;

  @FXML private TableColumn<Client, String> adresse;

  @FXML private TextField rechercher;

  private ObservableList<Client> clients = FXCollections.observableArrayList();

  @FXML
  void modifier(ActionEvent event) {
    int indice = tableView.getSelectionModel().getSelectedIndex();
    if (indice >= 0) {
      try {
        IMetierImpl.client = tableView.getItems().get(indice);

        Stage stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("UpdateClt.fxml"));

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("modifier client");
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.show();
        stage.setOnCloseRequest(
                new EventHandler<WindowEvent>() {
                  @Override
                  public void handle(WindowEvent e) {
                    tableView.getItems().clear();
                    IMetier metier = new IMetierImpl();
                    clients.addAll(metier.getAllClients());
                    id.setCellValueFactory(new PropertyValueFactory<>("id"));
                    cin.setCellValueFactory(new PropertyValueFactory<>("cin"));
                    nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
                    prenom.setCellValueFactory(new PropertyValueFactory<>("prenom"));
                    telephone.setCellValueFactory(new PropertyValueFactory<>("telephone"));
                    email.setCellValueFactory(new PropertyValueFactory<>("email"));
                    adresse.setCellValueFactory(new PropertyValueFactory<>("adresse"));
                    tableView.setItems(clients);
                  }
                });
      } catch (Exception ex) {
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
    loader.setLocation(getClass().getResource("NouveauClt.fxml"));
    Scene scene = new Scene(loader.load());
    stage.initModality(Modality.APPLICATION_MODAL);
    stage.setTitle("Nouveau client");
    stage.setScene(scene);
    stage.show();
  } catch (Exception e) {
    e.printStackTrace();
  }}

  @FXML
  void rechercher(KeyEvent event) {
    String keyWord = rechercher.getText();
    tableView.getItems().clear();

    IMetier m = new IMetierImpl();
    clients.addAll(m.searchClt(keyWord));
    tableView.setItems(clients);}

  @FXML
  void supprimer(ActionEvent event) {
    int indice = tableView.getSelectionModel().getSelectedIndex();
    if (indice >= 0) {
      try {
        IMetier metier = new IMetierImpl();
        metier.delClient(tableView.getItems().get(indice).getId_cl());
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
    // TODO
    IMetier metier = new IMetierImpl();
    tableView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
    clients.addAll(metier.getAllClients());
    id.setCellValueFactory(new PropertyValueFactory<Client, Integer>("id"));
    cin.setCellValueFactory(new PropertyValueFactory<Client, String>("cin"));
    nom.setCellValueFactory(new PropertyValueFactory<Client, String>("nom"));
    prenom.setCellValueFactory(new PropertyValueFactory<Client, String>("prenom"));
    telephone.setCellValueFactory(new PropertyValueFactory<Client, String>("telephone"));
    email.setCellValueFactory(new PropertyValueFactory<Client, String>("email"));
    adresse.setCellValueFactory(new PropertyValueFactory<Client, String>("adresse"));
    tableView.setItems(clients);
  }
}
