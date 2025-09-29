package FxmlControllers;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;
import Core_logics.LoginLogic;
import Database.GetFrom_DB;
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
        Platform.runLater(() -> errorLabel.getParent().requestFocus()); //to avoid default focus while switch scene
    }

    public String getSelectedRole() {
        return roleDropdown.getValue();
    }

    public void login(ActionEvent e) {
        try {
            //because after calling that successfully created account methode property of the errorLabel will be change so re-initialized needed :)
            errorLabel.setTextFill(Color.web("#d70909"));
            errorLabel.setStyle("-fx-background-color:  #E7EFE7;"+ "-fx-font-weight: normal");


            LoginLogic loginLogic = new LoginLogic();
            if(Objects.equals(getUserNameTextField(), "") || Objects.equals(getPassTextField(), "")) errorLabel.setText("username/password can not be empty");
            else if (UserRole.userRoleID(getSelectedRole()) == 0) errorLabel.setText("Please select your role");

            //probably the most annoying condition I've ever written -_- although it doesn't matter if it works :) ....and it worked  (>_<)
            else if ((loginLogic.loggedInUsername(getUserNameTextField(),getPassTextField()) || loginLogic.loggedInEmail(getUserNameTextField(),getPassTextField())) && (GetFrom_DB.getRoleID(GetFrom_DB.getUserID(getUserNameTextField())) == UserRole.userRoleID(getSelectedRole())||GetFrom_DB.getRoleID(GetFrom_DB.getUserIDbyEmail(getUserNameTextField())) == UserRole.userRoleID(getSelectedRole()))) {
                System.out.println("Successfully logged in");
                //role wise windows(Scenes)

                //switching to interface but before that passing the usernames to UserUI()
                if(Objects.equals(getSelectedRole(), "Patient")){
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/UI/UserUI.fxml"));
                    Parent root = loader.load();
                    UserUI_controller controller = loader.getController();

                    //email or username?
                    if(getUserNameTextField().indexOf('@') == -1){
                        controller.setFullName(GetFrom_DB.getFullNameByUsername(getUserNameTextField()));
                        controller.setUsername(getUserNameTextField());
                    } else {
                        controller.setFullName(GetFrom_DB.getFullNameByEmail(getUserNameTextField()));
                        controller.setUsername(GetFrom_DB.getUserNameByEmail(getUserNameTextField()));
                    }
                    Stage stage = (Stage)((Node)e.getSource()).getScene().getWindow();
                    Scene scene = new Scene(root);
                    stage.setScene(scene);
                    stage.show();
                }
            } else {

                errorLabel.setText("Invalid username or password");
            }

        } catch (Exception e1){
            e1.printStackTrace();
        }
    }


    public void createAccountPage(ActionEvent e) throws IOException {
        SwitchScene switchScene = new SwitchScene();
        switchScene.switchscene(e,"/UI/CreateAccountUI.fxml");
    }


    public void successFullyCreatedAccount(){
        //values are changing must re-initialize after thin function call
        errorLabel.setStyle("-fx-background-color:  #AFF2AF;" + "-fx-font-weight : 700;");
        errorLabel.setTextFill(Color.WHITE);
        errorLabel.setText("Account created successfully");
    }
}
