package org.example.view;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import org.example.model.dao.GeneralDAO;
import org.example.model.dao.UnitDAO;
import org.example.model.entity.General;
import org.example.model.entity.Unit;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class SearchGeneralController extends Controller implements Initializable {

    @FXML
    private TextField name;

    @FXML
    private ComboBox<General> combo_ids;


    private ObservableList<General> generals;

    private tabla_no_editable_General_Controller controller;

    @Override
    public void onOpen(Object input) throws IOException {
        this.controller = (tabla_no_editable_General_Controller) input;
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
        ((Node)(event.getSource())).getScene().getWindow().hide();
    }
}
