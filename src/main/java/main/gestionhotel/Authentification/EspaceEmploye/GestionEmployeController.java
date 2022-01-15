package main.gestionhotel.Authentification.EspaceEmploye;

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
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import main.gestionhotel.ClassesPersistants.Employe;
import main.gestionhotel.IMetier.IMetier;
import main.gestionhotel.IMetier.IMetierImpl;

import java.net.URL;
import java.util.ResourceBundle;

public class GestionEmployeController implements Initializable {

  @FXML private TableView<Employe> tableView = new TableView<Employe>();

  @FXML private TableColumn<Employe, Integer> id;

  @FXML private TableColumn<Employe, String> cin;

  @FXML private TableColumn<Employe, String> nom;

  @FXML private TableColumn<Employe, String> prenom;

  @FXML private TableColumn<Employe, String> telephone;

  @FXML private TableColumn<Employe, String> email;

  @FXML private TableColumn<Employe, String> fonction;

  @FXML private TextField rechercher;

  private ObservableList<Employe> employes = FXCollections.observableArrayList();

  @Override
  public void initialize(URL url, ResourceBundle rb) {
    // TODO
    IMetier metier = new IMetierImpl();
    employes.addAll(metier.getAllEmployes());
    id.setCellValueFactory(new PropertyValueFactory<Employe, Integer>("id"));
    cin.setCellValueFactory(new PropertyValueFactory<Employe, String>("cin"));
    nom.setCellValueFactory(new PropertyValueFactory<Employe, String>("nom"));
    prenom.setCellValueFactory(new PropertyValueFactory<Employe, String>("prenom"));
    telephone.setCellValueFactory(new PropertyValueFactory<Employe, String>("telephone"));
    email.setCellValueFactory(new PropertyValueFactory<Employe, String>("email"));
    fonction.setCellValueFactory(new PropertyValueFactory<Employe, String>("fonction"));
    tableView.setItems(employes);
  }

  @FXML
  void modifier(ActionEvent event) {
    int indice = tableView.getSelectionModel().getSelectedIndex();
    if (indice >= 0) {
      try {
        IMetierImpl.employe = tableView.getItems().get(indice);

        Stage stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("UpdateEmp.fxml"));

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("modifier employé");
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.getIcons().add(new Image("https://img.icons8.com/emoji/344/hotel-emoji.png"));
        stage.show();
        stage.setOnCloseRequest(
            new EventHandler<WindowEvent>() {
              @Override
              public void handle(WindowEvent e) {
                tableView.getItems().clear();
                IMetier metier = new IMetierImpl();
                employes.addAll(metier.getAllEmployes());
                id.setCellValueFactory(new PropertyValueFactory<>("id"));
                cin.setCellValueFactory(new PropertyValueFactory<>("cin"));
                nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
                prenom.setCellValueFactory(new PropertyValueFactory<>("prenom"));
                telephone.setCellValueFactory(new PropertyValueFactory<>("telephone"));
                email.setCellValueFactory(new PropertyValueFactory<>("email"));
                fonction.setCellValueFactory(new PropertyValueFactory<>("fonction"));
                tableView.setItems(employes);
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
      loader.setLocation(getClass().getResource("NouveauEmploye.fxml"));
      Scene scene = new Scene(loader.load());
      stage.initModality(Modality.APPLICATION_MODAL);
      stage.setTitle("Nouveau employé");
      stage.setScene(scene);
      stage.getIcons().add(new Image("https://img.icons8.com/emoji/344/hotel-emoji.png"));
      stage.show();
      stage.setOnCloseRequest(
              new EventHandler<WindowEvent>() {
                @Override
                public void handle(WindowEvent e) {
                  tableView.getItems().clear();
                  IMetier metier = new IMetierImpl();
                  employes.addAll(metier.getAllEmployes());
                  tableView.setItems(employes);
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
    employes.addAll(m.searchEmp(keyWord));
    tableView.setItems(employes);
  }

  @FXML
  void supprimer(ActionEvent event) {
    int indice = tableView.getSelectionModel().getSelectedIndex();
    if (indice >= 0) {
      try {
        IMetier metier = new IMetierImpl();
        metier.delEmploye(tableView.getItems().get(indice).getId());
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
}
