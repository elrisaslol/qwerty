package org.example.view;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import org.example.App;
import org.example.model.dao.CompanyDAO;
import org.example.model.dao.GeneralDAO;
import org.example.model.entity.Company;
import org.example.model.entity.General;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

public class tabla_no_editable_General_Controller extends Controller implements Initializable {
   @FXML
   private TableView<Company> tableView;

   @FXML
   private TableColumn<Company,String> columnID;
   @FXML
   private TableColumn<Company,String> columnName;
   @FXML
   private TableColumn<Company,String> columnID_General;


   private ObservableList<Company> companies;

    @Override
    public void onOpen(Object input) throws IOException {
            //Al inicio

    }

    @Override
    public void onClose(Object output) {

    }
    public void searchGeneral(String key) throws SQLException {
        List<Company> auxCopanias;
        GeneralDAO.build().Truncate();
        auxCopanias= GeneralDAO.build().findCompanybyFK(key);
        this.companies = FXCollections.observableArrayList(auxCopanias);
        tableView.setItems(this.companies);

    }

  /*  public void saveQuerrry(General newGeneral){
        GeneralDAO.build().saveQuerry(newGeneral);
        this.generals.add(newGeneral);

    }*/

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        tableView.setEditable(false);
        columnID.setCellValueFactory(company -> new SimpleStringProperty(company.getValue().getId()));
        columnName.setCellValueFactory(company-> new SimpleStringProperty(company.getValue().getName()));
        columnID_General.setCellValueFactory(company-> new SimpleStringProperty(company.getValue().getGeneral().getId()));

    }
    @FXML
    private void closeApp(){
        System.exit(0);
    }

    @FXML
    private void refresh() throws IOException {

        App.currentController.changeScene(Scenes.TABLANOEDITABLEGENERAL,null);
    }
    @FXML
    private void goToIntermedio() throws IOException {

        App.currentController.changeScene(Scenes.INTERMEDIO,null);
    }

    @FXML
    public void goToTablaNoEditableCompania() throws IOException {
        App.currentController.changeScene(Scenes.TABLANOEDITABLECOMPANIA,null);

    }
    @FXML
    public void goToTablaNoEditableTropas() throws IOException {
        App.currentController.changeScene(Scenes.TABLANOEDITABLETROPAS,null);

    }
    @FXML
    private void busquedaGeneral() throws IOException {
        App.currentController.openModal(Scenes.SEARCHGENERAL,"Eliminar un general...",this,null);

    }
}
