package com.userinterface;

import Database.Agency;
import Database.Dataclass;
import Database.Switchscenes;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.io.IOException;

public class update2  extends Switchscenes {
     update upp=null;
    public int k ;
    @FXML
     private  JFXButton cancel1, update;
    @FXML
    private JFXTextField name1;
    @FXML
   private  JFXTextField place1;
    @FXML
   private  JFXTextField phone1;
    @FXML
  private  JFXTextField email1;
    @FXML
   private  JFXComboBox<String> type1= new JFXComboBox<>();
    @FXML
    private Label key,d;

    public void initialized(int k){
        type1.getItems().addAll("Restaurants and Cafe ","Lodges ", "Hotels", "Transportation", "Agencies");
        Agency li= null;
        key.setText(Integer.toString(k));
        li=Dataclass.getInstance().queryAgencythoughkey(k);
        name1.setText(li.getName());
        place1.setText(li.getPlace());
        phone1.setText(Integer.toString(li.getPhonenumber()));
        email1.setText(li.getEmail());
    }


    @FXML
    public void buttonsclicked(ActionEvent e) throws IOException,NumberFormatException {
        if(e.getSource().equals(cancel1)){
            super.switchscene("update1.fxml",e);
        }else if(e.getSource().equals(update)){
            String t = type1.getSelectionModel().getSelectedItem();
            long pho = Integer.parseInt(phone1.getText());
            int c=Dataclass.getInstance().updateinagency(name1.getText(), place1.getText(), t, pho, email1.getText(), Integer.parseInt( key.getText()));
            if(c==1){
                d.setText("Your Profile has been updated");
            }else{
                d.setText("Oops!! something went wrong");
            }
        }
    }

}
