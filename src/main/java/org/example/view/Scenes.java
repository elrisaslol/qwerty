package org.example.view;

public enum Scenes {
    ROOT("view/layout.fxml"),
    MAINMENU("view/mainMenu.fxml"),
    CREDITOS("view/creditos.fxml"),
    INTERMEDIO("view/intermedio.fxml"),
    TABLAEDITABLEGENERAL("view/tabla_editableGeneral.fxml"),
    TABLANOEDITABLEGENERAL("view/tabla_no_editableGeneral.fxml"),
    TABLAEDITABLECOMPANIA("view/tabla_editableCompania.fxml"),
    TABLANOEDITABLECOMPANIA("view/tabla_no_editableCompania.fxml"),
    TABLAEDITABLETROPAS("view/tabla_editableTropas.fxml"),
    TABLANOEDITABLETROPAS("view/tabla_no_editableTropas.fxml"),
    ADDGENERAL("view/addGeneral.fxml"),
    DELETEGENERAL("view/deltGeneral.fxml"),
    UPDATEGENERAL("view/updtGeneral.fxml"),
    ADDCOMPANIA("view/addCompania.fxml"),
    DELETECOMPANIA("view/deltCompania.fxml"),
    UPDATECOMPANIA ("view/updtCompania.fxml"),
    ADDTROPA("view/addTropa.fxml"),
    DELETETROPA("view/deltTropa.fxml");

    private String url;
    Scenes(String url){
        this.url=url;
    }
    public String getURL(){
        return url;
    }

}
