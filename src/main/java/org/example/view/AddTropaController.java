package org.example.view;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import org.example.model.dao.CompanyDAO;
import org.example.model.entity.Company;
import org.example.model.entity.General;
import org.example.model.entity.Unit;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class AddTropaController extends Controller implements Initializable {

    @FXML
    private TextField name;

    @FXML
    private TextField id_Compania;

    @FXML
    private ComboBox<Company> combo_ids;


    private ObservableList<Company> companies;

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
        List<Company> companies = CompanyDAO.build().findAll();
        System.out.println(companies);
        this.companies = FXCollections.observableArrayList(companies);
        this.combo_ids.setItems(this.companies);
    }

    @FXML
    private void closeWindow(Event event){
        Company company =new Company();
        company.setId(combo_ids.getValue().getId());
        Unit unit = new Unit(name.getText(),company);
        this.controller.saveTropa(unit);
        ((Node)(event.getSource())).getScene().getWindow().hide();
    }
}
