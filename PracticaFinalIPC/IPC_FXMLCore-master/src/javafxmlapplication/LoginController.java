/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package javafxmlapplication;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.Acount;
import model.AcountDAOException;

/**
 * FXML Controller class
 *
 * @author ENVOGROJ
 */
public class LoginController implements Initializable {

    @FXML
    private TextField Nickname;
    @FXML
    private PasswordField Password;
    
    private Acount acount;
    @FXML
    private Text textoDeError;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            acount = Acount.getInstance();
        } catch (AcountDAOException | IOException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    

    @FXML
    private void loginAction(ActionEvent event) {
        textoDeError.setText("");
        String nickname = Nickname.getText().strip();
        String password = Password.getText().strip();
        System.out.println("Attempting to log in with nickname: " + nickname + " and password: " + password); // Debugging

        try {
            if (!acount.logInUserByCredentials(nickname, password)) {
                textoDeError.setText("Credentials not found");
            } else {
                System.out.println("Login successful!"); // Debugging
                Stage currentStage = (Stage) textoDeError.getScene().getWindow();
                currentStage.close();
                Stage stage = new Stage();
                FXMLLoader fxmlloader = new FXMLLoader(getClass().getResource("Inicio.fxml"));
                Pane root = fxmlloader.load();
                stage.setScene(new Scene(root, 600, 600));
                stage.show();
            }
        } catch (AcountDAOException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
            textoDeError.setText("Ocurrió un error al intentar iniciar sesión.");
        } catch (IOException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void onBack(ActionEvent event) {
        Stage currentStage = (Stage) textoDeError.getScene().getWindow();
        currentStage.close();
        Stage stage = new Stage();
        FXMLLoader fxmlloader = new FXMLLoader(getClass().getResource("PaginaPrincipal.fxml"));
        try {
            Pane root = fxmlloader.load();
            stage.setScene(new Scene(root, 600, 600));
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
