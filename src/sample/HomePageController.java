package sample;

import javafx.animation.FadeTransition;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.control.PopupControl;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class HomePageController implements Initializable {

    @FXML
    AnchorPane holderPane;

    @FXML
    private Button logout;

    @FXML
    private Button homeb;

    @FXML
    private Button exit;

    @FXML
    private Text welcome;



    AnchorPane home;

    private static HomePageController instance;

    public HomePageController()
    {
        instance = this;
    }

    public static HomePageController getInstance()
    {
        return instance;
    }

    public void createPage(AnchorPane homeN,String loc) {

        try {
            homeN = FXMLLoader.load(getClass().getResource(loc));
            setNode(homeN);
        } catch (IOException e) {

            e.printStackTrace();
        }
    }

    public void setUsername(String user){
        this.welcome.setText("Welcome, " + user);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {



        try {
            createPage();
        } catch (IOException e) {
            e.printStackTrace();
        }

        setUsername(LoginController.getInstance().username());

    }



    private void setNode(Node node) {

        holderPane.getChildren().clear();
        holderPane.getChildren().add((Node) node);

        FadeTransition ft = new FadeTransition(Duration.millis(1500));
        ft.setNode(node);
        ft.setFromValue(0.1);
        ft.setToValue(1);
        ft.setCycleCount(1);
        ft.setAutoReverse(false);
        ft.play();


    }

    private void createPage() throws IOException {

        try {
            home = FXMLLoader.load(getClass().getResource("Home.fxml"));
            setNode(home);


        }catch (IOException e) {
            e.printStackTrace();
        }

    }
    @FXML
    public void onexit(ActionEvent e){
        Platform.exit();
    }
    @FXML
    public void homebtn(ActionEvent e){
        setNode(home);
    }

    @FXML
    public void onlogout(ActionEvent e){

        logout.getScene().getWindow().hide();
        Stage login = new Stage();
        Parent root;
        try {
            root = FXMLLoader.load(getClass().getResource("sample.fxml"));
            Scene scene = new Scene(root);
            login.setScene(scene);
            login.setTitle("Login Page");
            login.show();
            login.setResizable(false);
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }
}
