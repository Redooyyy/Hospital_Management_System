package FxmlControllers.Pharmacist;

import FxmlControllers.SettingUI_controller;
import FxmlControllers.SwitchScene;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class PharmacistUserUI_controller implements Initializable {
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
        loadingContent("/UI/Pharmacist/OverviewUI.fxml");
    }

    public void profile(ActionEvent e){}

    public void notifications(ActionEvent e) throws IOException {
        loadingContent("/UI/NotificationUI.fxml");
    }

    public void doctors(ActionEvent e) throws IOException {
        loadingContent("/UI/Pharmacist/SeeStacksUI.fxml");
    }

    public void handOver(ActionEvent e) throws IOException {
        loadingContent("/UI/Pharmacist/HandOverUI.fxml");
    }

    public void addStock() throws IOException {
        loadingContent("/UI/Pharmacist/AddMedicine.fxml");
    }

    public void appointments(ActionEvent e) throws IOException {
        loadingContent("/UI/AppointmentUI.fxml");
    }

    public void settings(ActionEvent e) throws IOException {
        loadingContent("/UI/SettingUI.fxml", usernameLabel.getText());
    }

    public void logout(ActionEvent e) throws IOException {
        SwitchScene switchScene = new SwitchScene();
        switchScene.switchscene(e,"/UI/LoginUI.fxml");
    }

    //content part
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        loadingContent("/UI/Pharmacist/OverviewUI.fxml");
    }


    //loading content

    public <T> void loadingContent(String fxmlPath) {
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
            loader.getController();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void loadingContent(String fxmlPath, String username) throws IOException {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPath));
            AnchorPane pane = loader.load();

            contentPane.getChildren().clear();
            contentPane.getChildren().add(pane);

            AnchorPane.setBottomAnchor(pane, 0.0);
            AnchorPane.setTopAnchor(pane, 0.0);
            AnchorPane.setLeftAnchor(pane, 0.0);
            AnchorPane.setRightAnchor(pane, 0.0);

            // ✅ Get controller and pass username
            Object controller = loader.getController();
            if (controller instanceof SettingUI_controller) {
                ((SettingUI_controller) controller).setUsername(username);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }



}

