package FxmlControllers;

import Database.GetFrom_DB;
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

    public void overview(ActionEvent e) throws IOException {
        loadingContent("/UI/OverviewUI.fxml");
    }

    public void profile(ActionEvent e){}

    public void notifications(ActionEvent e) throws IOException {
    notify("/UI/NotificationUI.fxml",usernameLabel.getText());
    }

    public void doctors(ActionEvent e) throws IOException {
        loadingContent("/UI/DoctorsUI.fxml");
    }

    public void appointments(ActionEvent e) throws IOException {
        appoint("/UI/AppointmentUI.fxml",usernameLabel.getText());
    }

    public void settings(ActionEvent e) throws IOException {
        loadingContent("/UI/SettingUI.fxml",usernameLabel.getText());
    }

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

    private void loadingContent(String fxmlPath, String username) throws IOException {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPath));
            AnchorPane pane = loader.load();
            SettingUI_controller controller = loader.getController();
            controller.setUsername(username);
            controller.setAll();

            contentPane.getChildren().clear();
            contentPane.getChildren().add(pane);

            AnchorPane.setBottomAnchor(pane, 0.0);
            AnchorPane.setTopAnchor(pane, 0.0);
            AnchorPane.setLeftAnchor(pane, 0.0);
            AnchorPane.setRightAnchor(pane, 0.0);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void notify(String fxmlPath, String username) throws IOException {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPath));
            AnchorPane pane = loader.load();
            NotificationUI_controller controller = loader.getController();
            controller.setUserID(GetFrom_DB.getUserID(username));
            controller.loadData();

            contentPane.getChildren().clear();
            contentPane.getChildren().add(pane);

            AnchorPane.setBottomAnchor(pane, 0.0);
            AnchorPane.setTopAnchor(pane, 0.0);
            AnchorPane.setLeftAnchor(pane, 0.0);
            AnchorPane.setRightAnchor(pane, 0.0);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private void appoint(String fxmlPath, String username) throws IOException {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPath));
            AnchorPane pane = loader.load();
            AppointmentUI_controller controller = loader.getController();
            controller.setUsername(username);

            contentPane.getChildren().clear();
            contentPane.getChildren().add(pane);

            AnchorPane.setBottomAnchor(pane, 0.0);
            AnchorPane.setTopAnchor(pane, 0.0);
            AnchorPane.setLeftAnchor(pane, 0.0);
            AnchorPane.setRightAnchor(pane, 0.0);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
