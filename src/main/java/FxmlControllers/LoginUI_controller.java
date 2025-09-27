package FxmlControllers;

import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;
import Core_logics.LoginLogic;
import Database.GetFrom_DB;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

/**
 *
 * @author Reo
 */
public class LoginUI_controller implements Initializable{
    @FXML
    private TextField userNameTextfield;
    @FXML
    private Label errorLabel;
    @FXML
    private PasswordField passwordField;
    @FXML
    private ChoiceBox<String>roleDropdown;
    
    private final String[] roles = {"Admin" , "Doctor" , "Receptionist" , "Pharmacist" , "Patient"};

    public String getUserNameTextField() {
        return userNameTextfield.getText();
    }

    public String getPassTextField() {
        return passwordField.getText();
    }

    //abstract methode for adding options to dropdown menu(Because Choice box does not have any addAll method)
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        roleDropdown.getItems().addAll(roles);
        roleDropdown.setStyle("-fx-font-size: 18px;"+"-fx-background-radius: 22px;"); //-_- There wasn't any border radius option in scene builder
    }

    public int getSelectedRole() {
        String role = roleDropdown.getValue();
        int role_id = 0;
        if(Objects.equals(role, "Admin"))role_id=1;
        else if(Objects.equals(role, "Patient"))role_id=2;
        else if(Objects.equals(role, "Doctor"))role_id=3;
        else if (Objects.equals(role, "Receptionist"))role_id=4;
        else if(Objects.equals(role, "Pharmacist"))role_id=5;
        return role_id;
    }

    public void login(ActionEvent e) {
        try {
            LoginLogic loginLogic = new LoginLogic();
            if(Objects.equals(getUserNameTextField(), "") || Objects.equals(getPassTextField(), "")) errorLabel.setText("username/password can not be empty");
            else if (getSelectedRole() == 0) errorLabel.setText("Role Can not be Empty");
            else if (loginLogic.loggedInUsername(getUserNameTextField(),getPassTextField()) && GetFrom_DB.getRoleID(GetFrom_DB.getUserID(getUserNameTextField())) == getSelectedRole()) {
                System.out.println("Successfully logged in");
                //role wise windows(Scenes)
            } else {
                errorLabel.setText("Invalid username or password");
            }

        } catch (Exception e1){
            e1.printStackTrace();
        }
    }


}
