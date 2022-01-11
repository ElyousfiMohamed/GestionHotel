package main.gestionhotel.IReservation;

import javafx.scene.control.Alert;
import main.gestionhotel.ClassesPersistants.Reservation;
import main.gestionhotel.Database.SingletonConnexionDB;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class IReservationImpl implements IReservation{
    @Override
    public void addReservation(Reservation p) {
        Connection conn = SingletonConnexionDB.getConnection();
        try {
            Statement pstn = conn.createStatement();
            String s = pstn.executeUpdate("INSERT INTO `reservation`(`NUMERO_RSV`, `NOMBRE_PER`, `NOMBRE_CH`, `DATE_ARIV`, `DATE_SORT`, `TOTAL_RSV`) VALUES "
                    + "('" + p.getNum_res() + "','"
                    + p.getNum_pers() + "','"
                    + p.getNum_chbr()) + "','"
                    + p.getDate_arv() + "','"
                    + p.getDate_sort() + "','"
                    + p.getTotal_rsv() + "','";
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setContentText("Reservation ajouté avec succés");
            alert.show();
        } catch (Exception ex) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText(ex.getMessage());
            alert.show();
        }
    }

    @Override
    public List<Reservation> getAllReservation() {
        List<Reservation> reservations = new ArrayList<>();
        try {
            Connection conn = SingletonConnexionDB.getConnection();
            Statement pstn = conn.createStatement();
            ResultSet rs = pstn.executeQuery("SELECT * FROM client");
            while (rs.next()) {
                Reservation p = new Reservation(rs.getInt(1), rs.getInt(2),rs.getInt(3),rs.getInt(4),rs.getDate(5),rs.getDate(6),rs.getFloat(7));
                reservations.add(p);
            }
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText(e.getMessage());
            alert.show();
        }
        return reservations;
    }

    @Override
    public void delReservation(int id) {
        try {
            Connection conn = SingletonConnexionDB.getConnection();
            Statement st = conn.createStatement();
            st.executeUpdate("DELETE FROM reservation WHERE ID_R=" + id);
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setContentText("reservation supprimé avec succés");
            alert.show();
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText(e.getMessage());
            alert.show();
        }
    }

    @Override
    public List<Reservation> searchReservation(String keyWord) {
        List<Reservation> reservations = new ArrayList<>();
        try {
            Connection connx = SingletonConnexionDB.getConnection();
            Statement stm = connx.createStatement();
            ResultSet rs = stm.executeQuery("SELECT * FROM reservation WHERE ID_R LIKE '%" + keyWord + "%'");
            while (rs.next()) {
                Reservation p = new Reservation(rs.getInt(1), rs.getInt(2),rs.getInt(3),rs.getInt(4),rs.getDate(5),rs.getDate(6),rs.getFloat(7));
                reservations.add(p);
            }
        } catch (Exception ex) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText(ex.getMessage());
            alert.show();
        }
        return reservations;
    }
}
