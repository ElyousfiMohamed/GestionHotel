package main.gestionhotel.IMetier;

import main.gestionhotel.ClassesPersistants.Employe;

import java.util.List;

public interface IMetier {
    void addEmploye(Employe p);
    List<Employe> getAllEmployes();
    void delEmploye(int id);
    List<Employe> searchEmp(String keyWord);
}
