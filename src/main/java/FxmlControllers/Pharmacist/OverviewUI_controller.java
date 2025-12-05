package FxmlControllers.Pharmacist;

import Database.GetFrom_DB;
import FxmlControllers.NotificationUI_controller;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Circle;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class OverviewUI_controller implements Initializable {
    @FXML
   private Label avarageSell;
    @FXML
    private Label totalSell;
    @FXML
    private Label todaysSell;
    @FXML
    AnchorPane contentPane;
    private String username;
    private String fusername;

    public void setUsername(String username) {
        this.username = username;
    }

    //have to add a new table for these in my sql.....I'll finish it soon

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        LocalDate date = LocalDate.now();
        todaysSell.setText(""+GetFrom_DB.getMedRev(date));
    }


    public void setProfileImage(){

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

    public void addPharma(ActionEvent event) throws IOException {
        loadingContent("/UI/Pharmacist/AddMedicine.fxml");
    }

    public void addRecept(ActionEvent event) throws IOException {
        loadingContent("/UI/Pharmacist/SeeStacksUI.fxml");
    }

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

