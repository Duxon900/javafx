package ehu.isad.controller;

import ehu.isad.Book;
import ehu.isad.Details;
import ehu.isad.Liburuak;
import ehu.isad.utils.Sarea;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.image.ImageView;
import javafx.util.StringConverter;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LiburuKud implements Initializable {

  //References to main application
  private Liburuak mainApp;

  @FXML
  private Button kautotuBotoia;

  @FXML
  private ComboBox comboLiburua;



  public void setMainApp(Liburuak main) {
    this.mainApp = main;
  }

  @FXML
  public void onClick(ActionEvent actionEvent) throws IOException {
    if(comboLiburua.getValue()!=null){
      Details unekoa=(Details) comboLiburua.getValue();
      Book nireLiburua= Sarea.readFromUrl(unekoa.getIsbn());

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

}