package org.example.view;


import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import org.example.model.entity.General;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class SearchGeneralController extends Controller implements Initializable {

    @FXML
    private TextField name;

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

    }

    @FXML
    private void closeWindow(Event event){
        General general = new General(name.getText());
        this.controller.saveAuthor(general);
        ((Node)(event.getSource())).getScene().getWindow().hide();
    }
}
