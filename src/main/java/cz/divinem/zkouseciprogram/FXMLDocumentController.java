package cz.divinem.zkouseciprogram;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class FXMLDocumentController implements Initializable {
    private Stage stage;

    @FXML
    private Button startBtn;

    @FXML
    void zaciname(ActionEvent event) throws IOException {
        if (stage == null) {
            Parent parent = FXMLLoader.load(getClass().getResource("/VyberZakaFXML.fxml"));
            stage = new Stage(StageStyle.DECORATED);
            stage.setScene(new Scene(parent));
            stage.getIcons().add(new Image("/obrazky/ikona.png"));
            stage.setTitle("Zkoušecí program");
        }

        stage.show();
        Stage stage = (Stage) startBtn.getScene().getWindow();
        stage.close();

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

}
