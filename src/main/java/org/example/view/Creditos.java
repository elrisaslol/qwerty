package org.example.view;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import org.example.App;
import org.example.view.Controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Creditos extends Controller implements Initializable {
    @Override
    public void onOpen(Object input) throws IOException {

    }

    @Override
    public void onClose(Object output) {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
    @FXML
    private void goToMainMenu() throws IOException {
        App.currentController.changeScene(Scenes.MAINMENU,null);
    }
}
