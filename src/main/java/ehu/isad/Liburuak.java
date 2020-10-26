package ehu.isad;

import ehu.isad.controller.DBKudeatzaile;
import ehu.isad.controller.LiburuDBKudeatzaile;
import ehu.isad.controller.LiburuKud;
import ehu.isad.controller.XehetasunakKud;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Arrays;

public class Liburuak extends Application {

  private Parent liburuKudUI;
  private Parent xehetasunakUI;

  private Stage stage;

  private Scene liburuKudScene;
  private Scene xehetasunakKudScene;

  private LiburuKud liburuKud;
  private XehetasunakKud xehetasunakKud;


  @Override
  public void start(Stage primaryStage) throws Exception {

    stage = primaryStage;
    pantailakKargatu();

    stage.setTitle("Liburuak");
    liburuKudScene=new Scene(liburuKudUI,700,600);
    xehetasunakKudScene=new Scene(xehetasunakUI,700,600);
    stage.setScene(liburuKudScene);
    stage.show();
  }

  private void pantailakKargatu() throws IOException {

    FXMLLoader loaderLiburuak = new FXMLLoader(getClass().getResource("/Liburuak.fxml"));
    liburuKudUI = (Parent) loaderLiburuak.load();
    liburuKud = loaderLiburuak.getController();
    liburuKud.setMainApp(this);

    FXMLLoader loaderXehetasunak = new FXMLLoader(getClass().getResource("/Xehetasunak.fxml"));
    xehetasunakUI = (Parent) loaderXehetasunak.load();
    xehetasunakKud = loaderXehetasunak.getController();
    xehetasunakKud.setMainApp(this);
  }


  public static void main(String[] args) {
    sartuDatuakDB();
    launch(args);
    ezabatuDatuak();
  }

  public void xehetasunakErakutsi() {
    stage.setScene(xehetasunakKudScene);
    stage.show();
  }

  public void liburuakErakutsi() {
    stage.setScene(liburuKudScene);
    stage.show();
  }

  private static void sartuDatuakDB(){
    var lista=Arrays.asList(
            new Details("Blockchain: Blueprint for a New Economy","9781491920497"),
            new Details("R for Data Science","1491910399"),
            new Details("Fluent Python","1491946008"),
            new Details("Natural Language Processing with PyTorch","1491978236"),
            new Details("Data Algorithms","9781491906187")
    );

    LiburuDBKudeatzaile liburuDBKudeatzaile=new LiburuDBKudeatzaile();

    for(int i=0;i<lista.size();i++){
      liburuDBKudeatzaile.gordeDatuak(lista.get(i));
    }
  }


  private static void ezabatuDatuak(){
    var lista=Arrays.asList(
            new Details("Blockchain: Blueprint for a New Economy","9781491920497"),
            new Details("R for Data Science","1491910399"),
            new Details("Fluent Python","1491946008"),
            new Details("Natural Language Processing with PyTorch","1491978236"),
            new Details("Data Algorithms","9781491906187")
    );

    LiburuDBKudeatzaile liburuDBKudeatzaile=new LiburuDBKudeatzaile();

    for(int i=0;i<lista.size();i++){

      liburuDBKudeatzaile.ezabatuDatuak(lista.get(i));
    }
  }


  public XehetasunakKud getXehetasunakKud(){
    return xehetasunakKud;
  }
}
