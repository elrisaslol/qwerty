package org.example.view;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import org.example.App;
import org.example.model.dao.GeneralDAO;
import org.example.model.entity.General;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class Tabla_Editable_Tropas_Controller extends Controller implements Initializable {
   @FXML
   private TableView<General> tableView;

   @FXML
   private TableColumn<General,String> columnDNI;
   @FXML
   private TableColumn<General,String> columnName;

   private ObservableList<General> generals;

    @Override
    public void onOpen(Object input) {
            //Al inicio
        List<General> generals = GeneralDAO.build().findAll();
        System.out.println(generals);
        this.generals = FXCollections.observableArrayList(generals);
        tableView.setItems(this.generals);
    }

    @Override
    public void onClose(Object output) {

    }

    public void saveAuthor(General newGeneral){
        GeneralDAO.build().save(newGeneral);
        this.generals.add(newGeneral);

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        tableView.setEditable(false);
        columnDNI.setCellValueFactory(author-> new SimpleStringProperty(author.getValue().getId()));
        columnName.setCellValueFactory(author-> new SimpleStringProperty(author.getValue().getName()));
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

        App.currentController.changeScene(Scenes.TABLAEDITABLETROPAS,null);
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
    private void abrirTablaEditableCompanias() throws IOException {
        App.currentController.changeScene(Scenes.TABLAEDITABLECOMPANIA,null);

    }
    @FXML
    private void abrirTablaEditableGeneral() throws IOException {
        App.currentController.changeScene(Scenes.TABLAEDITABLEGENERAL,null);

    }
}
