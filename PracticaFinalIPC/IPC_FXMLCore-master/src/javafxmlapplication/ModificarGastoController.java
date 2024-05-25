/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package javafxmlapplication;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import model.Acount;
import model.AcountDAOException;
import model.Category;
import model.Charge;

/**
 * FXML Controller class
 *
 * @author nicon
 */
public class ModificarGastoController implements Initializable {

    @FXML
    private TextField nameField;
    @FXML
    private TextField descriptionField;
    @FXML
    private TextField costField;
    @FXML
    private TextField unitsField;
    @FXML
    private Button botonModify;
    @FXML
    private DatePicker dateField;
    @FXML
    private MenuButton categoryField;
    @FXML
    private Label l1;
    
    private Image image = null;
    
    private Charge charge;
    
    private Acount acount;
    
    private Category category;

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
        
        dateField.setDayCellFactory((DatePicker picker) -> {
            return new DateCell() {
                @Override
                public void updateItem(LocalDate date, boolean empty) {
                super.updateItem(date, empty);
                LocalDate today = LocalDate.now();
                setDisable(empty || date.compareTo(today) > 0 );
                    }
                };
            });
        
        try {
            List<Category> categories = acount.getUserCategories();
            categories.forEach(category -> {
                categoryField.getItems().add(new MenuItem(category.getName()));
            });
            categoryField.getItems().forEach(item -> {
                item.setOnAction(event -> {
                    categoryField.setText(item.getText());
                    
                    
                });
            });
            
            
        } catch (AcountDAOException ex) {
            Logger.getLogger(AnadirGastoController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }    

    @FXML
    private void modify(ActionEvent event) throws AcountDAOException {
        boolean valid = true;
        
        
        String name = nameField.getText().strip();
        String description = descriptionField.getText().strip();
        String s_cost = costField.getText().strip();
        String s_unit = unitsField.getText().strip();
        
        //verify name not blank 
        if (name.isBlank())
            valid = false;
        
        //verify description not blank
        if (description.isBlank())
            valid = false;
        
        //verificar que costo son solo digitos
        if (!isNumeric(s_cost)){
            //TODO cambiar color texto
            valid = false;
        }
        double cost = Double.parseDouble(s_cost);
        
        //verificar que units son solo digitos
        if (!s_unit.matches("\\d*")){
            //TODO cambiar color texto
            valid = false;
        }
        int unit = Integer.parseInt(s_unit);
        
       
        if(valid){
            
            acount.removeCharge(charge);
            getCategory();
            System.out.println(image);
            
            
            if(image.errorProperty().get()){
                acount.registerCharge(name, description, cost, unit, null, dateField.getValue(), category);
            } else {
                acount.registerCharge(name, description, cost, unit, image, dateField.getValue(), category);
            }
            
        Stage currentStage = (Stage) nameField.getScene().getWindow();
        currentStage.close();           
}
    }

    @FXML
    private void onCancel(ActionEvent event) {
        Stage currentStage = (Stage) nameField.getScene().getWindow();
        currentStage.close();
    }

    @FXML
    private void getDate(ActionEvent event) {
    }
 
    
    public void setCharge(Charge charge) {
        this.charge = charge;
        nameField.setText(charge.getName());
        descriptionField.setText(charge.getDescription());
        costField.setText(String.valueOf(charge.getCost()));
        unitsField.setText(String.valueOf(charge.getUnits()));
        dateField.setValue(charge.getDate());
        categoryField.setText(charge.getCategory().getName());
        image = charge.getImageScan();
        
    }

    private void getCategory() {

        try {
            List<Category> categories = acount.getUserCategories();
            category = categories.stream().filter(cat -> cat.getName().equals(categoryField.getText())).findFirst().get();
            
        } catch (AcountDAOException ex) {
            Logger.getLogger(AnadirGastoController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    private boolean isNumeric(String strNum) {
    if (strNum == null) {
        return false;
    }
    try {
        double d = Double.parseDouble(strNum);
    } catch (NumberFormatException nfe) {
        return false;
    }
    return true;
}
}
