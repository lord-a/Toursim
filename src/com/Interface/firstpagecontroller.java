package com.userinterface;

import Database.Switchscenes;
import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;



import java.io.IOException;

public class firstpagecontroller extends Switchscenes {

    @FXML
    JFXButton trav;
    @FXML
    JFXButton agen;

    @FXML
    public void onclicked(ActionEvent e) throws IOException{
        if(e.getSource().equals(trav)){
            super.switchscene("login.fxml",e);
        }else if(e.getSource().equals(agen)) super.switchscene("agencyform.fxml", e);

}
}
