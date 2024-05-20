package org.example.view;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import org.example.App;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class IntermedioController extends Controller implements Initializable {

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
    private void goToMain() throws IOException {
        App.currentController.changeScene(Scenes.TABLAEDITABLEGENERAL,null);
    }
    @FXML
    private void goToTablaNoEditable1() throws IOException {
        App.currentController.changeScene(Scenes.TABLANOEDITABLEGENERAL,null);
    }
    @FXML
    private void goToMainMenu() throws IOException {

        App.currentController.changeScene(Scenes.MAINMENU,null);
    }

}
