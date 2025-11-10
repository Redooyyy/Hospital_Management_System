package FxmlControllers.Admin;

import FxmlControllers.SwitchScene;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AdminUserUI_controller implements Initializable {
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

    public Label getUsernameLabel() {
        return usernameLabel;
    }

    public void overview(ActionEvent e) throws IOException {
        loadingContent("/UI/AdminUI/OverviewUI.fxml");
    }

    public void profile(ActionEvent e){}

    public void notifications(ActionEvent e) throws IOException {
    loadingContent("/UI/NotificationUI.fxml");
    }

    public void doctors(ActionEvent e) throws IOException {
        loadingContent("/UI/DoctorsUI.fxml");
    }

    public void appointments(ActionEvent e) throws IOException {
        loadingContent("/UI/AppointmentUI.fxml");
    }

    public void settings(ActionEvent e) throws IOException {
        loadingContent("/UI/SettingUI.fxml");
    }

    public void logout(ActionEvent e) throws IOException {
        SwitchScene switchScene = new SwitchScene();
        switchScene.switchscene(e,"/UI/LoginUI.fxml");
    }

//content part
@Override
public void initialize(URL location, ResourceBundle resources) {

    Platform.runLater(() -> {
        try {
            passUsername();
        } catch (IOException e) {
            e.printStackTrace();
        }
    });
}

    public void passUsername() throws IOException {
        loadOverviewWithUsername();
    }

//loading content
public <T> T loadingContent(String fxmlPath) {
    try {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPath));
        AnchorPane pane = loader.load();

        contentPane.getChildren().clear();
        contentPane.getChildren().add(pane);

        AnchorPane.setBottomAnchor(pane, 0.0);
        AnchorPane.setTopAnchor(pane, 0.0);
        AnchorPane.setLeftAnchor(pane, 0.0);
        AnchorPane.setRightAnchor(pane, 0.0);

        // Return controller so you can use it outside
        return loader.getController();

    } catch (Exception e) {
        e.printStackTrace();
        return null;
    }
}



    public void loadOverviewWithUsername() throws IOException {
        OverviewUI_controller controller = loadingContent("/UI/AdminUI/OverviewUI.fxml");
        if (controller != null && usernameLabel.getText() != null) {
            controller.setUsername(usernameLabel.getText());
            System.out.println(usernameLabel.getText());
        }
    }




}
