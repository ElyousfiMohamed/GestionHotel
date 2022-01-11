package main.gestionhotel.IReservation;

import main.gestionhotel.ClassesPersistants.Reservation;

import java.util.List;

public interface IReservation {
    void addReservation(Reservation p);
    List<Reservation> getAllReservation();
    void delReservation(int id);
    List<Reservation> searchReservation(String keyWord);
}
