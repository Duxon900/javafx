package ehu.isad.controller;

import ehu.isad.Book;
import ehu.isad.Details;
import ehu.isad.Liburuak;
import ehu.isad.utils.Sarea;
import ehu.isad.utils.Utils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.image.Image;
import javafx.util.StringConverter;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;
import java.util.ResourceBundle;

public class LiburuKud implements Initializable {

  //References to main application
  private Liburuak mainApp;

  @FXML
  private Button kautotuBotoia;

  @FXML
  private ComboBox<Details> comboLiburua;



  public void setMainApp(Liburuak main) {
    this.mainApp = main;
  }

  @FXML
  public void onClick(ActionEvent actionEvent) throws IOException, SQLException {
    if(comboLiburua.getValue()!=null){
      Details unekoa= comboLiburua.getValue();
      Book nireLiburua;

      LiburuDBKudeatzaile liburuDBKudeatzaile=new LiburuDBKudeatzaile();
      ResultSet resultSet=liburuDBKudeatzaile.kargatutaDago(unekoa.getIsbn()+".jpg");

      if(!resultSet.first()){
        nireLiburua= Sarea.readFromUrl(unekoa.getIsbn());
        nireLiburua.getDetails().setIsbn(unekoa.getIsbn());
        String url=mainApp.getXehetasunakKud().irudiaHanditu(nireLiburua.getThumbnail_url());
        nireLiburua.setThumbnail_url(url);

        //irudia kargatu
        mainApp.getXehetasunakKud().hasieratuImage(nireLiburua);

        //Datuak db-an gordetzeko
        saveData(nireLiburua);
      }
      else{
        nireLiburua=kargatuLiburua(resultSet);
        //Irudia kargatu
        mainApp.getXehetasunakKud().hasieratuImageDB(nireLiburua);
      }
      mainApp.getXehetasunakKud().hasieratuDatuak(nireLiburua);
      mainApp.xehetasunakErakutsi();
    }
  }

  @Override
  public void initialize(URL location, ResourceBundle resources) {
    comboLiburua.getItems().addAll(
            new Details("Blockchain: Blueprint for a New Economy","9781491920497"),
            new Details("R for Data Science","1491910399"),
            new Details("Fluent Python","1491946008"),
            new Details("Natural Language Processing with PyTorch","1491978236"),
            new Details("Data Algorithms","9781491906187")
    );

    jarriIzena();

    Utils.lortuEzarpenak();
  }

  public void jarriIzena(){
    comboLiburua.setConverter(new StringConverter<Details>() {
      @Override
      public String toString(Details object) {
        if (object==null) return null;
        else return object.getTitle();
      }

      @Override
      public Details fromString(String string) {
        return null;
      }
    });
  }

  public Book kargatuLiburua(ResultSet resultSet) throws SQLException {
    //Liburua hasieratu
    Book emaitza=new Book();
    Properties properties= Utils.lortuEzarpenak();
    String path=properties.getProperty("imagePath");

//    emaitza.setBib_key(resultSet.getString("bib_key"));
    emaitza.setInfo_url(resultSet.getString("info_url"));
    emaitza.setPreview_url(resultSet.getString("preview_url"));
    emaitza.setPreview(resultSet.getString("preview"));
    //thumbnail
    emaitza.setThumbnail_url(path+resultSet.getString("thumbnail_url"));

    //Detaileak hasieratu
    Details details=new Details(resultSet.getString("title"),resultSet.getString("isbn"));

    details.setNumber_of_pages(resultSet.getInt("number_of_pages"));

    String izenak=resultSet.getString("publishers");
    izenak=izenak.replace("[","");
    izenak=izenak.replace("]","");
    String[] lista=izenak.split(",");
    details.setPublishers(lista);

    //emaitzan sartu details eta return
    emaitza.setDetails(details);
    return emaitza;
  }

  private void saveData(Book liburua) throws IOException {
    Image argazki=mainApp.getXehetasunakKud().createImage(liburua.getThumbnail_url());

    LiburuDBKudeatzaile liburuDBKudeatzaile=new LiburuDBKudeatzaile();
    liburuDBKudeatzaile.saveToFile(argazki, liburua.getDetails().getIsbn());
    liburuDBKudeatzaile.updateDatuBasea(liburua);
  }

}