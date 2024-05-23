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
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Acount;
import model.AcountDAOException;

/**
 * FXML Controller class
 *
 * @author nicon
 */
public class AnadirCategoriaController implements Initializable {

    @FXML
    private TextField categoryName;
    @FXML
    private TextField categoryDescription;
    
    private Acount acount;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            acount = Acount.getInstance();
        } catch (AcountDAOException ex) {
            Logger.getLogger(AnadirCategoriaController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(AnadirCategoriaController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    

    @FXML
    private void onCancelar(ActionEvent event) {
        Stage stage = (Stage) this.categoryName.getScene().getWindow();
        
        stage.close();
    }

    @FXML
    private void onCrear(ActionEvent event) throws AcountDAOException {
    
        boolean valid = true;
        
        String nombre = categoryName.getText().strip();
        String descripcion = categoryDescription.getText().strip();
        
        if (nombre.isBlank())
            valid = false;
        
        if (descripcion.isBlank())
            valid = false;
        
        if(valid)
            valid = acount.registerCategory(nombre, descripcion);
        
        if(valid) {
            Stage stage = (Stage) this.categoryName.getScene().getWindow();
            stage.close();
        } else {
            //TODO DIALOGO DE ERROR + RETURN A PAGINA ANTERIOR
        }
    }
    
}
