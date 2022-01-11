package main.gestionhotel.IClient;

import javafx.scene.control.Alert;
import main.gestionhotel.ClassesPersistants.Client;
import main.gestionhotel.ClassesPersistants.Employe;
import main.gestionhotel.Database.SingletonConnexionDB;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class IClientImpl implements IClient{
    public static Client client;
    @Override
    public void addClient(Client p) {
        Connection conn = SingletonConnexionDB.getConnection();
        try {
            Statement pstn = conn.createStatement();
            String s = pstn.executeUpdate("INSERT INTO `client`(`CIN`, `NOM_CLT`, `PRENOM_CLT`, `EMAIL`, `NUMTEL_CLT`, `ADRESSE`) VALUES "
                    + "('" + p.getCIN_cl() + "','"
                    + p.getNom_cl() + "','"
                    + p.getPrenom_cl()) + "','"
                    + p.getEmail_cl() + "','"
                    + p.getNumtel_cl() + "','"
                    + p.getAdresse_cl() + "','";
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setContentText("Client ajouté avec succés");
            alert.show();
        } catch (Exception ex) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText(ex.getMessage());
            alert.show();
        }
    }

    @Override
    public void updateClient(Client p) {
        Connection conn = SingletonConnexionDB.getConnection();
        try {
            Statement pstn = conn.createStatement();
            pstn.executeUpdate("UPDATE client SET "
                    + "CIN  = '" + client.getCIN_cl() + "',NOM_CLT = '"
                    + client.getNom_cl() + "',PRENOM_CLT = '"
                    + client.getPrenom_cl() + "',NUMTEL_CLT = '"
                    + client.getNumtel_cl() + "',EMAIL = '"
                    + client.getEmail_cl() + "',ADRESSE = '"
                    + client.getAdresse_cl() + "' WHERE ID_CL = " + client.getId_cl());
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setContentText("Client modifié avec succés");
            alert.show();
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText(e.getMessage());
            alert.show();
        }
    }

    @Override
    public List<Client> getAllClients() {
        List<Client> clients = new ArrayList<>();
        try {
            Connection conn = SingletonConnexionDB.getConnection();
            Statement pstn = conn.createStatement();
            ResultSet rs = pstn.executeQuery("SELECT * FROM client");
            while (rs.next()) {
                Client c = new Client(rs.getInt(1), rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7));
                clients.add(c);
            }
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText(e.getMessage());
            alert.show();
        }
        return clients;
    }

    @Override
    public void delClient(int id) {
        try {
            Connection conn = SingletonConnexionDB.getConnection();
            Statement st = conn.createStatement();
            st.executeUpdate("DELETE FROM client WHERE ID_CL=" + id);
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setContentText("Client supprimé avec succés");
            alert.show();
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText(e.getMessage());
            alert.show();
        }
    }

    @Override
    public List<Client> searchClt(String keyWord) {
        List<Client> clients = new ArrayList<>();
        try {
            Connection connx = SingletonConnexionDB.getConnection();
            Statement stm = connx.createStatement();
            ResultSet rs = stm.executeQuery("SELECT * FROM client WHERE NOM_CLT LIKE '%" + keyWord + "%'");
            while (rs.next()) {
                Client c = new Client(rs.getInt(1), rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7));
                clients.add(c);
            }
        } catch (Exception ex) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText(ex.getMessage());
            alert.show();
        }
        return clients;
    }
}
