/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package javafxmlapplication;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
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
public class RegisterController implements Initializable {

    private Acount acount;
    
    @FXML
    private TextField Password;
    @FXML
    private TextField Correo;
    @FXML
    private TextField Nombre;
    @FXML
    private TextField Nickname;
    private ImageView profilePicture;
    
    private boolean validated = false;
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
            Logger.getLogger(RegisterController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(RegisterController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    

    @FXML
    private void registerAction(ActionEvent event) {
        
        String name = Nombre.getText().strip();
        String nickname = Nickname.getText().strip();
        String password = Password.getText().strip();
        String correo = Correo.getText().strip();
        LocalDate date = LocalDate.now();
        File file = new File("C:\\Users\\nicon\\Desktop\\ProyectoFinalIPC\\PracticaFinalIPC\\IPC_FXMLCore-master\\src\\Imagenes\\Iconos\\nopp.jpg");
        Image image = new Image(file.toURI().toString());
        
        textoDeError.setText("");
        validated = false;
        
        if (nickname.contains(" "))
            textoDeError.setText("Nickname cannot contain spaces!");
        
        for(int i = 0; i < password.length(); i++) {
            if (!Character.isAlphabetic(password.charAt(i))){
                textoDeError.setText("Password must contain only alphanumeric characters !");
            }
                
        }
        
        if (password.length() <= 6)
            textoDeError.setText("Su contrasena deber tener al menos 7 caracteres !");
        
        if(password.length() == 0 || name.length() == 0 || nickname.length() == 0 || correo.length() == 0)
            textoDeError.setText("You must fill in all the form !");
        
        if(textoDeError.getText().equals("")) { // Si no hay errores, intentar registrarse
            try {
                validated =
                        acount.registerUser(name, nickname, name, correo, password, image, date);
            } catch (AcountDAOException ex) {
                Logger.getLogger(RegisterController.class.getName()).log(Level.SEVERE, null, ex);
            }

            if(!validated) { 
                textoDeError.setText("Unsuccessfull register !");
            } else {
                createSuccessfullAlert();
            }
        }
        
        
    
    }

    private void changeProfilePicture(ActionEvent event) {
    //TODO
    System.out.println("change profile picture");
    
    Alert alert = new Alert(AlertType.INFORMATION);
    alert.setTitle("Cambiar foto de perfil");
    alert.setHeaderText("Selecciona tu foto de perfil");
    
//TODO cambiar foto de perfil
//alert.getDialogPane().setContent();
    }
    
    private void createSuccessfullAlert() {
        
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Register done");
        alert.setHeaderText(null);
        alert.setContentText("You now have to login to use the application !");
        alert.showAndWait();
        
        
        try {
            Stage currentStage = (Stage)textoDeError.getScene().getWindow();
            currentStage.close();
            
            Stage stage = new Stage();
            FXMLLoader fxmlloader = new FXMLLoader();
            Pane root = fxmlloader.load(getClass().getResource("Login.fxml"));
            stage.setScene(new Scene(root, 600, 600));
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }

    @FXML
    private void onBack(ActionEvent event) {
        try {
            Stage currentStage = (Stage)textoDeError.getScene().getWindow();
            currentStage.close();
            
            Stage stage = new Stage();
            FXMLLoader fxmlloader = new FXMLLoader();
            Pane root = fxmlloader.load(getClass().getResource("PaginaPrincipal.fxml"));
            stage.setScene(new Scene(root, 600, 600));
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
