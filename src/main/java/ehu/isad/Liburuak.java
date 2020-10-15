package ehu.isad;

import ehu.isad.controller.LiburuKud;
import ehu.isad.controller.XehetasunakKud;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Liburuak extends Application {

  private Parent liburuKudUI;
  private Parent xehetasunakUI;

  private Stage stage;

  private LiburuKud liburuKud;
  private XehetasunakKud xehetasunakKud;


  @Override
  public void start(Stage primaryStage) throws Exception {

    stage = primaryStage;
    pantailakKargatu();

    stage.setTitle("Liburuak");
    stage.setScene(new Scene(liburuKudUI, 700, 600));
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
    launch(args);
  }

  public void mainErakutsi() {
    stage.setScene(new Scene(liburuKudUI));
    stage.show();
  }
}
