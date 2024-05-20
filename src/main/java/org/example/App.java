package org.example;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.example.view.AppController;
import org.example.view.uiiicontroller;
import org.example.view.Scenes;
import org.example.view.View;

import java.io.IOException;

import static org.example.view.AppController.loadFXML;

/**
 * JavaFX App
 */
public class App extends Application {

    public static Scene scene;
    public static Stage stage;
    public static AppController currentController;

    //este el es primer método que se ejecuta al abrir la primera ventana
    @Override
    public void start(Stage stage) throws IOException {
        //view/layout.fxml
        View view = loadFXML(Scenes.ROOT);
        scene = new Scene(view.scene, 600.0, 400.0);
        currentController = (AppController) ((View) view).controller;
        currentController.onOpen(null);
        stage.setScene(scene);
        stage.show();
    }

    public static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML2(fxml));
    }

    private static Parent loadFXML2(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }


    public static void main(String[] args) {
        launch();
    }

}