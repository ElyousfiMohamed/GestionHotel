package main.gestionhotel.IMetier;

import javafx.scene.control.Alert;
import main.gestionhotel.ClassesPersistants.Employe;
import main.gestionhotel.Database.SingletonConnexionDB;

import java.security.MessageDigest;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class IMetierImpl implements IMetier{

    private static MessageDigest md;
    public static Employe employe;

    public static String cryptWithMD5(String password){
        try {
            md = MessageDigest.getInstance("MD5");
            byte[] passBytes = password.getBytes();
            md.reset();
            byte[] digested = md.digest(passBytes);
            StringBuffer sb = new StringBuffer();
            for(int i=0;i<digested.length;i++){
                sb.append(Integer.toHexString(0xff & digested[i]));
            }
            return sb.toString();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public static boolean connect(String email, String password) {
        Connection conn = SingletonConnexionDB.getConnection();
        try {
            Statement pstn = conn.createStatement();
            ResultSet rs = pstn.executeQuery("SELECT EMAIL_EMP,PASSWORD FROM employe WHERE EMAIL_EMP='"+email+"'");
            while (rs.next()) {
                if(rs.getString("EMAIL_EMP").equals(email) && rs.getString("PASSWORD").equals(cryptWithMD5(password)))
                    return true;
            }
        } catch (Exception e) {
            return false;
        }
        return false;
    }

    public static void updateEmploye() {
        Connection conn = SingletonConnexionDB.getConnection();
        try {
            Statement pstn = conn.createStatement();
            pstn.executeUpdate("UPDATE employe SET "
                    + "CIN_E  = '" + employe.getCin() + "',NOM_EMP = '"
                    + employe.getNom() + "',PRENOM_EMP = '"
                    + employe.getPrenom() + "',NUMTEL = '"
                    + employe.getTelephone() + "',EMAIL_EMP = '"
                    + employe.getEmail() + "',FONCTION = '"
                    + employe.getFonction() + "' WHERE ID_EMP = " + employe.getId());
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setContentText("Employé modifié avec succés");
            alert.show();
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText(e.getMessage());
            alert.show();
        }
    }

    @Override
    public void addEmploye(Employe e) {
        Connection conn = SingletonConnexionDB.getConnection();
        try {
            Statement pstn = conn.createStatement();
            pstn.executeUpdate("INSERT INTO employe(CIN_E,NOM_EMP,PRENOM_EMP,NUMTEL,EMAIL_EMP,FONCTION,PASSWORD) VALUES "
                    + "('" + e.getCin() + "','"
                    + e.getNom() + "','"
                    + e.getPrenom() + "','"
                    + e.getTelephone()+ "','"
                    + e.getEmail() + "','"
                    + e.getFonction() + "','"
                    + cryptWithMD5(e.getPassword())+"')");
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setContentText("Employé ajouté avec succés");
            alert.show();
        } catch (Exception ex) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText(ex.getMessage());
            alert.show();
        }
    }

    @Override
    public List<Employe> getAllEmployes() {
        List<Employe> employes = new ArrayList<>();
        try {
            Connection conn = SingletonConnexionDB.getConnection();
            Statement pstn = conn.createStatement();
            ResultSet rs = pstn.executeQuery("SELECT * FROM employe");
            while (rs.next()) {
                Employe e = new Employe(rs.getInt(1), rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7));
                employes.add(e);
            }
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText(e.getMessage());
            alert.show();
        }
        return employes;
    }

    @Override
    public void delEmploye(int id) {
        try {
            Connection conn = SingletonConnexionDB.getConnection();
            Statement st = conn.createStatement();
            st.executeUpdate("DELETE FROM employe WHERE ID_EMP=" + id);
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setContentText("Employé supprimé avec succés");
            alert.show();
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText(e.getMessage());
            alert.show();
        }
    }

    @Override
    public List<Employe> searchEmp(String keyWord) {
        List<Employe> employes = new ArrayList<>();
        try {
            Connection connx = SingletonConnexionDB.getConnection();
            Statement stm = connx.createStatement();
            ResultSet rs = stm.executeQuery("SELECT * FROM employe WHERE NOM_EMP LIKE '%" + keyWord + "%'");
            while (rs.next()) {
                Employe e = new Employe(rs.getInt(1), rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7));
                employes.add(e);
            }
        } catch (Exception ex) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText(ex.getMessage());
            alert.show();
        }
        return employes;
    }
}
