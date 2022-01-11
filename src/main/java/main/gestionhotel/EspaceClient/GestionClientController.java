package main.gestionhotel.EspaceClient;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;

public class GestionClientController {

  @FXML private TableView<?> tableView;

  @FXML private TableColumn<?, ?> id;

  @FXML private TableColumn<?, ?> cin;

  @FXML private TableColumn<?, ?> nom;

  @FXML private TableColumn<?, ?> prenom;

  @FXML private TableColumn<?, ?> telephone;

  @FXML private TableColumn<?, ?> email;

  @FXML private TableColumn<?, ?> ADRESSE;

  @FXML private TextField rechercher;

  @FXML
  void modifier(ActionEvent event) {}

  @FXML
  void nouveau(ActionEvent event) {}

  @FXML
  void rechercher(KeyEvent event) {}

  @FXML
  void supprimer(ActionEvent event) {}
}
