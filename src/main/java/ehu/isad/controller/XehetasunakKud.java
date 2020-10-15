package ehu.isad.controller;

import ehu.isad.Book;
import ehu.isad.Details;
import ehu.isad.Liburuak;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class XehetasunakKud{
    //References to main application
    private Liburuak mainApp;

    @FXML
    private Label lblIzenburuHandia;

    @FXML
    private Label lblIzenburua;

    @FXML
    private Button btnAtzera;

    @FXML
    private Label lblArgitaletxea;

    @FXML
    private Label lblOrriKop;


    public void setMainApp(Liburuak main) {
        this.mainApp = main;
    }


    public void hasieratuDatuak(Book liburua){
        Details details= liburua.getDetails();
        lblIzenburuHandia.setText(details.getTitle()+" Liburuaren fitxa:");
        lblIzenburua.setText(details.getTitle());
        lblArgitaletxea.setText(details.getPublishers());
        lblOrriKop.setText(details.getNumber_of_pages());

    }

    @FXML
    void onAction(ActionEvent event) {
        System.out.println("hola");
        mainApp.liburuakErakutsi();
        System.out.println("hola2");
    }

}
