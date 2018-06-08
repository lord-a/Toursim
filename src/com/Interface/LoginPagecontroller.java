package com.userinterface;

import Database.Dataclass;
import Database.Switchscenes;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
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

public class LoginPagecontroller  extends Switchscenes {
    @FXML
    Label lb;
    @FXML
    JFXTextField Username1;
    @FXML
    JFXPasswordField Password1;
    @FXML
    JFXButton reg,log,back;

    public void initialize() {

       log.setDisable(true);
    }

    @FXML
    public void onButtonClicked(ActionEvent e) throws SQLException, IOException {
        if (e.getSource().equals(reg)) {
            super.switchscene("signup.fxml",e);
        }
        else if(e.getSource().equals(back)){
            super.switchscene("firstnew.fxml",e);
        } else if (e.getSource().equals(log)) {
            int key = Dataclass.getInstance().queryregister(Username1.getText(), Password1.getText());
            if (key == 1) { FXMLLoader loader = new FXMLLoader(getClass().getResource("inside.fxml"));
                Parent inparent = loader.load();
                Scene inscene = new Scene(inparent);
                insidecontroller inda = loader.getController();
                inda.initializess(Username1.getText());
                Stage upstage = (Stage) ((Node) e.getSource()).getScene().getWindow();
                upstage.setScene(inscene);
                upstage.show();
            }else if(key==0){
               lb.setText("Invalid Username Or Password");
               Username1.clear();
               Password1.clear();
            }
        }
    }
    @FXML
    public void handlekeyreleased() {
        String st1=Username1.getText();
        String st2=Password1.getText();
        boolean disablebuttons = st1.isEmpty()|| st2.isEmpty();
        log.setDisable(disablebuttons);


    }
}
