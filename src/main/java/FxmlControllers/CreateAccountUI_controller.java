package FxmlControllers;

import Core_logics.Username;
import Roles.Patient;
import Roles.UserRole;
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
    @FXML
    private ChoiceBox<String>numCode;
    @FXML
    private  Label codeNum;
    private String finalNumber;

    private final String[] codes ={
            "AF +93",
            "AL +355",
            "DZ +213",
            "AS +1-684",
            "AD +376",
            "AO +244",
            "AR +54",
            "AM +374",
            "AU +61",
            "AT +43",
            "AZ +994",
            "BS +1-242",
            "BH +973",
            "BD +880",
            "BB +1-246",
            "BY +375",
            "BE +32",
            "BZ +501",
            "BJ +229",
            "BT +975",
            "BO +591",
            "BA +387",
            "BW +267",
            "BR +55",
            "BN +673",
            "BG +359"
    };

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
        numCode.getItems().addAll(codes);
        numCode.setOnAction(this::selectedCode);
    }



    public void sign_up(ActionEvent e) throws IOException {
            Patient patient = new Patient();
             patient.setAll(getFirstNameField(),getLastNameField(),getPassword1Field(),getPassword2Field(),genderNull(),getEmailField(),finalNumber+getPhoneNumberField(),UserRole.PATIENT);
            if(patient.exceptionsPassed(getPassword1Field(),getPassword2Field())){
                patient.saveToDataBase();
                System.out.println("SIGNED UP");

                FXMLLoader load = new FXMLLoader(getClass().getResource("/UI/LoginUI.fxml"));
                Parent root = load.load();
                LoginUI_controller loginController = load.getController();
                loginController.successFullyCreatedAccount();
                Stage stage = (Stage)((Node)e.getSource()).getScene().getWindow(); //usages current stage rather than creating another
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.show();

            } else {
                errorLabel.setText(patient.getErrorText());
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

    public String genderNull(){
        String s;
        //tried object.equal but this hit an error while running -_-
        try {
             s = getSelectedGender().getText();
        } catch (NullPointerException e){
            s = "";
        }
        return s;
    }

    public void selectedCode(ActionEvent e) {
        String val=numCode.getValue();
        String code = val.substring(val.indexOf(" ")+1);
        codeNum.setText(code);
        finalNumber=code;
    }
}
