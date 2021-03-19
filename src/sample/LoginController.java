package sample;

import DBConnection.DBHandler;
import javafx.animation.PauseTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class LoginController implements Initializable{

    @FXML
    private Button signup;

    @FXML
    private TextField username;

    @FXML
    private CheckBox remember;

    @FXML
    private Button login;

    @FXML
    private Button forgotPassword;

    @FXML
    private PasswordField password;

    private DBHandler handler;
    private Connection connection;
    private PreparedStatement pst;
    private static LoginController instance;

    public LoginController(){
        instance = this;
    }

    public static LoginController getInstance(){
        return instance;
    }

    public String username(){
        return username.getText();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        username.setStyle("-fx-text-inner-color: black");
        password.setStyle("-fx-text-inner-color: black");

        handler = new DBHandler();


    }

    @FXML
    public void loginAction(ActionEvent e) throws SQLException {

        PauseTransition pt = new PauseTransition();
//        pt.setDuration(Duration.seconds(0));
        pt.setOnFinished(event -> {
        });
        pt.play();

        connection = handler.getConnection();
        String q1 = "SELECT * from users where names=? and password=?";

        try {
            pst = connection.prepareStatement(q1);
            pst.setString(1,username.getText());
            pst.setString(2,password.getText());
            ResultSet rs = pst.executeQuery();

            int count = 0;

            while (rs.next()){
                count = count+1;
            }
            if (count==1){
                login.getScene().getWindow().hide();

                Stage home = new Stage();
                Parent root = FXMLLoader.load(getClass().getResource("HomePage.fxml"));
                Scene scene = new Scene(root);
                home.setScene(scene);
                home.setTitle("Home Page");
                home.show();
                home.setResizable(false);

            }else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText(null);
                alert.setContentText("Username and Password is not Correct");
                alert.show();

            }

        }catch (SQLException | IOException e1) {
            e1.printStackTrace();
        }
        finally {
            try {
                connection.close();

            }catch (SQLException e1){
                e1.printStackTrace();
            }
        }


    }

    @FXML
    public void signUpAction(ActionEvent e1) throws IOException {

        login.getScene().getWindow().hide();

        Stage signup = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("signup.fxml"));
        Scene scene = new Scene(root);
        signup.setTitle("Sign Up Page");
        signup.setScene(scene);
        signup.show();
        signup.setResizable(false);


    }

}
