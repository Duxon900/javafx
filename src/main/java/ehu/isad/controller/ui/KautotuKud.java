package ehu.isad.controller.ui;

import ehu.isad.Main;
import ehu.isad.controller.db.ZerbitzuKud;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class KautotuKud implements Initializable {

    // Reference to the main application.
    private Main mainApp;

    @FXML
    private ComboBox comboZerbitzua;

    @FXML
    private TextField txtErabiltzaile;

    @FXML
    private TextField txtPasahitza;

    public void setMainApp(Main main) {
        this.mainApp = main;
    }

    @FXML
    public void onClick(ActionEvent actionEvent) {
        System.out.println(txtErabiltzaile.getText() + ":" + txtPasahitza.getText());
        System.out.println(comboZerbitzua.getValue());

        String izena= (String)comboZerbitzua.getValue();
        List<String> lista= ZerbitzuKud.getInstance().lortuZerbitzuak();


        if (!lista.contains(izena)){
            comboZerbitzua.getItems().add(izena);
            ZerbitzuKud.getInstance().sartuElem(izena);
        }
        else {
            System.out.println("good job bratan!");
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        List<String> herrialdeakList = ZerbitzuKud.getInstance().lortuZerbitzuak();
        ObservableList<String> herrialdeak = FXCollections.observableArrayList(herrialdeakList);

        comboZerbitzua.setItems( herrialdeak );

    }

    @FXML
    void ezabatuElementua(ActionEvent event) {
        String izena= (String)comboZerbitzua.getValue();
        comboZerbitzua.getItems().remove(izena);
        ZerbitzuKud.getInstance().dropRow(izena);
    }

}
