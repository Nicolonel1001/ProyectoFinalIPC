/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package javafxmlapplication;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.Locale.Category;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import model.Acount;
import model.AcountDAOException;

/**
 * FXML Controller class
 *
 * @author nicon
 */
public class AnadirGastoController implements Initializable {

    @FXML
    private TextField nameField;
    @FXML
    private TextField descriptionField;
    @FXML
    private TextField costField;
    @FXML
    private TextField unitsField;
    
    private Acount acount;
    
    private Image image;
    
    private LocalDate date;
    
    private Category category;
    @FXML
    private Button botonAddExpense;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            acount = Acount.getInstance();
        } catch (AcountDAOException ex) {
            Logger.getLogger(AnadirGastoController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(AnadirGastoController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }    

    @FXML
    private void onAddExpense(ActionEvent event) {
        String name = nameField.getText().strip();
        String description = descriptionField.getText().strip();
        String s_cost = costField.getText().strip();
        String s_unit = unitsField.getText().strip();
        
        
        if (!s_cost.matches("\\d*")){
            //todo cambiar color texto
        }
        
        double cost = Double.parseDouble(s_cost);
        
        
    }

    @FXML
    private void onCancel(ActionEvent event) {
        Stage stage = (Stage) this.nameField.getScene().getWindow();
        stage.close();
        
    }
    
}
