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
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.Acount;
import model.AcountDAOException;
import model.Category;

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
    
    private Image image = null;
    
    private LocalDate date = LocalDate.now();
    
    private Category category = null;
    @FXML
    private Button botonAddExpense;
    @FXML
    private DatePicker dateField;
    @FXML
    private MenuButton categoryField;
    
    private String categoryTitle;
    @FXML
    private Label l1;


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
            getUserCategories();
        } catch (AcountDAOException ex) {
            Logger.getLogger(AnadirGastoController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    

    @FXML
    private void onAddExpense(ActionEvent event) throws AcountDAOException {
        
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
        if (!s_cost.matches("\\d*")){
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
        
        if(category == null)
            valid = false;
        
        
        if(valid)
            valid = acount.registerCharge(name, description, cost, unit, image, date, category);
        
        if(valid){
            switchToMainMenu();
        }else{
            switchToMainMenuWithError();}
        
        
        
        
        
        
        
        
    }

    @FXML
    private void onCancel(ActionEvent event) {
        Stage stage = (Stage) this.nameField.getScene().getWindow();
        stage.close();
        
    }

    @FXML
    private void getDate(ActionEvent event) {
        date = dateField.getValue();
    }



    private void getUserCategories() throws AcountDAOException{
        
        
        List<Category> categories = acount.getUserCategories();
        
        if(categories.isEmpty()) {
            botonAddExpense.disableProperty().set(true);
            nameField.disableProperty().set(true);
            descriptionField.disableProperty().set(true);
            costField.disableProperty().set(true);
            unitsField.disableProperty().set(true);
            dateField.disableProperty().set(true);
            categoryField.disableProperty().set(true);
            l1.setText("Debes crear una categoria para tus gastos primero !! ");
            
            
        } else {
            categories.forEach(category -> {
                categoryField.getItems().add(new MenuItem(category.getName()));
            });
            categoryField.getItems().forEach(item -> {
                item.setOnAction(event -> {
                    categoryField.setText(item.getText());
                    categoryTitle = item.getText();
                    getCategory();
                });
            });
        }
        
    }

    
    private void getCategory() {
        try {
            List<Category> categories = acount.getUserCategories();
            category = categories.stream().filter(cat -> cat.getName().equals(categoryTitle)).findFirst().get();
            
        } catch (AcountDAOException ex) {
            Logger.getLogger(AnadirGastoController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void switchToMainMenu() {
        Stage currentStage = (Stage) nameField.getScene().getWindow();
        currentStage.close();
        
    }

    private void switchToMainMenuWithError() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}

