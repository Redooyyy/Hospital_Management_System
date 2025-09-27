package com.mycompany.hms;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */


import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

/**
 *
 * @author Reo
 */
public class LoginUI_controller implements Initializable{
    @FXML
    private TextField userNameTextField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private ChoiceBox<String>roleDropdown;
    
    private String[] roles = {"Admin" , "Doctor" , "Receptionist" , "Pharmacist" , "Patient"};

    public String getUserNameTextField() {
        return userNameTextField.getText();
    }

    public String getPassTextField() {
        return passwordField.getText();
    }

    //abstract methode for adding options to dropdown menue(Because Choice box does not have any addAll method)
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        roleDropdown.getItems().addAll(roles);
        roleDropdown.setStyle("-fx-font-size: 18px;"+"-fx-background-radius: 22px;");
    }
    
    
    
    
    
}
