package FxmlControllers;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

public class AppointmentRequestFormUI_controller implements Initializable {
    @FXML
    private AnchorPane formPane;
    @FXML
    private Label dateOfBirth;
    @FXML
    private TextArea address;
    @FXML
    private ChoiceBox<String>bloodGroupChoicebox;
    @FXML
    private DatePicker datepicker;

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

    public void formFillUp(ActionEvent event){
        LocalDate myDate = datepicker.getValue();
        String date = myDate.format(DateTimeFormatter.ofPattern("dd-MM-yyy"));
        dateOfBirth.setText(date);
    }

}
