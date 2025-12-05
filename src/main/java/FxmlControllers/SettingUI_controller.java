package FxmlControllers;

import Database.GetFrom_DB;
import Database.Update_DB;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class SettingUI_controller implements Initializable {
    @FXML
    private TextField fullName;
    @FXML
    private TextField mobile;
    @FXML
    private Label email;
    @FXML
    private TextField gender;
    @FXML
    private Label accountCreatedDate;
    @FXML
    private Label userName;
    @FXML
    private TextField newPass;
    @FXML
    private TextField confirmPass;
    private String usernm;

    public SettingUI_controller(){
    }
    public void setUsername(String username) {
        this.usernm = username;
    }
    public SettingUI_controller(String username) {
        this.usernm = username;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void cancel(ActionEvent event) {
    }

    public void saveChanges(ActionEvent event) {
        Update_DB.updateFullName(fullName.getText(),usernm);
        Update_DB.updateMobileNumber(usernm,mobile.getText());
        if(Objects.equals(newPass.getText(), "") || Objects.equals(confirmPass.getText(), "")){
            if(Objects.equals(newPass.getText(), confirmPass.getText())){
                Update_DB.updatePass(usernm,newPass.getText());
            } else{
                //error
            }
        }
    }

    public void setAll(){
        if(usernm != null){
            userName.setText("   @"+usernm);
            email.setText("   "+GetFrom_DB.getEmailByUsername(usernm));
            accountCreatedDate.setText("   "+GetFrom_DB.getUserTimestamp(usernm).toString().substring(0,GetFrom_DB.getUserTimestamp(usernm).toString().indexOf(" ")));
            fullName.setText("   "+GetFrom_DB.getFullNameByUsername(usernm));
            mobile.setText("   "+GetFrom_DB.getMobileByUsername(usernm));
            gender.setText("   "+GetFrom_DB.getGenderByUsername(usernm));
        }
    }

}
