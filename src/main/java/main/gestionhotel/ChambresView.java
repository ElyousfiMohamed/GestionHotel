package main.gestionhotel.GestionChambre;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import main.gestionhotel.ClassesPersistants.Chambre;
import main.gestionhotel.IMetier.IMetier;
import main.gestionhotel.IMetier.IMetierImpl;

import java.net.URL;
import java.util.ResourceBundle;

public class ChambresView  implements Initializable {

    @FXML
    private TableView<Chambre> table;

    @FXML
    private TableColumn<Chambre,Integer> numero;

    @FXML
    private TableColumn<Chambre,String> description;

    @FXML
    private TableColumn<Chambre,Integer> type;

    @FXML
    private TableColumn<Chambre,Integer> ID;

    static ObservableList<Chambre> chambres=  FXCollections.observableArrayList();

    IMetierImpl IM=new IMetierImpl();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

       ID.setCellValueFactory(new PropertyValueFactory<>("d_chmbr"));
       numero.setCellValueFactory(new PropertyValueFactory<>("num_chmbr"));
       description.setCellValueFactory(new PropertyValueFactory<>("desq_chmbr"));
       type.setCellValueFactory(new PropertyValueFactory<>("id_type"));
       chambres.addAll(IM.getAllChambreDispo());
       table.setItems(chambres);
    }

    public void addChambre(ActionEvent event){
        try{
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("AddChambre.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 500, 500);
            Stage stage= new Stage();
            stage.setTitle("Ajout");
            stage.setScene(scene);
            stage.show();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    public void modifierChambre(ActionEvent event){
        int indice=table.getSelectionModel().getSelectedIndex();
        Chambre c=table.getSelectionModel().getSelectedItem();
        if (indice >= 0) {
          try{

            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("updateChambre.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 500, 500);
            Stage stage= new Stage();
            stage.setTitle("Modification");
            stage.setScene(scene);
            stage.show();


            } catch (Exception ex) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setContentText(ex.getMessage());
                alert.show();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setContentText("Veuillez sélectionner un élément ");
            alert.show();
        }
    }

    public void supprimerChambre(ActionEvent actionEvent) {
        int indice = table.getSelectionModel().getSelectedIndex();
        if (indice >= 0) {
            try {
                IMetier metier = new IMetierImpl();
                metier.delChambre(table.getItems().get(indice).getId_chmbr());
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            table.getItems().remove(indice);
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setContentText("Veuillez sélectionner un élément ");
            alert.show();
        }
    }
}
