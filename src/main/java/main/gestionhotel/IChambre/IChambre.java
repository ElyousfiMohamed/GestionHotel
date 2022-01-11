package main.gestionhotel.IChambre;


import main.gestionhotel.ClassesPersistants.Chambre;

import java.util.List;

public interface IChambre {
    void addChambre(Chambre p);
    List<Chambre> getAllChambreDispo();
    void delChambre(int id);
    List<Chambre> searchChambre(String keyWord);
}
