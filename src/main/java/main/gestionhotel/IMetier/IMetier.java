package main.gestionhotel.IMetier;

import main.gestionhotel.ClassesPersistants.Employe;

import java.util.List;

public interface IMetier {
    void addEmploye(Employe p);
    List<Employe> getAllEmployes();
    void delProfesseur(int id);
    List<Employe> searchProf(String keyWord);
}
