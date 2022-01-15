package main.gestionhotel.Authentification.EspaceEmploye;

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
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import main.gestionhotel.ClassesPersistants.Type_Chambre;
import main.gestionhotel.IMetier.IMetier;
import main.gestionhotel.IMetier.IMetierImpl;

import java.net.URL;
import java.util.ResourceBundle;

public class GestionTypeController implements Initializable {

  @FXML private TableView<Type_Chambre> tableView = new TableView<>();

  @FXML private TableColumn<Type_Chambre, Integer> id;

  @FXML private TableColumn<Type_Chambre, String> INTITULE;

  @FXML private TableColumn<Type_Chambre, Integer> CAPACITE;

  @FXML private TableColumn<Type_Chambre, Float> PRIX;

  @FXML private TextField rechercher;
  private ObservableList<Type_Chambre> types = FXCollections.observableArrayList();

  @FXML
  void modifier(ActionEvent event) {
    int indice = tableView.getSelectionModel().getSelectedIndex();
    if (indice >= 0) {
      try {
        IMetierImpl.type = tableView.getItems().get(indice);

        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("UpdateType.fxml"));
        Scene scene = new Scene(loader.load());
        stage.setScene(scene);
        stage.setTitle("modifier type");
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.getIcons().add(new Image("https://img.icons8.com/emoji/344/hotel-emoji.png"));
        stage.show();
        stage.setOnCloseRequest(
            new EventHandler<WindowEvent>() {
              @Override
              public void handle(WindowEvent e) {
                tableView.getItems().clear();
                IMetier metier = new IMetierImpl();
                types.addAll(metier.getAllTypes());
                tableView.setItems(types);
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
      loader.setLocation(getClass().getResource("NouveauType.fxml"));
      Scene scene = new Scene(loader.load());
      stage.initModality(Modality.APPLICATION_MODAL);
      stage.setTitle("Nouveau type");
      stage.setScene(scene);
      stage.getIcons().add(new Image("https://img.icons8.com/emoji/344/hotel-emoji.png"));
      stage.show();
      stage.setOnCloseRequest(
          new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent e) {
              tableView.getItems().clear();
              IMetier metier = new IMetierImpl();
              types.addAll(metier.getAllTypes());
              id.setCellValueFactory(new PropertyValueFactory<>("id_type"));
              INTITULE.setCellValueFactory(new PropertyValueFactory<>("intitule"));
              CAPACITE.setCellValueFactory(new PropertyValueFactory<>("capacité"));
              PRIX.setCellValueFactory(new PropertyValueFactory<>("prix"));
              tableView.setItems(types);
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
    types.addAll(m.searchType(keyWord));
    tableView.setItems(types);
  }

  @FXML
  void supprimer(ActionEvent event) {
    int indice = tableView.getSelectionModel().getSelectedIndex();
    if (indice >= 0) {
      try {
        IMetier metier = new IMetierImpl();
        metier.delType(tableView.getItems().get(indice).getId_type());
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
    IMetier metier = new IMetierImpl();
    types.addAll(metier.getAllTypes());
    id.setCellValueFactory(new PropertyValueFactory<>("id_type"));
    INTITULE.setCellValueFactory(new PropertyValueFactory<>("intitule"));
    CAPACITE.setCellValueFactory(new PropertyValueFactory<>("capacité"));
    PRIX.setCellValueFactory(new PropertyValueFactory<>("prix"));
    tableView.setItems(types);
  }
}
