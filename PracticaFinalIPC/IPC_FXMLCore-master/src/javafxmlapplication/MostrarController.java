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
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import model.Acount;
import model.AcountDAOException;
import model.Category;
import model.Charge;

/**
 * FXML Controller class
 *
 * @author nicon
 */
public class MostrarController implements Initializable {

    @FXML
    private ListView<Charge> listOfMonthlyCharges;
    
    private Acount acount;
    
    private LocalDate date = LocalDate.now();
    @FXML
    private Label Month_Year;
    @FXML
    private ListView<Charge> costListView;
    @FXML
    private Label precioTotal;
    
    private double total = 0;
    @FXML
    private MenuButton filtroCategorias;
    
    private String categoria = "Todo";
    
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            acount = Acount.getInstance();
        } catch (AcountDAOException ex) {
            Logger.getLogger(MostrarController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(MostrarController.class.getName()).log(Level.SEVERE, null, ex);
        }
        listOfMonthlyCharges.setCellFactory((c) -> {return new ChargeListCell();} );
        costListView.setCellFactory((c) -> {return new CostListCell();} );
         
        updateView();
        
 }    

    @FXML
    private void incrementMonth(ActionEvent event) {
        date = date.plusMonths(1);
        updateView();
    }

    @FXML
    private void decrementMonth(ActionEvent event) {
        date = date.minusMonths(1);
        updateView();
    }

    private void updateView(){
        try {
            List<Category> categories = acount.getUserCategories();
            
            filtroCategorias.getItems().clear();
            categories.forEach(category -> {
                filtroCategorias.getItems().add(new MenuItem(category.getName()));});
            
            filtroCategorias.getItems().forEach(item -> {
                item.setOnAction(event -> {
                  filtroCategorias.setText(item.getText());
              }); 
            });
           
        } catch (AcountDAOException ex) {
            Logger.getLogger(MostrarController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try {
            List<Charge> listOfCharges = acount.getUserCharges();
            
            if(!listOfCharges.isEmpty()){
                List<Charge> filteredList;
               
                filteredList = listOfCharges.stream().filter(charge -> (charge.getDate().getMonth() == date.getMonth()) && (charge.getDate().getYear() == date.getYear())).filter(c -> c != null).toList();
               
                listOfMonthlyCharges.getItems().clear();
                listOfMonthlyCharges.getItems().addAll(filteredList); 
                costListView.getItems().clear();
                costListView.getItems().addAll(filteredList); 
                
                total = 0;
                filteredList.forEach((charge -> { 
                    total += charge.getCost();
                }));
                precioTotal.setText("$" + String.valueOf(total));
            }      
        } catch (AcountDAOException ex) {
            Logger.getLogger(MostrarController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        StringBuilder sb = new StringBuilder();
        sb.append(date.getMonth().toString())
                .append("-")
                .append(String.valueOf(date.getYear()));
        Month_Year.setText(sb.toString());
    
    }

   
}

class ChargeListCell extends ListCell<Charge> {
    
    @Override
    protected void updateItem(Charge charge, boolean empty) {
        super.updateItem(charge, empty);
        setGraphic(null);
        if(charge == null){
            setText(null);
        } else {
            setText(charge.getName());
        } 
    }
}

class CostListCell extends ListCell<Charge> {
    
    @Override
    protected void updateItem(Charge charge, boolean empty) {
        super.updateItem(charge, empty);
        setGraphic(null);
        if(charge == null){
            setText(null);
        } else {
            setText("$" + String.valueOf(charge.getCost()));
        } 
    }
}
