package FxmlControllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class SettingUI_controller implements Initializable {
    @FXML
    private TextField fullName;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        fullName.setStyle(
                "-fx-text-fill: white;"
        );
    }
}
