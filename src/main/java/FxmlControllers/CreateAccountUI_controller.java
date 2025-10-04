package FxmlControllers;

import Database.GetFrom_DB;
import Database.UserAdd_DB;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class CreateAccountUI_controller implements Initializable {
    @FXML
    private TextField lastNameField;
    @FXML
    private TextField firstNameField;
    @FXML
    private TextField emailField;
    @FXML
    private TextField phoneNumberField;
    @FXML
    private PasswordField password1Field;
    @FXML
    private PasswordField password2Field;
    @FXML
    private Label errorLabel;
    @FXML
    private ToggleGroup gender;
    @FXML
    private Label genderLabel;
    private RadioButton selectedGender;

     //getters
    public RadioButton getSelectedGender() {
        return (RadioButton) gender.getSelectedToggle();
    }
    public String getFirstNameField() {
        return firstNameField.getText();
    }
    public String getLastNameField() {
        return lastNameField.getText();
    }
    public String getEmailField() {
        return emailField.getText();
    }
    public String getPhoneNumberField() {
        return phoneNumberField.getText();
    }
    public String getPassword1Field() {
        return password1Field.getText();
    }
    public String getPassword2Field() {
        return password2Field.getText();
    }

    //setters (for reset button)
    public void setFirstNameField(String firstNameField) {
        this.firstNameField.setText(firstNameField);
    }
    public void setLastNameField(String lastNameField) {
        this.lastNameField.setText(lastNameField);
    }
    public void setEmailField(String emailField) {
        this.emailField.setText(emailField);
    }
    public void setPhoneNumberField(String phoneNumberField) {
        this.phoneNumberField.setText(phoneNumberField);
    }
    public void setPassword1Field(String password1Field) {
        this.password1Field.setText(password1Field);
    }
    public void setPassword2Field(String password2Field) {
        this.password2Field.setText(password2Field);
    }
    public void unselect(){
        gender.selectToggle(null);
        genderLabel.setText("  gender");
        genderLabel.setTextFill(Color.web("#a1a0a0"));
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Platform.runLater(() -> errorLabel.getParent().requestFocus()); //to avoid default focus while switch scene
    }

    public void sign_up(ActionEvent e) throws IOException {
            if(exceptionsPassed()){
                //add all info to database
                UserAdd_DB add = new UserAdd_DB();
                String fullName = getFirstNameField() + " " + getLastNameField();
                add.addUser(fullName,getEmailField(),getPassword1Field(),getPhoneNumberField(),getSelectedGender().getText());

                System.out.println("SIGNED UP");

                FXMLLoader load = new FXMLLoader(getClass().getResource("/UI/LoginUI.fxml"));
                Parent root = load.load();
                LoginUI_controller loginController = load.getController();
                loginController.successFullyCreatedAccount();
                Stage stage = (Stage)((Node)e.getSource()).getScene().getWindow(); //usages current stage rather than creating another
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.show();

            }
    }

    public void reset(ActionEvent e){
        setFirstNameField("");
        setLastNameField("");
        setEmailField("");
        setPhoneNumberField("");
        setPassword1Field("");
        setPassword2Field("");
        unselect();
    }

    public void sign_inPage(ActionEvent e) throws IOException {
        SwitchScene switchscene = new SwitchScene();
        switchscene.switchscene(e,"/UI/LoginUI.fxml");
    }

    public void getGender(ActionEvent e){
        genderLabel.setText("  "+getSelectedGender().getText());
        genderLabel.setTextFill(Color.BLACK);

    }

    //all exceptions handled(almost -_-)
    public boolean exceptionsPassed(){
        //Null safeties
        if(Objects.equals(getFirstNameField(),"") || Objects.equals(getLastNameField(),"")){
            errorLabel.setText("Please enter your first and last name");
            return false;
        }
        if (getFirstNameField().length() < 2 || getLastNameField().length() < 2){
            errorLabel.setText("Please enter a valid name");
            return false;
        }
        for(int i =0;i<getFirstNameField().length();i++){
            if((getFirstNameField().toLowerCase().charAt(i) < 'a' || getFirstNameField().toLowerCase().charAt(i) > 'z') && getFirstNameField().charAt(i) != ' '){
                errorLabel.setText("Name can not contains number or special characters");
            } else {
                continue;
            }
            return false;
        }
        for(int i =0; i < getLastNameField().length(); i++){
            if((getLastNameField().toLowerCase().charAt(i) < 'a' || getLastNameField().toLowerCase().charAt(i) > 'z') && getLastNameField().charAt(i)!= ' '){
                errorLabel.setText("Name can not contains number or special characters");
            } else {
                continue;
            }
            return false;
        }
        //tried object.equal but this hit an error while running -_-
        try {
            String s = getSelectedGender().getText();
        } catch (NullPointerException e){
            errorLabel.setText("Please select your gender");
            return false;
        }
        if(Objects.equals(getEmailField(),"")){
            errorLabel.setText("Please enter your email");
            return false;
        }
        //still many exception, but we'll add those logic later for valid email
        int indx = getEmailField().lastIndexOf('@');
        String domain = getEmailField().substring(indx+1);
        String local = getEmailField().substring(0,indx);
        if(!domain.equals("gmail.com") || local.indexOf('@')!=-1) {
            errorLabel.setText("Invalid email address");
            return false;
        }
        if(!Objects.equals(GetFrom_DB.getPasswordByEmail(getEmailField()), null)){
            errorLabel.setText("Email is already registered");
            return false;
        }
        if(Objects.equals(getPhoneNumberField(),"")){
            errorLabel.setText("Please enter your phone number");
            return false;
        }
        //valid phone number or not
        String validNum="+0123456789";
        for(int i =0;i<getPhoneNumberField().length();i++){
            if (getPhoneNumberField().length() < 5){
                errorLabel.setText("Invalid phone number");
                return false;
            }
            if(validNum.indexOf(getPhoneNumberField().charAt(i)) == -1){
                errorLabel.setText("Invalid phone number");
                return false;
            }
        }
        if(Objects.equals(getPassword1Field(),"")||Objects.equals(getPassword2Field(),"")){
            errorLabel.setText("Please set your password");
            return false;
        }
        if(getPassword1Field().length() < 7){
            errorLabel.setText("Password must be more than 7 characters");
            return false;
        }
        if(getPassword1Field().length() > 15){
            errorLabel.setText("Password must be less than 15 characters");
            return  false;
        }
        //User exist in database or not
        if(!Objects.equals(getPassword1Field(), getPassword2Field())){
            errorLabel.setText("Password mismatch, Please enter matching password");
            return false;
        }
        return true;
    }
}
