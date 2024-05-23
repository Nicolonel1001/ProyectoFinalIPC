/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package javafxmlapplication;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author nicon
 */
public class AnadirController implements Initializable {

    @FXML
    private Button cancelButton;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void onCancel(ActionEvent event) {
        
        Stage stage = (Stage) this.cancelButton.getScene().getWindow();
        
        stage.close();
    }

    @FXML
    private void onAddCategory(ActionEvent event) throws IOException {
        FXMLLoader loader= new  FXMLLoader(getClass().getResource("AnadirCategoria.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root, 300, 300);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("Anadir una categoria");
        stage.initModality(Modality.APPLICATION_MODAL);
        //la ventana se muestra modal
        stage.show();
    }

    @FXML
    private void onAddExpense(ActionEvent event) throws IOException {
        FXMLLoader loader= new  FXMLLoader(getClass().getResource("AnadirGasto.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root, 300, 300);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("Anadir una gasto");
        stage.initModality(Modality.APPLICATION_MODAL);
        //la ventana se muestra modal
        stage.showAndWait();
            
        Stage currentStage = (Stage) cancelButton.getScene().getWindow();
        currentStage.close();

        stage.close();
    }
    
}
