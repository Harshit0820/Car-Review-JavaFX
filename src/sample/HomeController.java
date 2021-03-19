package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;

public class HomeController implements Initializable {

    @FXML
    private AnchorPane homeAnchor;

    @FXML
    private Button maruti;

    @FXML
    private Button hyundai;

    @FXML
    private Button skoda;

    @FXML
    private Button tataMotors;

    private AnchorPane CarsAnchor;

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {



    }

    @FXML
    public void marutiAction(ActionEvent e)
    {

        HomePageController.getInstance().createPage(CarsAnchor, "Maruti.fxml");


    }

    @FXML
    public void skodaAction(ActionEvent event) {

        HomePageController.getInstance().createPage(CarsAnchor, "Skoda.fxml");


    }

    @FXML
    public void hyundaiAction(ActionEvent event) {


        HomePageController.getInstance().createPage(CarsAnchor, "Hyundai.fxml");

    }


    @FXML
    public void tataMotorsAction(ActionEvent event) {

        HomePageController.getInstance().createPage(CarsAnchor, "Tata.fxml");

    }




}

