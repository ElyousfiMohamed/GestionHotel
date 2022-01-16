package main.gestionhotel.Authentification.EspaceClient;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import main.gestionhotel.ClassesPersistants.Chambre;
import main.gestionhotel.IMetier.IMetier;
import main.gestionhotel.IMetier.IMetierImpl;

import java.net.URL;
import java.sql.Date;
import java.util.ResourceBundle;

import static main.gestionhotel.IMetier.IMetierImpl.reservation;

public class UpdateResController implements Initializable {

    @FXML
    private AnchorPane rootPane;

    @FXML
    private TextField NUM_RSV;

    @FXML
    private TextField NBR_PRS;

    @FXML
    private Button SELECT_CHMBRS;

    @FXML
    private Button SELECT_CLIENT;

    @FXML
    private DatePicker DATE_ARRV;

    @FXML
    private DatePicker DATE_SORT;

    @FXML
    void annuler(ActionEvent event) {
        Stage stage = (Stage) rootPane.getScene().getWindow();
        stage.close();
    }

    @FXML
    void save(ActionEvent event) {
        try {
            IMetier metier = new IMetierImpl();

            java.sql.Date date1 = Date.valueOf(DATE_ARRV.valueProperty().get());
            java.sql.Date date2 = Date.valueOf(DATE_SORT.valueProperty().get());

            IMetierImpl.reservation.setNum_res(Integer.parseInt(this.NUM_RSV.getText()));
            IMetierImpl.reservation.setNum_pers(Integer.parseInt(this.NBR_PRS.getText()));
            IMetierImpl.reservation.setDate_arv(date1);
            IMetierImpl.reservation.setDate_sort(date2);

            float totalTemp = 0;
            for(Chambre c : IMetierImpl.reservation.getChambres()) {
                totalTemp = totalTemp + c.getType_chambre().getPrix();
            }
            System.out.println(totalTemp);
            IMetierImpl.reservation.setTotal_rsv(totalTemp);

            IMetierImpl.updateReservation();
            GestionReservationController.reservations.clear();
            GestionReservationController.reservations.addAll(metier.getAllReservation());
            Stage stage = (Stage) rootPane.getScene().getWindow();
            stage.close();
        } catch (Exception ex) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setContentText(ex.getMessage());
            alert.show();
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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.NUM_RSV.setText(String.valueOf(reservation.getNum_res()));
        this.NBR_PRS.setText(String.valueOf(reservation.getNum_pers()));
        this.DATE_ARRV.valueProperty().set(reservation.getDate_arv().toLocalDate());
        this.DATE_SORT.valueProperty().set(reservation.getDate_sort().toLocalDate());
    }
}
