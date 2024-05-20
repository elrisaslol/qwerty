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
import org.example.model.entity.Company;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class Tabla_Editable_Companias_Controller extends Controller implements Initializable {
   @FXML
   private TableView<Company> tableView;

   @FXML
   private TableColumn<Company,String> columnDNI;
   @FXML
   private TableColumn<Company,String> columnName;

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

    public void saveAuthor(Company newCompany){
        CompanyDAO.build().save(newCompany);
        this.companies.add(newCompany);

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        tableView.setEditable(false);
        columnDNI.setCellValueFactory(company -> new SimpleStringProperty(company.getValue().getId()));
        columnName.setCellValueFactory(company-> new SimpleStringProperty(company.getValue().getName()));
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
    private void agregarGeneral() throws IOException {
        App.currentController.openModal(Scenes.NEWAUTHOR,"Agregando un autor...",this,null);
    }
    @FXML
    private void borrarGeneral() throws IOException {
    }
    @FXML
    private void modificarGeneral() throws IOException {
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
