package com.userinterface;

import Database.Dataclass;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        FXMLLoader loader =new FXMLLoader(getClass().getResource("firstnew.fxml"));
        Parent root = loader.load();
//   insidecontroller contt=loader.load();
        primaryStage.setTitle("GetInTouch(A Traveler's Companion)");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
  //      contt.listname();
    }
    @Override
    public void init() throws Exception {
        super.init();
        if(!Dataclass.getInstance().open()){
            System.out.println("Couldnt connect to the database");
            Platform.exit();
        }
    }

    @Override
    public void stop() throws Exception {
        super.stop();
        Dataclass.getInstance().close();
    }

    public static void main(String[] args) {
        launch(args);

    }
}
