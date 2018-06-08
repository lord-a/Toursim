package Database;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Switchscenes {


    public void switchscene(String file , ActionEvent e) throws IOException {
        Parent loginparent= FXMLLoader.load(getClass().getResource(file));
        Scene loginScene = new Scene(loginparent);

        Stage loginstage= (Stage)((Node)e.getSource()).getScene().getWindow();
        loginstage.setScene(loginScene);
        loginstage.show();
    }
}
