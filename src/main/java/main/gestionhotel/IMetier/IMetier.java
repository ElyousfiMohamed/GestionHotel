package main.gestionhotel.IMetier;

import main.gestionhotel.ClassesPersistants.Chambre;
import main.gestionhotel.ClassesPersistants.Client;
import main.gestionhotel.ClassesPersistants.Employe;
import main.gestionhotel.ClassesPersistants.Reservation;

import java.util.List;

public interface IMetier {
    //entite employe
    void addEmploye(Employe p);
    List<Employe> getAllEmployes();
    void delEmploye(int id);
    List<Employe> searchEmp(String keyWord);

    //entite client
    void addClient(Client p);
    List<Client> getAllClients();
    void delClient(int id);
    List<Client> searchClt(String keyWord);

    //entite chambre
    void addChambre(Chambre p);
    List<Chambre> getAllChambreDispo();
    void delChambre(int id);
    List<Chambre> searchChambre(String keyWord);

    //entite reservation
    void addReservation(Reservation p);
    List<Reservation> getAllReservation();
    void delReservation(int id);
    List<Reservation> searchReservation(String keyWord);
}
