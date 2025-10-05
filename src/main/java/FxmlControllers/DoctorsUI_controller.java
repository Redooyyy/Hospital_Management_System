package FxmlControllers;

import javafx.fxml.FXML;
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

import java.net.URL;
import java.util.ResourceBundle;

public class DoctorsUI_controller implements Initializable {
    @FXML
    private VBox doctorsVbox;
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private Label noNotifications;
    @FXML
    private Label defaultOverview;
    @FXML
    private Label notificationShow;
    @FXML
    private Label overviewName;
    @FXML
    private Label overviewAge;
    @FXML
    private Label overviewGender;
    @FXML
    private Label overviewSpeciality;
    @FXML
    private Label overviewExperience;
    @FXML
    private Label overviewContact;
    @FXML
    private VBox overviewField;
    @FXML
    private ImageView overViewImage;
    @FXML
    private Button rate;
    @FXML
    private Hyperlink askAppointment;
    private AnchorPane selected;


    public void setDoctor(String mainMessage, String subMassage,int index){
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
        notificationCard.setPrefWidth(735);
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
                "-fx-set-fill: white;"+"-fx-font-size: 19;"+"-fx-font-family: FreeSans;"
        );
        mainNotification.setTextFill(Color.WHITE);
        mainNotification.setPrefWidth(420);
        mainNotification.prefHeight(27);
        mainNotification.setLayoutX(80);
        mainNotification.setLayoutY(22);
        mainNotification.setText(mainMessage);

        //set profile pic
        ImageView imageView = new ImageView();
        imageView.setFitWidth(50);
        imageView.setFitHeight(50);
        imageView.setLayoutX(10);
        imageView.setLayoutY(7);
        clipImage(imageView,"/assets/doctormale.jpg",50);

        Label timeOfNotification = new Label();
        timeOfNotification.setTextFill(Color.web("#8a7f7f"));
        timeOfNotification.setStyle(
                "-fx-set-fill: #8a7f7f;"+"-fx-font-size: 17;"+"-fx-font-family: FreeSans;"+"-fx-font-weight: bold"
        );
        timeOfNotification.setPrefWidth(98);
        timeOfNotification.prefHeight(34);
        timeOfNotification.setLayoutY(24);
        timeOfNotification.setLayoutX(495);
        timeOfNotification.setText("✮ 4.5"); //for testing

        //clear Button
        Button view = new Button();
        view.setText("View");
        view.setStyle("-fx-background-color: green;"+"-fx-background-radius: 18;"+"-fx-font-size: 14;"+"-fx-font-family: FreeSans;"+"-fx-text-fill: white;"+"-fx-font-weight: bold");
        view.setPrefHeight(30);
        view.setPrefWidth(60);
        view.setLayoutX(633);
        view.setLayoutY(18);
        view.setOnAction(event -> viewDetail());



        //adding all element to ->anchorPane ->Vbox
        notificationCard.getChildren().addAll(imageView,mainNotification,timeOfNotification,view);
        doctorsVbox.getChildren().add(index,notificationCard);
        VBox.setMargin(notificationCard,new Insets(4,10,13,10));

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setDoctor("Dr. Fayed Hasan","",0);
        defauilt();
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

    public void viewDetail(){//for testing
        active();
        clipImage(overViewImage,"/assets/doctormale.jpg",100); //based on gender
        //for testing
        overviewName.setText(":     "+"Fayed Hasan Nirob");
        overviewAge.setText(":     "+"22");
        overviewContact.setText(":     "+"01311720456");
        overviewExperience.setText(":     "+"2 yrs");
        overviewGender.setText(":     "+"Male");
        overviewSpeciality.setText(":     "+"Cardiology");
    }

    public void defauilt(){
        defaultOverview.setText("no profile is selected");
        overviewField.setVisible(false);
        rate.setVisible(false);
        askAppointment.setVisible(false);
    }

    public void active(){
        defaultOverview.setText("");
        overviewField.setVisible(true);
        rate.setVisible(true);
        askAppointment.setVisible(true);
    }
}
