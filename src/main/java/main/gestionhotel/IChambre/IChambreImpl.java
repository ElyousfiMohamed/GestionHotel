package main.gestionhotel.IChambre;

import javafx.scene.control.Alert;
import main.gestionhotel.ClassesPersistants.Chambre;
import main.gestionhotel.Database.SingletonConnexionDB;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class IChambreImpl implements IChambre{
    @Override
    public void addChambre(Chambre p) {
        Connection conn = SingletonConnexionDB.getConnection();
        try {
            Statement pstn = conn.createStatement();
            String s = pstn.executeUpdate("INSERT INTO `chambre`(`ID_C`, `ID_T`, `NUM_CHAMBRE`, `DESC_CHAMBRE`, `DISPO`) VALUES "
                    + "('" + p.getId_chmbr() + "','"
                    + p.getId_type() + "','"
                    + p.getNum_chmbr()) + "','"
                    + p.getDesq_chmbr() + "','"
                    + p.isDispo_chmbr() + "','";
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setContentText("Chambre ajouté avec succés");
            alert.show();
        } catch (Exception ex) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText(ex.getMessage());
            alert.show();
        }
    }

    @Override
    public List<Chambre> getAllChambreDispo() {
        List<Chambre> chambres = new ArrayList<>();
        try {
            Connection conn = SingletonConnexionDB.getConnection();
            Statement pstn = conn.createStatement();
            ResultSet rs = pstn.executeQuery("SELECT * FROM chambre");
            while (rs.next()) {
                Chambre p = new Chambre(rs.getInt(1), rs.getInt(2),rs.getInt(3),rs.getString(4),rs.getBoolean(5));
                if(p.isDispo_chmbr()) {
                    chambres.add(p);
                }
            }
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText(e.getMessage());
            alert.show();
        }
        return chambres;
    }

    @Override
    public void delChambre(int id) {
        try {
            Connection conn = SingletonConnexionDB.getConnection();
            Statement st = conn.createStatement();
            st.executeUpdate("DELETE FROM chambre WHERE ID_C=" + id);
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setContentText("chambre supprimé avec succés");
            alert.show();
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText(e.getMessage());
            alert.show();
        }
    }

    @Override
    public List<Chambre> searchChambre(String keyWord) {
        List<Chambre> chambres = new ArrayList<>();
        try {
            Connection connx = SingletonConnexionDB.getConnection();
            Statement stm = connx.createStatement();
            ResultSet rs = stm.executeQuery("SELECT * FROM chambre WHERE ID_C LIKE '%" + keyWord + "%'");
            while (rs.next()) {
                Chambre p = new Chambre(rs.getInt(1), rs.getInt(2),rs.getInt(3),rs.getString(4),rs.getBoolean(5));
                chambres.add(p);
            }
        } catch (Exception ex) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText(ex.getMessage());
            alert.show();
        }
        return chambres;
    }
}
