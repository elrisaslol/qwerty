package org.example.view;


import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import org.example.model.entity.Company;
import org.example.model.entity.General;
import org.example.model.entity.Unit;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AddTropaController extends Controller implements Initializable {

    @FXML
    private TextField name;

    @FXML
    private TextField id_Compania;

    private Tabla_Editable_Tropas_Controller controller;

    @Override
    public void onOpen(Object input) throws IOException {
        this.controller = (Tabla_Editable_Tropas_Controller) input;
    }

    @Override
    public void onClose(Object output) {

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @FXML
    private void closeWindow(Event event){
        Company company =new Company();
        company.setId(id_Compania.getText());
        Unit unit = new Unit(name.getText(),company);
        this.controller.saveTropa(unit);
        ((Node)(event.getSource())).getScene().getWindow().hide();
    }
}
