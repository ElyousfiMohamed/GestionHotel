package main.gestionhotel.IClient;

import main.gestionhotel.ClassesPersistants.Client;

import java.util.List;

public interface IClient {
    void addClient(Client p);
    void updateClient(Client p);
    List<Client> getAllClients();
    void delClient(int id);
    List<Client> searchClt(String keyWord);
}
