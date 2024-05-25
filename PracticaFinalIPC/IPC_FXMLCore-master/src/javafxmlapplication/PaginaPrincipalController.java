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
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author nicon
 */
public class PaginaPrincipalController implements Initializable {

    @FXML
    private Button boton;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void switchToLogin(ActionEvent event) throws IOException {
        
        
        Stage currentStage = (Stage)boton.getScene().getWindow();
            currentStage.close();
            Stage stage = new Stage();
            FXMLLoader fxmlloader = new FXMLLoader();
            
                try {
                    Pane root = fxmlloader.load(getClass().getResource("Login.fxml"));
                    stage.setScene(new Scene(root, 600, 600));
                    stage.show();
                } catch (IOException ex) {
                    Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
                }   
    }

    @FXML
    private void switchToRegister(ActionEvent event) throws IOException {
        Stage currentStage = (Stage)boton.getScene().getWindow();
            currentStage.close();
            Stage stage = new Stage();
            FXMLLoader fxmlloader = new FXMLLoader();
            
                try {
                    Pane root = fxmlloader.load(getClass().getResource("Register.fxml"));
                    stage.setScene(new Scene(root, 600, 600));
                    stage.show();
                } catch (IOException ex) {
                    Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
                }
        
    }
    
}
