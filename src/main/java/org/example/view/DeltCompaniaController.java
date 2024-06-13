package org.example.view;


import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import org.example.model.entity.Company;
import org.example.model.entity.General;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class DeltCompaniaController extends Controller implements Initializable {

    @FXML
    private TextField id;

    private Tabla_Editable_Companias_Controller controller;

    @Override
    public void onOpen(Object input) throws IOException {
        this.controller = (Tabla_Editable_Companias_Controller) input;
    }

    @Override
    public void onClose(Object output) {

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @FXML
    private void closeWindow(Event event) throws SQLException {
        Company company = new Company(null,null);
        company.setId(id.getText());
        this.controller.deleteCompania(company);
        ((Node)(event.getSource())).getScene().getWindow().hide();
    }
}
