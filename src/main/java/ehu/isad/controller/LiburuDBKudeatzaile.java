package ehu.isad.controller;

import ehu.isad.Book;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.ResultSet;

public class LiburuDBKudeatzaile {

    public void gordeDatuBasean(Book liburua, String argazkiUrl){
        //INSERT INTO `openLibrary`.`Liburuak` (`isbn`, `publishers`, `title`, `number_of_pages`, `info_url`, `bib_key`, `preview_url`, `thumbnail_url`, `preview`) VALUES ('1', '2', '3', '3', '4', '1', '34', '1', '4');

        String query="insert into `Liburuak` (`isbn`, `publishers`, `title`, `number_of_pages`, `info_url`, `bib_key`, `preview_url`, `thumbnail_url`, `preview`) values("+lortuLiburuBalioak(liburua,argazkiUrl)+");";

        DBKudeatzaile dbKudeatzaile=DBKudeatzaile.getInstantzia();

        dbKudeatzaile.execSQL(query);
    }

    public String lortuLiburuBalioak(Book liburua, String argazkiUrl){
        String emaitza="";

        //ISBN
        emaitza=emaitza+"'"+liburua.getDetails().getIsbn()+"',";
        //publisher
        String publishers=liburua.getDetails().getPublishers();
        publishers=publishers.replace('\'',' ');
        emaitza=emaitza+"'"+publishers+"',";
        //title && number of pages
        emaitza=emaitza+"'"+liburua.getDetails().getTitle()+"',";
        emaitza=emaitza+liburua.getDetails().getNumber_of_pages()+",";
        //url varias
        emaitza=emaitza+"'"+liburua.getInfo_url()+"',";
        emaitza=emaitza+"'"+liburua.getBib_key()+"',";
        emaitza=emaitza+"'"+liburua.getPreview_url()+"',";
        //Argazkia
        emaitza=emaitza+"'"+argazkiUrl+"',";
        emaitza=emaitza+"'"+liburua.getPreview()+"'";


        return emaitza;
    }

    public String saveToFile(Image image,String isbn) {
        File outputFile = new File("ArgazkiBU/"+isbn+".jpg");
        BufferedImage bImage = SwingFXUtils.fromFXImage(image, null);

        try {
            ImageIO.write(bImage, "jpg", outputFile);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return outputFile.getAbsolutePath();
    }


    public ResultSet kargatutaDago(String isbn){
        DBKudeatzaile dbKudeatzaile=DBKudeatzaile.getInstantzia();
        String query="select * from Liburuak where isbn='"+isbn+"'";

        try{
            return dbKudeatzaile.execSQL(query);
        }catch (NullPointerException e){
            return null;
        }
    }

}
