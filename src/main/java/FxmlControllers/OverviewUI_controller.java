package FxmlControllers;

import Database.GetFrom_DB;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Circle;

import java.net.URL;
import java.util.*;


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
   private Label doctor1Sub;
   @FXML
    private Label doctor2;
    @FXML
    private Label doctor2Sub;
   @FXML
    private Label doctor3;
    @FXML
    private Label doctor3Sub;
   @FXML
    private ImageView doctor1image;
   @FXML
    private ImageView doctor2image;
   @FXML
    private ImageView doctor3image;

//have to add a new table for these in my sql.....I'll finish it soon

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setProfileImage();
        setHealthTips();
    }

    public void setProfileImage(){
        clipImage(doctor1image,"/assets/doctormale.jpg");
        clipImage(doctor2image,"/assets/doctorfemale.jpg");
        clipImage(doctor3image,"/assets/doctormale.jpg");
    }

    public void setHealthTips(){
        Random random = new Random();
        Set<Integer>uniq = new HashSet<>();

        while(uniq.size()<4){
            uniq.add((random.nextInt(30)+1));  //it'll give me 4 uniq but random numbers between 1-30
        }
        //HashSet font use indexing that's why convert into array
        Integer[] arr = uniq.toArray(new Integer[0]);

        //  \uD83D\uDC9C heart shape copy it from Google
        healthTips1.setText("  \uD83D\uDC9C  "+GetFrom_DB.getTips(arr[0]));
        healthTips2.setText("  \uD83D\uDC9C  "+GetFrom_DB.getTips(arr[1]));
        healthTips3.setText("  \uD83D\uDC9C  "+GetFrom_DB.getTips(arr[2]));
        healthTips4.setText("  \uD83D\uDC9C  "+GetFrom_DB.getTips(arr[3]));
    }

    //helper functions
    public void clipImage(ImageView frame, String path){
        Image maleDoctorImage = new Image(path);
        frame.setImage(maleDoctorImage);

        frame.setFitHeight(55);
        frame.setFitWidth(55);
        frame.setPreserveRatio(false);

        Circle clip = new Circle(55/2.0,55/2.0,55/2.0);
        frame.setClip(clip);
    }
}

