package com.userinterface;

import Database.Agency;
import Database.Dataclass;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;

public class GetAllagency extends Task<ObservableList<Agency>> {
    @Override
    public ObservableList<Agency> call() throws Exception {
        return FXCollections.observableArrayList(Dataclass.getInstance().querynamebyorder("ASC"));
    }
}
