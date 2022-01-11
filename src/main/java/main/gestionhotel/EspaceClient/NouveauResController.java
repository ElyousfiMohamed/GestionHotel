package main.gestionhotel.EspaceClient;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import main.gestionhotel.ClassesPersistants.Reservation;
import main.gestionhotel.IMetier.IMetier;
import main.gestionhotel.IMetier.IMetierImpl;

import java.sql.Date;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;

public class NouveauResController {

  @FXML private AnchorPane rootPane;

  @FXML private TextField NUM_RSV;

  @FXML private TextField NBR_PRS;

  @FXML private DatePicker DATE_ARRV;

  @FXML private DatePicker DATE_SORT;

  @FXML private Button SELECT_CHMBRS;

  @FXML private Button SELECT_CLIENT;

  @FXML
  void annuler(ActionEvent event) {
    Stage stage = (Stage) rootPane.getScene().getWindow();
    stage.close();
  }

  @FXML
  void save(ActionEvent event) {
    try {
      IMetier metier = new IMetierImpl();

      LocalDate localDate = DATE_ARRV.getValue();
      Instant instant = Instant.from(localDate.atStartOfDay(ZoneId.systemDefault()));
      Date date1 = (Date) Date.from(instant);
      localDate = DATE_SORT.getValue();
      instant = Instant.from(localDate.atStartOfDay(ZoneId.systemDefault()));
      Date date2 = (Date) Date.from(instant);

      IMetierImpl.reservation.setNum_res(Integer.parseInt(this.NUM_RSV.getText()));
      IMetierImpl.reservation.setNum_pers(Integer.parseInt(this.NBR_PRS.getText()));
      IMetierImpl.reservation.setNum_chbr(IMetierImpl.reservation.getChambres().size());
      IMetierImpl.reservation.setDate_arv(date1);
      IMetierImpl.reservation.setDate_sort(date2);

      //calcul de prix total a ajotuer ici :
      /*
      *
      *   CALCUL PRIX TOTAL ICIIIIII
      *
      * */
      metier.addReservation(IMetierImpl.reservation);
    } catch (Exception ex) {
      ex.printStackTrace();
    }
  }

  @FXML
  void select_chambres(ActionEvent event) {
    try {
      Stage stage = new Stage();
      FXMLLoader loader = new FXMLLoader();
      loader.setLocation(getClass().getResource("SelectChambres.fxml"));
      Scene scene = new Scene(loader.load());
      stage.initModality(Modality.APPLICATION_MODAL);
      stage.setTitle("Selectioner les chambres");
      stage.getIcons().add(new Image("https://img.icons8.com/emoji/344/hotel-emoji.png"));
      stage.setScene(scene);
      stage.show();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  @FXML
  void select_client(ActionEvent event) {
    try {
      Stage stage = new Stage();
      FXMLLoader loader = new FXMLLoader();
      loader.setLocation(getClass().getResource("SelectClient.fxml"));
      Scene scene = new Scene(loader.load());
      stage.initModality(Modality.APPLICATION_MODAL);
      stage.setTitle("Selectioner le client");
      stage.getIcons().add(new Image("https://img.icons8.com/emoji/344/hotel-emoji.png"));
      stage.setScene(scene);
      stage.show();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
