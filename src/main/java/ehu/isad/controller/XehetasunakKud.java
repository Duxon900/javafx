package ehu.isad.controller;

import ehu.isad.Book;
import ehu.isad.Details;
import ehu.isad.Liburuak;

import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
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

    private ImageView imgArgazki=new ImageView();

    @FXML
    private ImageView imageView;


    public void setMainApp(Liburuak main) {
        this.mainApp = main;
    }


    public void hasieratuDatuak(Book liburua) throws IOException {
        Details details= liburua.getDetails();
        lblIzenburuHandia.setText(details.getTitle()+" Liburuaren fitxa:");
        lblIzenburua.setText(details.getTitle());
        lblArgitaletxea.setText(details.getPublishers());
        lblOrriKop.setText(details.getNumber_of_pages());

        System.out.println(liburua.getThumbnail_url());
        //Image argazki=createImage(liburua.getThumbnail_url());
        Image argazki=lortuIrudia("9788483258866.jpg");
        imgArgazki.setImage(argazki);
    }

    @FXML
    void onAction(ActionEvent event) {
        mainApp.liburuakErakutsi();
    }

    private Image createImage(String url) throws IOException{
        URLConnection urlConnection=new URL(url).openConnection();
        urlConnection.setRequestProperty("User-Agent","Mozilla/5.0 (Macintosh; U; Intel Mac OS X 10.4; en-US; rv:1.9.2.2) Gecko/20100316 Firefox/3.6.2");

        try(InputStream stream=urlConnection.getInputStream()){
            return new Image(stream);
        }
    }

    //unean hautatutako elementuaren irudia lortzeko
    private Image lortuIrudia(String location) throws IOException {
        InputStream is = getClass().getResourceAsStream("/" + location);
        BufferedImage reader = ImageIO.read(is);
        return SwingFXUtils.toFXImage(reader, null);
    }

}
