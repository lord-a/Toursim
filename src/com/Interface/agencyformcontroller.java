package com.userinterface;

import Database.Dataclass;
import Database.Switchscenes;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;


import java.io.IOException;
import java.sql.SQLException;
import java.util.Random;


public class agencyformcontroller extends  Switchscenes {
      Random ran=new Random();
      @FXML
      Label err;
     @FXML
     JFXButton cancel1,submit,update;
     @FXML
     JFXTextField name1;
     @FXML
     JFXTextField place1;
     @FXML
     JFXTextField phone1;
     @FXML
     JFXTextField email1;
     @FXML
     JFXComboBox<String> type1= new JFXComboBox<>();


    public void initialize(){
        type1.getItems().addAll("Restaurants and Cafe ","Lodges ", "Hotels", "Transportation", "Agencies");
        submit.setDisable(true);
    }
    @FXML
    public void onbuttonclicked(ActionEvent e) throws IOException ,NumberFormatException{
        if(e.getSource().equals(cancel1)){
            //need to do work
            super.switchscene("firstnew.fxml",e);
        }else if(e.getSource().equals(update)) {
            super.switchscene("update1.fxml",e);

        }
        else if (e.getSource().equals(submit)) try {
            int km = ran.nextInt(3000);
            long pho = Integer.parseInt(phone1.getText());
            String t = type1.getSelectionModel().getSelectedItem();
            int key = Dataclass.getInstance().insertintoagency(name1.getText(), place1.getText(), t, pho, email1.getText()
                    , km);
            if (key == 1) {
                FXMLLoader load = new FXMLLoader(getClass().getResource("congratulation.fxml"));
                Parent congraparent = load.load();
                Scene congrascene = new Scene(congraparent);
                congratulation cog = load.getController();
                cog.showlabel(Integer.toString(km));
                Stage congrastage = (Stage) ((Node) e.getSource()).getScene().getWindow();
                congrastage.setScene(congrascene);
                congrastage.show();
            } else if (key == 0) {
                err.setText("Already Registered Organization");
            }
        } catch (SQLException e1) {
            System.out.println("Couldnt insert into value" + e1.getMessage());
        }
        }
    @FXML
    public void handlekeyrelease() {
        String st1=name1.getText();
        String st2=type1.getSelectionModel().getSelectedItem();
        String st3=place1.getText();
        String st4=phone1.getText();
        boolean disablebuttons = st1.isEmpty()|| st2.isEmpty()||st3.isEmpty()||st4.isEmpty();
        submit.setDisable(disablebuttons);
    }
    }

