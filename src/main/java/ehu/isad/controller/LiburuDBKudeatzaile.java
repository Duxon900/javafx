package ehu.isad.controller;

import ehu.isad.Book;
import ehu.isad.Details;
import ehu.isad.utils.Utils;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

public class LiburuDBKudeatzaile {

    public void updateDatuBasea(Book liburua){
        //(`publishers`, `number_of_pages`, `info_url`, `bib_key`, `preview_url`, `thumbnail_url`, `preview`)
        String publishers=liburua.getDetails().getPublishers();
        publishers=publishers.replace('\'',' ');

        String query="update `Liburuak` set publishers='"+
                publishers+"', number_of_pages='"+
                liburua.getDetails().getNumber_of_pages()+"', info_url='"+
                liburua.getInfo_url()+"', bib_key='"+
                liburua.getBib_key()+"', preview_url='"+
                liburua.getPreview_url()+"', thumbnail_url='"+
                liburua.getDetails().getIsbn()+".jpg', preview='"+
                liburua.getPreview()+"' where isbn='"+ liburua.getDetails().getIsbn()+"'";

        DBKudeatzaile dbKudeatzaile=DBKudeatzaile.getInstantzia();
        dbKudeatzaile.execSQL(query);
    }

    public void gordeDatuak(Details details){
        String query="insert into `Liburuak` (`isbn`, `title`) values('"+details.getIsbn()+"','"+details.getTitle()+"')";

        DBKudeatzaile dbKudeatzaile=DBKudeatzaile.getInstantzia();
        dbKudeatzaile.execSQL(query);
    }

    public String saveToFile(Image image,String isbn) {
        Properties properties= Utils.lortuEzarpenak();
        String path=properties.getProperty("imagePath");

        File outputFile = new File(path+isbn+".jpg");
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
        String query="select * from Liburuak where thumbnail_url='"+isbn+"'";
        try{
            return dbKudeatzaile.execSQL(query);
        }catch (NullPointerException e){
            return null;
        }
    }

    public boolean ezabatuDatuak(Details details){
        DBKudeatzaile dbKudeatzaile=DBKudeatzaile.getInstantzia();
        String query="delete from Liburuak where isbn='"+details.getIsbn()+"'";
        dbKudeatzaile.execSQL(query);
        return true;

    }

}
