package FxmlControllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.effect.BlurType;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AppointmentUI_controller implements Initializable {
    @FXML
    private VBox doctorsVbox;
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private AnchorPane contentBox;


    public void setDoctor(String mainMessage, String subMassage, int index) {
        //hiding scroll bar (Another think that I had to search for so long to make ui look good -_-)
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);

        //this for lines are from yt + some research just to stopping from automatic decreasing the height of my notification card while overflow -_-
        scrollPane.setFitToHeight(false);
        scrollPane.setFitToWidth(true);
        doctorsVbox.setPrefHeight(Region.USE_COMPUTED_SIZE);
        doctorsVbox.setPrefWidth(Region.USE_COMPUTED_SIZE);

        //Each Property are tested in scene-builder! after making the ui in scene builder I take note of colors, x y positioning, height and weight etc and implimented here for dynamically creating same notification card when a new notification will appear from database!!
        AnchorPane notificationCard = new AnchorPane();
        notificationCard.setPrefHeight(65);
        notificationCard.setPrefWidth(610);
        notificationCard.setStyle("-fx-background-color:  #2F4050;" + "-fx-background-radius: 18;");

//        notificationCard.setOnMouseClicked(event -> {
//            if(!(event.getTarget() instanceof Button)){
//                selected = (AnchorPane) event.getSource();
//                notificationShow.setText(subMassage);  // I have something in mind finish it later
//                defaultOverview.setText("");
//            }
//        });

        DropShadow shadow = new DropShadow();
        shadow.setBlurType(BlurType.THREE_PASS_BOX);
        shadow.setWidth(12.0);
        shadow.setHeight(11.63);
        shadow.setRadius(5.41);
        shadow.setColor(Color.web("#aeaeae"));

        notificationCard.setEffect(shadow);

        //main Notification
        Label mainNotification = new Label();
        mainNotification.setStyle(
                "-fx-set-fill: white;" + "-fx-font-size: 19;" + "-fx-font-family: FreeSans;"
        );
        mainNotification.setTextFill(Color.WHITE);
        mainNotification.setPrefWidth(318);
        mainNotification.prefHeight(31);
        mainNotification.setLayoutX(95);
        mainNotification.setLayoutY(9);
        mainNotification.setText(mainMessage);

        Label speciality = new Label();
        speciality.setStyle(
                "-fx-font-size: 14;" + "-fx-font-family: FreeSans;"
        );
        speciality.setTextFill(Color.web("#c9c9c9"));
        speciality.setPrefWidth(318);
        speciality.prefHeight(31);
        speciality.setLayoutX(95);
        speciality.setLayoutY(39);
        speciality.setText(subMassage);

        //set profile pic
        ImageView imageView = new ImageView();
        imageView.setFitWidth(50);
        imageView.setFitHeight(50);
        imageView.setLayoutX(14);
        imageView.setLayoutY(9);
        clipImage(imageView, "/assets/doctormale.jpg", 50);

        Label timeOfNotification = new Label();
        timeOfNotification.setTextFill(Color.web("#8a7f7f"));
        timeOfNotification.setStyle(
                "-fx-set-fill: #8a7f7f;" + "-fx-font-size: 17;" + "-fx-font-family: FreeSans;" + "-fx-font-weight: bold"
        );
        timeOfNotification.setPrefWidth(98);
        timeOfNotification.prefHeight(34);
        timeOfNotification.setLayoutY(24);
        timeOfNotification.setLayoutX(375);
        timeOfNotification.setText("✮ 4.5"); //for testing

        //clear Button
        Button view = new Button();
        view.setText("Appointment");
        view.setStyle("-fx-background-color: green;" + "-fx-background-radius: 18;" + "-fx-font-size: 14;" + "-fx-font-family: FreeSans;" + "-fx-text-fill: white;" + "-fx-font-weight: bold");
        view.setPrefHeight(37);
        view.setPrefWidth(120);
        view.setLayoutX(455);
        view.setLayoutY(14);


        //adding all element to ->anchorPane ->Vbox
        notificationCard.getChildren().addAll(imageView, mainNotification, speciality, timeOfNotification, view);
        doctorsVbox.getChildren().add(index, notificationCard);
        VBox.setMargin(notificationCard, new Insets(4, 10, 13, 10));

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setDoctor("Dr. Fayed Hasan", "Cardiology", 0);


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


    public void confirmed() throws IOException {
        loadingContent("/UI/ConfirmAppointmentListUI.fxml");
    }

    public void requested() throws IOException {
        loadingContent("/UI/RequestedAppointmentUI.fxml");
    }

    public void loadingContent(String fxmlPath) throws IOException {
        try {
            AnchorPane pane = FXMLLoader.load(getClass().getResource(fxmlPath));
            contentBox.getChildren().clear();
            contentBox.getChildren().add(pane);

            AnchorPane.setBottomAnchor(pane, 0.0);
            AnchorPane.setTopAnchor(pane, 0.0);
            AnchorPane.setLeftAnchor(pane, 0.0);
            AnchorPane.setRightAnchor(pane, 0.0);
        } catch (Exception e1) {
            e1.printStackTrace();
        }

    }
}
