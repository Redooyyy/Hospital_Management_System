package FxmlControllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class UserUI_controller implements Initializable {
    @FXML
    private AnchorPane contentPane;
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

//content part
@Override
public void initialize(URL location, ResourceBundle resources) {
    try {
        loadingContent("/UI/OverviewUI.fxml");
    } catch (IOException e) {
        throw new RuntimeException(e);
    }
}


    //loading content
    private void loadingContent (String fxmlPath) throws IOException {
        try {
            AnchorPane pane = FXMLLoader.load(getClass().getResource(fxmlPath));
            contentPane.getChildren().clear();
            contentPane.getChildren().add(pane);

            AnchorPane.setBottomAnchor(pane,0.0);
            AnchorPane.setTopAnchor(pane,0.0);
            AnchorPane.setLeftAnchor(pane,0.0);
            AnchorPane.setRightAnchor(pane,0.0);
        } catch (Exception e){
            e.printStackTrace();
        }
    }


}
