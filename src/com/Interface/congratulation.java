package com.userinterface;

import javafx.fxml.FXML;
import javafx.scene.control.Label;


public class congratulation {

    @FXML
    Label la;

    @FXML
    public void showlabel(String key){
        la.setText(key);
    }
}
