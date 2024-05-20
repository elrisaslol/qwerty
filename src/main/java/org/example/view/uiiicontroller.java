package org.example.view;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import org.example.App;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class uiiicontroller extends Controller implements Initializable {





    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    @Override
    public void onOpen(Object input) throws IOException {

    }

    @Override
    public void onClose(Object output) {

    }

    @FXML
    private void goToIntermedio() throws IOException {
        App.currentController.changeScene(Scenes.INTERMEDIO,null);
    }
    @FXML
    private void goToCreditos() throws IOException {
        App.currentController.changeScene(Scenes.CREDITOS,null);
    }

}
