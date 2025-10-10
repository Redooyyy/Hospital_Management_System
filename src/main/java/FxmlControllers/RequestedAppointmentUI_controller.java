package FxmlControllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.BlurType;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

import java.net.URL;
import java.util.ResourceBundle;

public class RequestedAppointmentUI_controller implements Initializable {
    @FXML
    private VBox confirmedVbox;

    public void confirmedDoctors(int indx,String name,String time){
        confirmedVbox.setPrefHeight(Region.USE_COMPUTED_SIZE);
        confirmedVbox.setPrefWidth(Region.USE_COMPUTED_SIZE);

        AnchorPane doctorCard = new AnchorPane();
        doctorCard.setPrefWidth(118);
        doctorCard.setPrefHeight(60);
        doctorCard.setStyle("-fx-background-color: #2A3A4B;" + "-fx-background-radius: 18");

        DropShadow shadow = new DropShadow();
        shadow.setBlurType(BlurType.THREE_PASS_BOX);
        shadow.setWidth(12.0);
        shadow.setHeight(11.63);
        shadow.setRadius(5.41);
        shadow.setColor(Color.web("#aeaeae"));

        doctorCard.setEffect(shadow);

        Label doctorName = new Label();
        doctorName.setStyle("-fx-set-fill: white;" + "-fx-font-size: 18;" + "-fx-font-family: FreeSans;");

        doctorName.setTextFill(Color.WHITE);
        doctorName.setPrefWidth(220);
        doctorName.prefHeight(50);
        doctorName.setLayoutX(90);
        doctorName.setLayoutY(19);
        //name
        doctorName.setText(name);

        Button cancelRequest = new Button();
        cancelRequest.setText("Cancel");
        cancelRequest.setTextFill(Color.WHITE);
        cancelRequest.setStyle(
                "-fx-font-size: 17;" + "-fx-font-family: FreeSans;"+ "-fx-background-radius: 18;" + "-fx-background-color: red;"+ "-fx-font-weight: bold"

        );
        cancelRequest.setPrefWidth(95);
        cancelRequest.setPrefHeight(34);
        cancelRequest.setLayoutX(400);
        cancelRequest.setLayoutY(13);

//        Label timeDate = new Label();
//        timeDate.setStyle("-fx-font-size: 16;" + "-fx-font-family: FreeSans;");
//        timeDate.setTextFill(Color.web("#bfbebe"));
//        timeDate.setPrefWidth(118);
//        timeDate.prefHeight(50);
//        timeDate.setLayoutX(380);
//        timeDate.setLayoutY(19);
//        //date
//        timeDate.setText(time);

        ImageView imageView = new ImageView();
        imageView.setFitWidth(50);
        imageView.setFitHeight(50);
        imageView.setLayoutX(14);
        imageView.setLayoutY(7);
        clipImage(imageView, "/assets/doctormale.jpg", 50);

        doctorCard.getChildren().addAll(imageView,doctorName,cancelRequest);
        confirmedVbox.getChildren().add(indx, doctorCard);
        VBox.setMargin(doctorCard,new Insets(10));
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        confirmedDoctors(0,"Dr. Bushra","12 sept 12:00pm");
        confirmedDoctors(1,"Dr. Mehzabin Akter Bushra","13 sept 12:00pm");
        confirmedDoctors(2,"Dr. Bushra Akter","17 oct 12:00pm");

    }



    public void clipImage(ImageView frame, String path, int x) {
        Image maleDoctorImage = new Image(path);
        frame.setImage(maleDoctorImage);

        frame.setFitHeight(x);
        frame.setFitWidth(x);
        frame.setPreserveRatio(false);

        Circle clip = new Circle(x / 2.0, x / 2.0, x / 2.0);
        frame.setClip(clip);
    }

}
