package main.gestionhotel.IMetier;

import javafx.scene.control.Alert;
import main.gestionhotel.ClassesPersistants.Employe;
import main.gestionhotel.Database.SingletonConnexionDB;

import java.security.MessageDigest;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class IMetierImpl implements IMetier{

    private static MessageDigest md;

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
        return null;
    }

    @Override
    public void delProfesseur(int id) {

    }

    @Override
    public List<Employe> searchProf(String keyWord) {
        return null;
    }
}
