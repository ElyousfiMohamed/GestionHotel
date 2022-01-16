package main.gestionhotel.IMetier;

import javafx.scene.control.Alert;
import main.gestionhotel.ClassesPersistants.*;
import main.gestionhotel.Database.SingletonConnexionDB;

import java.security.MessageDigest;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class IMetierImpl implements IMetier {

  private static MessageDigest md;
  public static Client client = new Client();
  public static String ROLE = "";
  public static Type_Chambre type = new Type_Chambre();
  public static Employe employe = new Employe();
  public static Chambre chambre = new Chambre();
  public static Reservation reservation = new Reservation();

  // entite Employe
  public static String cryptWithMD5(String password) {
    try {
      md = MessageDigest.getInstance("MD5");
      byte[] passBytes = password.getBytes();
      md.reset();
      byte[] digested = md.digest(passBytes);
      StringBuffer sb = new StringBuffer();
      for (int i = 0; i < digested.length; i++) {
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
      ResultSet rs =
          pstn.executeQuery(
              "SELECT EMAIL_EMP,PASSWORD,FONCTION FROM employe WHERE EMAIL_EMP='" + email + "'");
      while (rs.next()) {
        if (rs.getString("EMAIL_EMP").equals(email) && rs.getString("PASSWORD").equals(cryptWithMD5(password))) {
          ROLE=rs.getString("FONCTION");
          return true;
        }
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
      pstn.executeUpdate(
          "UPDATE employe SET "
              + "CIN_E  = '"
              + employe.getCin()
              + "',NOM_EMP = '"
              + employe.getNom()
              + "',PRENOM_EMP = '"
              + employe.getPrenom()
              + "',NUMTEL = '"
              + employe.getTelephone()
              + "',EMAIL_EMP = '"
              + employe.getEmail()
              + "',FONCTION = '"
              + employe.getFonction()
              + "' WHERE ID_EMP = "
              + employe.getId());
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
      pstn.executeUpdate(
          "INSERT INTO employe(CIN_E,NOM_EMP,PRENOM_EMP,NUMTEL,EMAIL_EMP,FONCTION,PASSWORD) VALUES "
              + "('"
              + e.getCin()
              + "','"
              + e.getNom()
              + "','"
              + e.getPrenom()
              + "','"
              + e.getTelephone()
              + "','"
              + e.getEmail()
              + "','"
              + e.getFonction()
              + "','"
              + cryptWithMD5(e.getPassword())
              + "')");
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
        Employe e =
            new Employe(
                rs.getInt(1),
                rs.getString(2),
                rs.getString(3),
                rs.getString(4),
                rs.getString(5),
                rs.getString(6),
                rs.getString(7));
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
      ResultSet rs =
          stm.executeQuery("SELECT * FROM employe WHERE NOM_EMP LIKE '%" + keyWord + "%'");
      while (rs.next()) {
        Employe e =
            new Employe(
                rs.getInt(1),
                rs.getString(2),
                rs.getString(3),
                rs.getString(4),
                rs.getString(5),
                rs.getString(6),
                rs.getString(7));
        employes.add(e);
      }
    } catch (Exception ex) {
      Alert alert = new Alert(Alert.AlertType.ERROR);
      alert.setContentText(ex.getMessage());
      alert.show();
    }
    return employes;
  }

  // entite client
  @Override
  public void addClient(Client p) {
    Connection conn = SingletonConnexionDB.getConnection();
    try {
      Statement pstn = conn.createStatement();
      pstn.executeUpdate(
          "INSERT INTO `client`(`CIN`, `NOM_CLT`, `PRENOM_CLT`, `EMAIL`, `NUMTEL_CLT`, `ADRESSE`) VALUES ('"
              + p.getCIN_cl()
              + "','"
              + p.getNom_cl()
              + "','"
              + p.getPrenom_cl()
              + "','"
              + p.getEmail_cl()
              + "','"
              + p.getNumtel_cl()
              + "','"
              + p.getAdresse_cl()
              + "')");
      Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
      alert.setContentText("Client ajouté avec succés");
      alert.show();
    } catch (Exception ex) {
      Alert alert = new Alert(Alert.AlertType.ERROR);
      alert.setContentText(ex.getMessage());
      alert.show();
    }
  }

  public static void updateClient(Client p) {
    Connection conn = SingletonConnexionDB.getConnection();
    try {
      Statement pstn = conn.createStatement();
      pstn.executeUpdate(
          "UPDATE client SET "
              + "CIN  = '"
              + client.getCIN_cl()
              + "',NOM_CLT = '"
              + client.getNom_cl()
              + "',PRENOM_CLT = '"
              + client.getPrenom_cl()
              + "',NUMTEL_CLT = '"
              + client.getNumtel_cl()
              + "',EMAIL = '"
              + client.getEmail_cl()
              + "',ADRESSE = '"
              + client.getAdresse_cl()
              + "' WHERE ID_CL = "
              + client.getId_cl());
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
        Client c =
            new Client(
                rs.getInt(1),
                rs.getString(2),
                rs.getString(3),
                rs.getString(4),
                rs.getString(5),
                rs.getString(6),
                rs.getString(7));
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
      ResultSet rs =
          stm.executeQuery("SELECT * FROM client WHERE NOM_CLT LIKE '%" + keyWord + "%'");
      while (rs.next()) {
        Client c =
            new Client(
                rs.getInt(1),
                rs.getString(2),
                rs.getString(3),
                rs.getString(4),
                rs.getString(5),
                rs.getString(6),
                rs.getString(7));
        clients.add(c);
      }
    } catch (Exception ex) {
      Alert alert = new Alert(Alert.AlertType.ERROR);
      alert.setContentText(ex.getMessage());
      alert.show();
    }
    return clients;
  }

  // entite chambre
  @Override
  public List<Chambre> getAllChambres() {
    List<Chambre> chambres = new ArrayList<>();
    try {
      Connection conn = SingletonConnexionDB.getConnection();
      Statement pstn = conn.createStatement();
      ResultSet rs = pstn.executeQuery("SELECT * FROM chambre");
      ResultSet rss;
      while (rs.next()) {
        Chambre c = new Chambre(rs.getInt(1), rs.getInt(3), rs.getString(4), rs.getBoolean(5));
        c.getType_chambre().setId_type(rs.getInt(2));
        chambres.add(c);
      }
      for (Chambre c : chambres) {
        rss =
            pstn.executeQuery(
                "SELECT * FROM type_c WHERE ID_T = '" + c.getType_chambre().getId_type() + "'");
        rss.next();
        c.setType_chambre(
            new Type_Chambre(rss.getInt(1), rss.getString(2), rss.getInt(3), rss.getFloat(4)));
        rss.close();
      }
    } catch (Exception e) {
      e.printStackTrace();
      Alert alert = new Alert(Alert.AlertType.ERROR);
      alert.setContentText(e.getMessage());
      alert.show();
    }
    return chambres;
  }

  public static void updateChambre() {
    Connection conn = SingletonConnexionDB.getConnection();
    try {
      Statement pstn = conn.createStatement();
      int i = chambre.isDispo_chmbr() ? 1 : 0;
      pstn.executeUpdate(
          "UPDATE chambre SET "
              + "NUM_CHAMBRE = '"
              + chambre.getNum_chmbr()
              + "',DESC_CHAMBRE = '"
              + chambre.getDesq_chmbr()
              + "',DISPO = '"
              + i
              + "' WHERE ID_C = "
              + chambre.getId_chmbr());
      Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
      alert.setContentText("Chambre modifié avec succés");
      alert.show();
    } catch (Exception e) {
      Alert alert = new Alert(Alert.AlertType.ERROR);
      alert.setContentText(e.getMessage());
      alert.show();
    }
  }

  @Override
  public void addChambre(Chambre p) {
    Connection conn = SingletonConnexionDB.getConnection();
    try {
      Statement pstn = conn.createStatement();
      int i = p.isDispo_chmbr() ? 1 : 0;
      pstn.executeUpdate(
          "INSERT INTO `chambre`(`ID_T`, `NUM_CHAMBRE`, `DESC_CHAMBRE`, `DISPO`) VALUES ('"
              + chambre.getType_chambre().getId_type()
              + "','"
              + p.getNum_chmbr()
              + "','"
              + p.getDesq_chmbr()
              + "','"
              + i
              + "')");
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
        Chambre p = new Chambre(rs.getInt(1), rs.getInt(3), rs.getString(4), rs.getBoolean(5));
        if (p.isDispo_chmbr()) {
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
      ResultSet rs =
          stm.executeQuery("SELECT * FROM chambre WHERE NUM_CHAMBRE LIKE '%" + keyWord + "%'");
      while (rs.next()) {
        Chambre p = new Chambre(rs.getInt(1), rs.getInt(3), rs.getString(4), rs.getBoolean(5));
        chambres.add(p);
      }
    } catch (Exception ex) {
      Alert alert = new Alert(Alert.AlertType.ERROR);
      alert.setContentText(ex.getMessage());
      alert.show();
    }
    return chambres;
  }

  // entite reservation
  public static void updateReservation() {
    Connection conn = SingletonConnexionDB.getConnection();
    try {
      Statement pstn = conn.createStatement();
      pstn.executeUpdate(
          "UPDATE reservation SET "
              + "NUMERO_RSV  = '"
              + reservation.getNum_res()
              + "',NOMBRE_PER = '"
              + reservation.getNum_pers()
              + "',NOMBRE_CH = '"
              + reservation.getNum_chbr()
              + "',DATE_ARIV = '"
              + reservation.getDate_arv()
              + "',DATE_SORT = '"
              + reservation.getDate_sort()
              + "',TOTAL_RSV = '"
              + reservation.getTotal_rsv()
              + "' WHERE ID_R = "
              + reservation.getId_res());
      Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
      alert.setContentText("Reservation modifié avec succés");
      alert.show();
    } catch (Exception e) {
      Alert alert = new Alert(Alert.AlertType.ERROR);
      alert.setContentText(e.getMessage());
      alert.show();
    }
  }

  @Override
  public void addReservation(Reservation p) {
    Connection conn = SingletonConnexionDB.getConnection();
    try {
      Statement pstn = conn.createStatement();
      pstn.executeUpdate(
          "INSERT INTO `reservation`(`NUMERO_RSV`, `NOMBRE_PER`, `NOMBRE_CH`, `DATE_ARIV`, `DATE_SORT`, `TOTAL_RSV`) VALUES ('"
              + p.getNum_res()
              + "','"
              + p.getNum_pers()
              + "','"
              + p.getNum_chbr()
              + "','"
              + p.getDate_arv()
              + "','"
              + p.getDate_sort()
              + "','"
              + p.getTotal_rsv()
              + "')");
      ResultSet rs =
          pstn.executeQuery(
              "SELECT ID_R FROM reservation WHERE NUMERO_RSV='" + p.getNum_res() + "'");
      rs.next();
      p.setId_res(rs.getInt("ID_R"));

      for (Chambre c : p.getChambres()) {
        pstn.executeUpdate(
            "INSERT INTO `concerner`(`ID_R`, `ID_C`) VALUES ('"
                + p.getId_res()
                + "','"
                + c.getId_chmbr()
                + "')");
      }

      pstn.executeUpdate(
          "INSERT INTO `reserver`(`ID_CL`, `ID_R`) VALUES ('"
              + p.getClient().getId_cl()
              + "','"
              + p.getId_res()
              + "')");

      Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
      alert.setContentText("Reservation ajouté avec succés");
      alert.show();
    } catch (Exception ex) {
      Alert alert = new Alert(Alert.AlertType.ERROR);
      alert.setContentText(ex.getMessage());
      alert.show();
      ex.printStackTrace();
    }
  }

  @Override
  public List<Reservation> getAllReservation() {
    List<Reservation> reservations = new ArrayList<>();
    try {
      Connection conn = SingletonConnexionDB.getConnection();
      Statement pstn = conn.createStatement();
      ResultSet rs = pstn.executeQuery("SELECT * FROM reservation");
      while (rs.next()) {
        Reservation p =
            new Reservation(
                rs.getInt(1),
                rs.getInt(2),
                rs.getInt(3),
                rs.getInt(4),
                rs.getDate(5),
                rs.getDate(6),
                rs.getFloat(7));
        /*
         *
         *   GETTING RELATED CLIENT AND CHAMBRES
         *
         * */
        reservations.add(p);
      }
      return reservations;
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
      st.executeUpdate("DELETE FROM concerner WHERE ID_R=" + id);
      st.executeUpdate("DELETE FROM reserver WHERE ID_R=" + id);
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
      ResultSet rs =
          stm.executeQuery("SELECT * FROM reservation WHERE NUMERO_RSV LIKE '%" + keyWord + "%'");
      while (rs.next()) {
        Reservation p =
            new Reservation(
                rs.getInt(1),
                rs.getInt(2),
                rs.getInt(3),
                rs.getInt(4),
                rs.getDate(5),
                rs.getDate(6),
                rs.getFloat(7));
        reservations.add(p);
      }
    } catch (Exception ex) {
      Alert alert = new Alert(Alert.AlertType.ERROR);
      alert.setContentText(ex.getMessage());
      alert.show();
    }
    return reservations;
  }

  @Override
  public void addType(Type_Chambre p) {
    Connection conn = SingletonConnexionDB.getConnection();
    try {
      Statement pstn = conn.createStatement();
      pstn.executeUpdate(
          "INSERT INTO `type_c`(`INTITULE`, `CAPACITE`, `PRIX`) VALUES ('"
              + p.getIntitule()
              + "','"
              + p.getCapacité()
              + "','"
              + p.getPrix()
              + "')");

      Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
      alert.setContentText("Type ajouté avec succés");
      alert.show();
    } catch (Exception ex) {
      Alert alert = new Alert(Alert.AlertType.ERROR);
      alert.setContentText(ex.getMessage());
      alert.show();
      ex.printStackTrace();
    }
  }

  @Override
  public List<Type_Chambre> getAllTypes() {
    List<Type_Chambre> types = new ArrayList<>();
    try {
      Connection conn = SingletonConnexionDB.getConnection();
      Statement pstn = conn.createStatement();
      ResultSet rs = pstn.executeQuery("SELECT * FROM type_c");
      while (rs.next()) {
        Type_Chambre p =
            new Type_Chambre(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getFloat(4));
        types.add(p);
      }
    } catch (Exception e) {
      e.printStackTrace();
      Alert alert = new Alert(Alert.AlertType.ERROR);
      alert.setContentText(e.getMessage());
      alert.show();
    }
    return types;
  }

  @Override
  public void delType(int id) {
    try {
      Connection conn = SingletonConnexionDB.getConnection();
      Statement st = conn.createStatement();
      st.executeUpdate("DELETE FROM type_c WHERE ID_T=" + id);
      Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
      alert.setContentText("Type supprimé avec succés");
      alert.show();
    } catch (Exception e) {
      Alert alert = new Alert(Alert.AlertType.ERROR);
      alert.setContentText(e.getMessage());
      alert.show();
    }
  }

  @Override
  public List<Type_Chambre> searchType(String keyWord) {
    List<Type_Chambre> types = new ArrayList<>();
    try {
      Connection connx = SingletonConnexionDB.getConnection();
      Statement stm = connx.createStatement();
      ResultSet rs =
          stm.executeQuery("SELECT * FROM type_c WHERE INTITULE LIKE '%" + keyWord + "%'");
      while (rs.next()) {
        Type_Chambre p =
            new Type_Chambre(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getFloat(4));
        types.add(p);
      }
    } catch (Exception ex) {
      Alert alert = new Alert(Alert.AlertType.ERROR);
      alert.setContentText(ex.getMessage());
      alert.show();
    }
    return types;
  }

  public static void updateType() {
    Connection conn = SingletonConnexionDB.getConnection();
    try {
      Statement pstn = conn.createStatement();
      pstn.executeUpdate(
          "UPDATE type_c SET "
              + "INTITULE  = '"
              + type.getIntitule()
              + "',CAPACITE = '"
              + type.getCapacité()
              + "',PRIX = '"
              + type.getPrix()
              + "' WHERE ID_T = '"
              + type.getId_type()
              + "'");
      Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
      alert.setContentText("Type modifié avec succés");
      alert.show();
    } catch (Exception e) {
      Alert alert = new Alert(Alert.AlertType.ERROR);
      alert.setContentText(e.getMessage());
      alert.show();
    }
  }
}
