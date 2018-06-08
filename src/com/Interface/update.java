package com.userinterface;

import Database.Agency;
import Database.Dataclass;
import Database.Switchscenes;
import com.jfoenix.controls.JFXButton;
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


public class update extends Switchscenes{
            Agency l =null;
            @FXML
            Label error;
    @FXML
    JFXButton next,back;
    @FXML
    JFXTextField keyn;
    public void initialize(){
        next.setDisable(true);
    }
    @FXML
    public void onclickedbutton(ActionEvent e) throws IOException {
        if(e.getSource().equals(next)){
            String key =keyn.getText();
            int ko=Integer.parseInt(key);

            l=Dataclass.getInstance().queryAgencythoughkey(ko);

            if(l!=null) {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("update2.fxml"));
                Parent upparent = loader.load();
                Scene upscene = new Scene(upparent);
                update2 upda = loader.getController();
                upda.initialized(ko);
                Stage upstage = (Stage) ((Node) e.getSource()).getScene().getWindow();
                upstage.setScene(upscene);
                upstage.show();
            }
            else
            {
                error.setText("Invalid Key Number");
                keyn.clear();
            }
        }else if(e.getSource().equals(back)){
            super.switchscene("agencyform.fxml",e);
        }
    }
    @FXML
    public void keyreleased(){
        String st1= keyn.getText();
        boolean disablebuttons = st1.isEmpty();
        next.setDisable(disablebuttons);

    }

}
