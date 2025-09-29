package FxmlControllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.io.IOException;

public class UserUI_controller {
    @FXML
    private Label fullNameLabel;
    @FXML
    private Label usernameLabel;


    public void setFullName(String fullName) {
        this.fullNameLabel.setText(fullName);
    }

    public void setUsername(String username) {
        this.usernameLabel.setText(username);
    }

    public void overview(ActionEvent e){}

    public void profile(ActionEvent e){}

    public void notifications(ActionEvent e){}

    public void doctors(ActionEvent e){}

    public void appointments(ActionEvent e){}

    public void settings(ActionEvent e){}

    public void logout(ActionEvent e) throws IOException {
        SwitchScene switchScene = new SwitchScene();
        switchScene.switchscene(e,"/UI/LoginUI.fxml");
    }
}
