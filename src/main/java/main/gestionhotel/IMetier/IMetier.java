package main.gestionhotel.IMetier;

import main.gestionhotel.ClassesPersistants.*;

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
    List<Chambre> getAllChambres();

    //entite reservation
    void addReservation(Reservation p);
    List<Reservation> getAllReservation();
    void delReservation(int id);
    List<Reservation> searchReservation(String keyWord);

    //entite type
    void addType(Type_Chambre p);
    List<Type_Chambre> getAllTypes();
    void delType(int id);
    List<Type_Chambre> searchType(String keyWord);
}
