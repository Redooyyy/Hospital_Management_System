package FxmlControllers.Doctor;

import Database.*;
import javafx.event.ActionEvent;
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
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

import java.net.URL;
import java.sql.*;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ResourceBundle;

public class DoctorAppointment_controller implements Initializable {
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
    private String username;

    private final String cancelTitle = "Canceled Appointment request";
    private final String cancelTxt = " ,\n" +
            "\n" +
            "I regret to inform you that I must cancel your scheduled appointment.  \n" +
            "The cancellation is due to unforeseen circumstances that require my immediate attention.  \n" +
            "\n" +
            "I sincerely apologize for any inconvenience this may cause. Please contact the clinic to arrange a new appointment at a time that is convenient for you.  \n" +
            "\n" +
            "Dr.";

    private final String acceptTitile = "Accepted Request";
    private final String acceptTxt = " ,\n" +
            "\n" +
            "I am pleased to confirm your appointment request.  \n" +
            "Your appointment has been scheduled for tomorrow at [Clinic Address].  \n" +
            "\n" +
            "We look forward to seeing you then. Hope your illness will be cured \n" +
            "\n" +
            "Dr. " ;

    public void setUsername(String username) {
        this.username = username;
    }

    public void setDoctor(String mainMessage, int rqID, int index,String patient){
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

//        Label timeOfNotification = new Label();
//        timeOfNotification.setTextFill(Color.web("#8a7f7f"));
//        timeOfNotification.setStyle(
//                "-fx-set-fill: #8a7f7f;"+"-fx-font-size: 17;"+"-fx-font-family: FreeSans;"+"-fx-font-weight: bold"
//        );
//        timeOfNotification.setPrefWidth(98);
//        timeOfNotification.prefHeight(34);
//        timeOfNotification.setLayoutY(24);
//        timeOfNotification.setLayoutX(495);
//        timeOfNotification.setText("✮ 4.5"); //for testing

        Button accept = new Button();
        accept.setText("Accept");
        accept.setStyle("-fx-background-color: green;"+"-fx-background-radius: 18;"+"-fx-font-size: 14;"+"-fx-font-family: FreeSans;"+"-fx-text-fill: white;"+"-fx-font-weight: bold");
        accept.setPrefHeight(30);
        accept.setPrefWidth(85);
        accept.setLayoutX(400);
        accept.setLayoutY(18);
        accept.setOnAction(event -> {
            System.out.println(username);
            //accept request
            NotificationAdd.addSallary(GetFrom_DB.getUserID(patient),(acceptTitile),("Dear "+GetFrom_DB.getFullNameByUsername(patient)+acceptTxt)+GetFrom_DB.getFullNameByUsername(username));
            Update_DB.acceptedAppointment(GetFrom_DB.getUserID(username),rqID);
            doctorsVbox.getChildren().remove(notificationCard);
        });

        Button cancel = new Button();
        cancel.setText("Cancel");
        cancel.setStyle("-fx-background-color: red;"+"-fx-background-radius: 18;"+"-fx-font-size: 14;"+"-fx-font-family: FreeSans;"+"-fx-text-fill: white;"+"-fx-font-weight: bold");
        cancel.setPrefHeight(30);
        cancel.setPrefWidth(85);
        cancel.setLayoutX(500);
        cancel.setLayoutY(18);
        cancel.setOnAction(event -> {
            //cancel request
            doctorsVbox.getChildren().remove(notificationCard);
            Update_DB.deleteAppointment(rqID);
            NotificationAdd.addSallary(GetFrom_DB.getUserID(patient),(cancelTitle),("Dear "+GetFrom_DB.getFullNameByUsername(patient)+cancelTxt)+GetFrom_DB.getFullNameByUsername(username));
        });

        //clear Button
        Button view = new Button();
        view.setText("View");
        view.setStyle("-fx-background-color: green;"+"-fx-background-radius: 18;"+"-fx-font-size: 14;"+"-fx-font-family: FreeSans;"+"-fx-text-fill: white;"+"-fx-font-weight: bold");
        view.setPrefHeight(30);
        view.setPrefWidth(60);
        view.setLayoutX(633);
        view.setLayoutY(18);
        view.setOnAction(event -> viewDetail(username));



        //adding all element to ->anchorPane ->Vbox
        notificationCard.getChildren().addAll(imageView,mainNotification,accept,cancel,view);
        doctorsVbox.getChildren().add(index,notificationCard);
        VBox.setMargin(notificationCard,new Insets(4,10,13,10));

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
       // setDoctor("Mim akter",2,0,"mimakter");
        defauilt();
        //loadData();
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

    public void viewDetail(String username){//for testing
        active();
        clipImage(overViewImage,"/assets/doctormale.jpg",100); //based on gender
        //for testing
        overviewName.setText(":     "+ GetFrom_DB.getFullNameByUsername(username));
        overviewAge.setText(":     "+GetFrom_DB.getEmailByUsername(username));
        overviewContact.setText(":     "+GetFrom_DB.getMobileByUsername(username));
        overviewGender.setText(":     "+GetFrom_DB.getGenderByUsername(username));
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


    public void loadData() {
        String sql = "SELECT doctor_id, patient_id, request_id FROM appointment_request";
        System.out.println("Hello");
        try (Connection connection = DB_connect.getConnect();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet rs = statement.executeQuery()) {
            int indx = 0;
            System.out.println(GetFrom_DB.getUserID(username));
            while (rs.next()) {
                if(rs.getInt("doctor_id") == GetFrom_DB.getUserID(username)){
                    System.out.println(GetFrom_DB.getUserID(username));
                    String patientUserName = GetFrom_DB.getUserNameByUserID(rs.getInt("patient_id"));
                    System.out.println(patientUserName);
                    setDoctor(GetFrom_DB.getFullNameByUsername(patientUserName),rs.getInt("request_id"),indx,patientUserName);
                    System.out.println(rs.getString("full_name"));
                    //see request list
                    indx++;
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
