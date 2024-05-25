/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package javafxmlapplication;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.Acount;
import model.AcountDAOException;

/**
 * FXML Controller class
 *
 * @author nicon
 */
public class InicioController implements Initializable {

    @FXML
    private Button botonVerGastos;
    
    private Acount acount;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            acount = Acount.getInstance();
        } catch (AcountDAOException ex) {
            Logger.getLogger(InicioController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(InicioController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    

    @FXML
    private void onAnadirGasto(ActionEvent event) throws IOException {
    
        Stage currentStage = (Stage) botonVerGastos.getScene().getWindow();
        currentStage.close();
        Stage stage = new Stage();
        FXMLLoader fxmlloader = new FXMLLoader();

            try {
                Pane root = fxmlloader.load(getClass().getResource("AnadirGasto.fxml"));
                stage.setScene(new Scene(root, 600, 600));
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
            }
        
    }

    @FXML
    private void onShowExpense(ActionEvent event) throws IOException {
        
        Stage currentStage = (Stage) botonVerGastos.getScene().getWindow();
        currentStage.close();
        Stage stage = new Stage();
        FXMLLoader fxmlloader = new FXMLLoader();
            
        try {
            Pane root = fxmlloader.load(getClass().getResource("Mostrar.fxml"));
            stage.setScene(new Scene(root, 600, 400));
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
            
    }

    @FXML
    private void onLogout(ActionEvent event) {
        
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Logout");
        alert.setHeaderText("Logout");
        alert.setContentText("Are you sure you want to logout?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){
            logout();
        } else {
            alert.close();
            // ... user chose CANCEL or closed the dialog
    }
        
        
    }

    private void logout() {
        acount.logOutUser();
        Stage currentStage = (Stage) botonVerGastos.getScene().getWindow();
        currentStage.close();
        Stage stage = new Stage();
        FXMLLoader fxmlloader = new FXMLLoader();
            
        try {
            Pane root = fxmlloader.load(getClass().getResource("PaginaPrincipal.fxml"));
            stage.setScene(new Scene(root, 600, 400));
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
