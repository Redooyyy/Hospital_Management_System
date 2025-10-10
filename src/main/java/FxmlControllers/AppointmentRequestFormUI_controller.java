package FxmlControllers;


import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AppointmentRequestFormUI_controller implements Initializable {
    @FXML
    private AnchorPane formPane;
    @FXML
    private TextField dateOfBirth;
    @FXML
    private TextArea address;
    @FXML
    private ChoiceBox<String>bloodGroupChoicebox;

    private final String[] bloodGroup = {"A(+ve)","A(-ve)","B(+ve)","B(-ve)","AB(+ve)","AB(-ve)","O(+ve)","O(-ve)" };

    public void confirmBtn() throws IOException {
        AppointmentUI_controller.appointmentUIController.bydefault();
        AppointmentUI_controller.appointmentUIController.confirmed();

        //add to DB (update request column)
    }

    public void cancelBtn() throws IOException {
        AppointmentUI_controller.appointmentUIController.confirmed();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        dateOfBirth.setStyle("-fx-text-fill: white;"+"-fx-background-color: #2A3A4B;"+"-fx-background-radius:18;");
        bloodGroupChoicebox.getItems().addAll(bloodGroup);
    }
}
