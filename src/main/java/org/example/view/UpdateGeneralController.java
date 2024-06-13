package org.example.view;


import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import org.example.model.dao.GeneralDAO;
import org.example.model.entity.Company;
import org.example.model.entity.General;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class UpdateGeneralController extends Controller implements Initializable {

    @FXML
    private TextField name;

    @FXML
    private TextField id;

    @FXML
    private ComboBox<General> combo_ids;


    private ObservableList<General> generals;

    private Tabla_Editable_General_Controller controller;

    @Override
    public void onOpen(Object input) throws IOException {
        this.controller = (Tabla_Editable_General_Controller) input;
    }

    @Override
    public void onClose(Object output) {

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        List<General> generals = GeneralDAO.build().findAll();
        System.out.println(generals);
        this.generals = FXCollections.observableArrayList(generals);
        this.combo_ids.setItems(this.generals);

    }

    @FXML
    private void closeWindow(Event event){
        General general =new General();
        general.setId(combo_ids.getValue().getId());
        general.setName(name.getText());
        this.controller.saveAuthor(general);
        ((Node)(event.getSource())).getScene().getWindow().hide();
    }
}
