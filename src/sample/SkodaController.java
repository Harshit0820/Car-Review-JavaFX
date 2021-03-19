package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;

public class SkodaController implements Initializable {

    @FXML
    private Button back;

    @FXML
    private AnchorPane SkodaAnchor;

    AnchorPane CarInfo;

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {


    }

    @FXML
    public void backaction(ActionEvent e)
    {

        HomePageController.getInstance().createPage(CarInfo, "HOME.fxml");

    }






}
