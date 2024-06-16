package org.example.view;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import org.example.App;
import org.example.model.dao.CompanyDAO;
import org.example.model.dao.UnitDAO;
import org.example.model.entity.Company;
import org.example.model.entity.General;
import org.example.model.entity.Unit;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class UpdateTropasController extends Controller implements Initializable {

    @FXML
    private TextField name;

    @FXML
    private TextField id;

    @FXML
    private ComboBox<Unit> combo_ids;


    private ObservableList<Unit> units;

    @FXML
    private ComboBox<Company> combo_NEWids;


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
        List<Unit> units = UnitDAO.build().findAll();
        System.out.println(units);
        this.units = FXCollections.observableArrayList(units);
        this.combo_ids.setItems(this.units);
        List<Company> companies = CompanyDAO.build().findAll();
        System.out.println(companies);
        this.companies = FXCollections.observableArrayList(companies);
        this.combo_NEWids.setItems(this.companies);

    }

    @FXML
    private void closeWindow(Event event) throws IOException {
        Unit unit= new Unit();
        unit.setId(combo_ids.getValue().getId());
        if (name.getText().isBlank()){
            QueMamon();
            unit.setName(combo_ids.getValue().getName());
        }else{
            unit.setName(name.getText());
        }
        Company company =new Company();
        try {
            unit.setCompany(company);
            company.setId(combo_NEWids.getValue().getId());
            company.setName(combo_NEWids.getValue().getName());
        }catch (NullPointerException e){
            QueMamon();
            unit.setCompany(company);
            company.setId(combo_ids.getValue().getCompany().getId());
            company.setName(combo_ids.getValue().getCompany().getName());
        }
        this.controller.saveTropa(unit);
        ((Node)(event.getSource())).getScene().getWindow().hide();
    }

    @FXML
    private void QueMamon() throws IOException {
        App.currentController.openModal(Scenes.FELICDADES,"mamahue",this,null);
    }


}
