package FxmlControllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Circle;

import java.net.URL;
import java.util.ResourceBundle;



public class OverviewUI_controller implements Initializable {
   @FXML
    private Label todaysAppointmentLabel;
   @FXML
    private Label upcomingAppointmentLabel;
   @FXML
    private Label dueLabel;
   @FXML
    private Label entireAmmountLabel;
   @FXML
    private  Label clearedAmmountLabel;
   @FXML
    private Label healthTips1;
   @FXML
    private Label healthTips2;
   @FXML
    private Label healthTips3;
   @FXML
    private Label healthTips4;
   @FXML
    private Label doctor1;
   @FXML
    private Label doctor2;
   @FXML
    private Label doctor3;
   @FXML
    private ImageView doctor1image;
   @FXML
    private ImageView doctor2image;
   @FXML
    private ImageView doctor3image;

//have to add a new table for these in my sql.....I'll finish it soon

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setProfileImage(doctor1image,"/assets/doctormale.jpg");
        setProfileImage(doctor2image,"/assets/doctorfemale.jpg");
        setProfileImage(doctor3image,"/assets/doctormale.jpg");
    }

    public void setProfileImage(ImageView frame, String path){
        Image maleDoctorImage = new Image(path);
        frame.setImage(maleDoctorImage);

        frame.setFitHeight(55);
        frame.setFitWidth(55);
        frame.setPreserveRatio(false);

        Circle clip = new Circle(55/2.0,55/2.0,55/2.0);
        frame.setClip(clip);
    }
}

