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
            // TODO
            acount = Acount.getInstance();
        } catch (AcountDAOException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    

    @FXML
    private void loginAction(ActionEvent event) {
        textoDeError.setText("");
        try {
            if(!acount.logInUserByCredentials(Nickname.getText().strip(), Password.getText().strip())){
                textoDeError.setText("Credentials not found");
            } else {
                
                
            Stage currentStage = (Stage)textoDeError.getScene().getWindow();
            currentStage.close();
            Stage stage = new Stage();
            FXMLLoader fxmlloader = new FXMLLoader();
            
                try {
                    Pane root = fxmlloader.load(getClass().getResource("Inicio.fxml"));
                    stage.setScene(new Scene(root, 600, 600));
                    stage.show();
                } catch (IOException ex) {
                    Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
                }
            
                
                
            }   
        } catch (AcountDAOException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
}
    
}
