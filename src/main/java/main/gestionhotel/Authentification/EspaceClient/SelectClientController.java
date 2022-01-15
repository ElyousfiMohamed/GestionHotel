package main.gestionhotel.Authentification.EspaceClient;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import main.gestionhotel.ClassesPersistants.Client;
import main.gestionhotel.IMetier.IMetier;
import main.gestionhotel.IMetier.IMetierImpl;

import java.net.URL;
import java.util.ResourceBundle;

public class SelectClientController implements Initializable {

  @FXML private TableView<Client> tableView;

  @FXML private TableColumn<Client, Integer> id;

  @FXML private TableColumn<Client, String> cin;

  @FXML private TableColumn<Client, String> nom;

  @FXML private TableColumn<Client, String> prenom;

  @FXML private TableColumn<Client, String> telephone;

  @FXML private TableColumn<Client, String> email;

  @FXML private TableColumn<Client, String> adresse;

  @FXML private AnchorPane rootPane;

  @FXML private TextField rechercher;

  @FXML private Button Enregistrer;

  @FXML private Button annuler;

  @FXML private Button addClient;

  private ObservableList<Client> clients = FXCollections.observableArrayList();

  @FXML
  void addClient(ActionEvent event) {
    try {
      Stage stage = new Stage();
      FXMLLoader loader = new FXMLLoader();
      loader.setLocation(getClass().getResource("NouveauClt.fxml"));
      Scene scene = new Scene(loader.load());
      stage.initModality(Modality.APPLICATION_MODAL);
      stage.setTitle("Ajouter un client");
      stage.setScene(scene);
      stage.getIcons().add(new Image("https://img.icons8.com/emoji/344/hotel-emoji.png"));
      stage.show();
      stage.setOnCloseRequest(
              new EventHandler<WindowEvent>() {
                @Override
                public void handle(WindowEvent e) {
                  tableView.getItems().clear();
                  IMetier metier = new IMetierImpl();
                  clients.addAll(metier.getAllClients());
                  id.setCellValueFactory(new PropertyValueFactory<>("id_cl"));
                  cin.setCellValueFactory(new PropertyValueFactory<>("CIN_cl"));
                  nom.setCellValueFactory(new PropertyValueFactory<>("nom_cl"));
                  prenom.setCellValueFactory(new PropertyValueFactory<>("prenom_cl"));
                  telephone.setCellValueFactory(new PropertyValueFactory<>("numtel_cl"));
                  email.setCellValueFactory(new PropertyValueFactory<>("email_cl"));
                  adresse.setCellValueFactory(new PropertyValueFactory<>("adresse_cl"));
                  tableView.setItems(clients);
                }
              });
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

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
    clients.addAll(m.searchClt(keyWord));
    tableView.setItems(clients);
  }

  @FXML
  void save(ActionEvent event) {
    int indice = tableView.getSelectionModel().getSelectedIndex();
    if (indice >= 0) {
      try {
        IMetierImpl.reservation.setClient(tableView.getItems().get(indice));
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
    clients.addAll(metier.getAllClients());
    id.setCellValueFactory(new PropertyValueFactory<Client, Integer>("id_cl"));
    cin.setCellValueFactory(new PropertyValueFactory<Client, String>("CIN_cl"));
    nom.setCellValueFactory(new PropertyValueFactory<Client, String>("nom_cl"));
    prenom.setCellValueFactory(new PropertyValueFactory<Client, String>("prenom_cl"));
    telephone.setCellValueFactory(new PropertyValueFactory<Client, String>("numtel_cl"));
    email.setCellValueFactory(new PropertyValueFactory<Client, String>("email_cl"));
    adresse.setCellValueFactory(new PropertyValueFactory<Client, String>("adresse_cl"));
    tableView.setItems(clients);
  }
}
