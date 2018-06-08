package com.userinterface;


import Database.Agency;

import Database.Dataclass;
import Database.Switchscenes;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import java.io.IOException;
public class insidecontroller extends Switchscenes {
    @FXML
    TableView<Agency> tv;
    @FXML
    ListView<String> cate ;
    @FXML
    Button sigout;
    @FXML
    TextField searchitem;
    @FXML
    Button request;
    @FXML
    Button search;
    @FXML
    Label there,aname,aloc,acat,aphn,aemail;

    public void initializess(String s) {
        cate.getItems().addAll("Restaurants and Cafe ", "Lodges ", "Hotels", "Transportation", "Agencies");
        Task<ObservableList<Agency>> task = new GetAllagency();
        there.setText(s);
       tv.itemsProperty().bind(task.valueProperty());
        new Thread(task).start();
    }
    @FXML
    public void onlistclicked(){
        final String  te =  cate.getSelectionModel().getSelectedItem();
        Task<ObservableList<Agency>> t=new Task<ObservableList<Agency>>() {
            @Override
            protected ObservableList<Agency> call() throws Exception {
                return FXCollections.observableArrayList(Dataclass.getInstance().querytype(te));
            }
        };
        tv.itemsProperty().bind(t.valueProperty());
        new Thread(t).start();
    }

    @FXML
    public void ontableviewclicked(){
        final Agency ag=(Agency)tv.getSelectionModel().getSelectedItem();
        Agency al=Dataclass.getInstance().queryAgency(ag.getName());
        aname.setText("Name of an Organization :" + al.getName());
        aloc.setText( "Location: "+ al.getPlace());
        aemail.setText("Email:"+al.getEmail());
        acat.setText("Category:"+ al.getType());
        aphn.setText( "Phone Number :" + al.getPhonenumber());

    }
    @FXML
    public void onsignbutton(ActionEvent e) throws IOException {
        if(e.getSource().equals(sigout)){
            super.switchscene("login.fxml",e);
        }
        else if(e.getSource().equals(search)){
            final String  te =  searchitem.getText();
            Task<ObservableList<Agency>> t=new Task<ObservableList<Agency>>() {
                @Override
                protected ObservableList<Agency> call() throws Exception {
                    return FXCollections.observableArrayList(Dataclass.getInstance().querynamebyplace(
                            te));
                }
            };
            tv.itemsProperty().bind(t.valueProperty());
            new Thread(t).start();

        }else if(e.getSource().equals(request)){
            final Agency ag=(Agency)tv.getSelectionModel().getSelectedItem();
            Agency al=Dataclass.getInstance().queryAgency(ag.getName());
            FXMLLoader loader = new FXMLLoader(getClass().getResource("lastfile.fxml"));
            Parent upparent = loader.load();
            Scene upscene = new Scene(upparent);
            lastclass upda = loader.getController();
            upda.ini(al.getName());
            Stage upstage = (Stage) ((Node) e.getSource()).getScene().getWindow();
            upstage.setScene(upscene);
            upstage.show();
        }
    }
}

