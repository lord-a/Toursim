package com.userinterface;

import Database.Dataclass;
import Database.Switchscenes;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;


import java.io.IOException;
import java.sql.SQLException;


public class signupcontroller extends Switchscenes {
    @FXML
    Label wrong;
    @FXML
    JFXTextField username1,mail;
    @FXML
    JFXPasswordField pass1;
    @FXML
    JFXPasswordField pass2;
    @FXML
    JFXButton back1, next1;

    public void initialize(){
        next1.setDisable(true);
    }
    @FXML
    public void buttonclicked(ActionEvent e) throws IOException {
        if (e.getSource().equals(back1)) {

            super.switchscene("login.fxml", e);
        } else if (e.getSource().equals(next1)) {
            if (pass2.getText().equals(pass1.getText())) {
                try {
                    int key = Dataclass.getInstance().insertinregister(username1.getText(), pass1.getText());
                    //by dataclass obj
                    if (key == 1) {
                        super.switchscene("inside.fxml", e);
                    } else if (key == 0) {
                        wrong.setText("Used Username");
                        username1.clear();
                    }
                } catch (SQLException e1) {
                    System.out.println("Error in signupcontroller" + e1.getMessage());
                }
            }else{
                wrong.setText("Passwords are mismatch");
                pass2.clear();
            }
        }
    }
    @FXML
    public void handlekeyrelease() {
        String st1=username1.getText();
        String st2=pass1.getText();
        String st3=pass2.getText();

        boolean disablebuttons = st1.isEmpty()|| st2.isEmpty()||st3.isEmpty();
        next1.setDisable(disablebuttons);

    }
}
