package sample;

import javafx.animation.PauseTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import DBConnection.DBHandler;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class SignUpController implements Initializable {

    @FXML
    private AnchorPane anchorPane;

    @FXML
    private Button signup;

    @FXML
    private TextField name;

    @FXML
    private PasswordField password;

    @FXML
    private RadioButton male;

    @FXML
    private RadioButton female;

    @FXML
    private RadioButton other;

    @FXML
    private TextField location;

    @FXML
    private Label genders;

    @FXML
    private Button login;

    private DBHandler handler;
    private Connection connection;
    private PreparedStatement pst;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        name.setStyle("-fx-text-inner-color: black");
        password.setStyle("-fx-text-inner-color: black");
        location.setStyle("-fx-text-inner-color: black");

         handler = new DBHandler();



    }

    @FXML
    public void signUpAction(ActionEvent e) throws SQLException {

        PauseTransition pt = new PauseTransition();
//        pt.setDuration(Duration.seconds(0));
        pt.setOnFinished(event -> {

            System.out.println("Sign Up Successfully");

        });
        pt.play();

        String insert = "INSERT INTO users(names,password,gender,location)"
                + "VALUES (?,?,?,?)";

        connection = handler.getConnection();
        try {
            pst = connection.prepareStatement(insert);
        } catch (SQLException e1) {
            e1.printStackTrace();
        }

        try {
            pst.setString(1, name.getText());
            pst.setString(2, password.getText());
            pst.setString(3, getGender());
            pst.setString(4, location.getText());


            pst.executeUpdate();


        }
        catch (SQLException e1) {

            e1.printStackTrace();
        }




    }

    @FXML
    public void loginAction(ActionEvent e1) throws IOException {

        signup.getScene().getWindow().hide();

        Stage login = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        Scene scene = new Scene(root);
        login.setTitle("Login Page");
        login.setScene(scene);
        login.show();
        login.setResizable(false);


    }

    public String getGender() {

        String gen ="";

        if(male.isSelected())
        {
            gen = "Male";
        }
        else if(female.isSelected())
        {
            gen = "Female";
        }
        else if(other.isSelected())
        {
            gen = "Other";
        }

        return gen;
    }


}
