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

public class Tabla_Editable_Companias_Controller extends Controller implements Initializable {
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
    public void onOpen(Object input) {
            //Al inicio
        List<Company> companies = CompanyDAO.build().findAll();
        System.out.println(companies);
        this.companies = FXCollections.observableArrayList(companies);
        tableView.setItems(this.companies);
    }

    @Override
    public void onClose(Object output) {

    }

    public void saveCompania(Company newCompany){
        CompanyDAO.build().save(newCompany);
        this.companies.add(newCompany);


    }
    public void deleteCompania(Company auxCompania) throws SQLException {
        CompanyDAO.build().delete(auxCompania);

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        tableView.setEditable(false);
        columnID.setCellValueFactory(company -> new SimpleStringProperty(company.getValue().getId()));
        columnName.setCellValueFactory(company-> new SimpleStringProperty(company.getValue().getName()));
        columnID_General.setCellValueFactory(company-> new SimpleStringProperty(company.getValue().getGeneral().getId()));

        /*columnName.setCellFactory(TextFieldTableCell.forTableColumn());
       columnName.setOnEditCommit(event -> {
            if(event.getNewValue()== event.getOldValue()){
                return;
            }

            if(event.getNewValue().length()<=60){
                General general = event.getRowValue();
                general.setName(event.getNewValue());
                GeneralDAO.build().save(general);
            }else{
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Te has pasao!!!!!");
                alert.show();
            }
            //Actualizar los datos



        });*/

    }
    @FXML
    private void closeApp(){
        System.exit(0);
    }

    @FXML
    private void refresh() throws IOException {

        App.currentController.changeScene(Scenes.TABLAEDITABLECOMPANIA,null);
    }

    @FXML
    private void goToIntermedio() throws IOException {

        App.currentController.changeScene(Scenes.INTERMEDIO,null);
    }
    @FXML
    private void agregarCompania() throws IOException {
        App.currentController.openModal(Scenes.ADDCOMPANIA,"Agregando una Compania...",this,null);
        refresh();
    }
    @FXML
    private void borrarCompania() throws IOException {
        App.currentController.openModal(Scenes.DELETECOMPANIA,"Borrar una Compania...",this,null);
        refresh();
    }
    @FXML
    private void modificarCompania() throws IOException {
        App.currentController.openModal(Scenes.UPDATECOMPANIA,"Borrar una Compania...",this,null);
        refresh();
    }
    @FXML
    private void abrirTablaEditableGeneral() throws IOException {
        App.currentController.changeScene(Scenes.TABLAEDITABLEGENERAL,null);

    }
    @FXML
    private void abrirTablaEditableTropas() throws IOException {
        App.currentController.changeScene(Scenes.TABLAEDITABLETROPAS,null);

    }
}
