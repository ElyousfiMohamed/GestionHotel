package main.gestionhotel.Authentification.EspaceClient;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
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
import main.gestionhotel.ClassesPersistants.Reservation;
import main.gestionhotel.IMetier.IMetier;
import main.gestionhotel.IMetier.IMetierImpl;

import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;

public class GestionReservationController implements Initializable {

  protected static ObservableList<Reservation> reservations = FXCollections.observableArrayList();

  @FXML private TableView<Reservation> tableView = new TableView<>();

  @FXML private TableColumn<Reservation, Integer> ID;

  @FXML private TableColumn<Reservation, Integer> NUM_RSV;

  @FXML private TableColumn<Reservation, Integer> NBR_PRS;

  @FXML private TableColumn<Reservation, Integer> NBR_CHBR;

  @FXML private TableColumn<Reservation, Date> DATE_ARIV;

  @FXML private TableColumn<Reservation, Date> DATE_SORT;

  @FXML private TableColumn<Reservation, Float> TOTAL_RSV;

  @FXML private TextField rechercher;

  @FXML
  void modifier(ActionEvent event) {
    int indice = tableView.getSelectionModel().getSelectedIndex();
    if (indice >= 0) {
      try {
        IMetierImpl.reservation = tableView.getItems().get(indice);

        Stage stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("UpdateRes.fxml"));

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("modifier reservation");
        stage.getIcons().add(new Image("https://img.icons8.com/emoji/344/hotel-emoji.png"));
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.show();
        stage.setOnCloseRequest(
            new EventHandler<WindowEvent>() {
              @Override
              public void handle(WindowEvent e) {
                tableView.getItems().clear();
                IMetier metier = new IMetierImpl();
                reservations.addAll(metier.getAllReservation());
                tableView.setItems(reservations);
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
      loader.setLocation(getClass().getResource("NouveauRes.fxml"));
      Scene scene = new Scene(loader.load());
      stage.initModality(Modality.APPLICATION_MODAL);
      stage.setTitle("Nouveau reservation");
      stage.setScene(scene);
      stage.getIcons().add(new Image("https://img.icons8.com/emoji/344/hotel-emoji.png"));
      stage.show();
      stage.setOnCloseRequest(
              new EventHandler<WindowEvent>() {
                @Override
                public void handle(WindowEvent e) {
                  tableView.getItems().clear();
                  IMetier metier = new IMetierImpl();

                  reservations.addAll(metier.getAllReservation());
                  ID.setCellValueFactory(new PropertyValueFactory<Reservation, Integer>("id_res"));
                  NUM_RSV.setCellValueFactory(new PropertyValueFactory<Reservation, Integer>("num_res"));
                  NBR_PRS.setCellValueFactory(new PropertyValueFactory<Reservation, Integer>("num_pers"));
                  NBR_CHBR.setCellValueFactory(new PropertyValueFactory<Reservation, Integer>("num_chbr"));
                  DATE_ARIV.setCellValueFactory(new PropertyValueFactory<Reservation, Date>("date_arv"));
                  DATE_SORT.setCellValueFactory(new PropertyValueFactory<Reservation, Date>("date_sort"));
                  TOTAL_RSV.setCellValueFactory(new PropertyValueFactory<Reservation, Float>("total_rsv"));
                  tableView.setItems(reservations);
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
    reservations.addAll(m.searchReservation(keyWord));
    tableView.setItems(reservations);
  }

  @FXML
  void supprimer(ActionEvent event) {
    int indice = tableView.getSelectionModel().getSelectedIndex();
    if (indice >= 0) {
      try {
        IMetier metier = new IMetierImpl();
        metier.delReservation(tableView.getItems().get(indice).getId_res());
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

    reservations.addAll(metier.getAllReservation());
    ID.setCellValueFactory(new PropertyValueFactory<Reservation, Integer>("id_res"));
    NUM_RSV.setCellValueFactory(new PropertyValueFactory<Reservation, Integer>("num_res"));
    NBR_PRS.setCellValueFactory(new PropertyValueFactory<Reservation, Integer>("num_pers"));
    NBR_CHBR.setCellValueFactory(new PropertyValueFactory<Reservation, Integer>("num_chbr"));
    DATE_ARIV.setCellValueFactory(new PropertyValueFactory<Reservation, Date>("date_arv"));
    DATE_SORT.setCellValueFactory(new PropertyValueFactory<Reservation, Date>("date_sort"));
    TOTAL_RSV.setCellValueFactory(new PropertyValueFactory<Reservation, Float>("total_rsv"));
    tableView.setItems(reservations);
  }
}
