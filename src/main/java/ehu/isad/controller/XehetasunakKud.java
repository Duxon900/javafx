package ehu.isad.controller;

import ehu.isad.Book;
import ehu.isad.Details;
import ehu.isad.Liburuak;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

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

        gordeDatuBasean(liburua);


        String argazkiUrl=liburua.getThumbnail_url();
        argazkiUrl=irudiaHanditu(argazkiUrl);
        Image argazki=createImage(argazkiUrl);
        imageView.setImage(argazki);



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

    public String irudiaHanditu(String Url){
        String[] array=Url.split("-S.");

        String emaitza=array[0];
        emaitza=emaitza+"-M.";
        emaitza=emaitza+array[1];
        return emaitza;
    }


    public void gordeDatuBasean(Book liburua){
        String query="insert into Liburuak values("+lortuLiburuBalioak(liburua)+");";

        DBKudeatzaile dbKudeatzaile=DBKudeatzaile.getInstantzia();
        dbKudeatzaile.execSQL(query);
    }


    public String lortuLiburuBalioak(Book liburua){
        String emaitza="";

        System.out.println(liburua.getDetails().getIsbn());

        emaitza=emaitza+"isbn='"+liburua.getDetails().getIsbn()+"',";

        String publishers=liburua.getDetails().getPublishers();

        publishers=publishers.replace('\'',' ');
        emaitza=emaitza+"publishers='"+publishers+"',";
        emaitza=emaitza+"title='"+liburua.getDetails().getTitle()+"',";
        emaitza=emaitza+"number_of_pages='"+liburua.getDetails().getNumber_of_pages()+"',";

        emaitza=emaitza+"info_url='"+liburua.getInfo_url()+"',";
        emaitza=emaitza+"bib_key='"+liburua.getBib_key()+"',";
        emaitza=emaitza+"preview_url='"+liburua.getBib_key()+"',";
        emaitza=emaitza+"thumbnail_url='"+liburua.getThumbnail_url()+"',";
        emaitza=emaitza+"preview='"+liburua.getPreview()+"'";

        return emaitza;
    }

}
