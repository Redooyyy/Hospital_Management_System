package FxmlControllers.Admin;

import Database.DB_connect;
import Database.GetFrom_DB;
import Database.SallaryAdd_DB;
import Database.Update_DB;
import Roles.Pharmacy.Medicine;
import Roles.UserRole;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.*;
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
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class StaffsUI_controller implements Initializable {
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
    private TextField sallary;
    private AnchorPane selected;
    private String selectedUser;
    @FXML
    private Label sallaryLabel;


    public void setDoctor(String mainMessage, String subMassage,int index,String user){
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
        timeOfNotification.setText(subMassage); //for testing

        //clear Button
        Button view = new Button();
        view.setText("View");
        view.setStyle("-fx-background-color: green;"+"-fx-background-radius: 18;"+"-fx-font-size: 14;"+"-fx-font-family: FreeSans;"+"-fx-text-fill: white;"+"-fx-font-weight: bold");
        view.setPrefHeight(30);
        view.setPrefWidth(60);
        view.setLayoutX(633);
        view.setLayoutY(18);
        view.setOnAction(event -> viewDetail(user));



        //adding all element to ->anchorPane ->Vbox
        notificationCard.getChildren().addAll(imageView,mainNotification,timeOfNotification,view);
        doctorsVbox.getChildren().add(index,notificationCard);
        VBox.setMargin(notificationCard,new Insets(4,10,13,10));

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
//        setDoctor("Dr. Fayed Hasan","",0);
        loadData();
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

    public void viewDetail(String usr){//for testing
        active();
        selectedUser = usr;
        clipImage(overViewImage,"/assets/doctormale.jpg",100); //based on gender
        //for testing
        overviewName.setText(":     "+ GetFrom_DB.getFullNameByUsername(usr));
        overviewAge.setText(":     "+GetFrom_DB.getEmailByUsername(usr));
        overviewContact.setText(":     "+GetFrom_DB.getMobileByUsername(usr));
        overviewExperience.setText(":     "+GetFrom_DB.getSallary(GetFrom_DB.getUserID(usr)));
        overviewGender.setText(":     "+GetFrom_DB.getGenderByUsername(usr));

    }

    public void defauilt(){
        defaultOverview.setText("no profile is selected");
        overviewField.setVisible(false);
        rate.setVisible(false);
    }

    public void active(){
        defaultOverview.setText("");
        overviewField.setVisible(true);
        sallaryLabel.setVisible(true);
        sallary.setVisible(true);
        rate.setVisible(true);

    }


    public void updateSallary(){
        double x = GetFrom_DB.getSallary(GetFrom_DB.getUserID(selectedUser));
        if(x == 0){
            SallaryAdd_DB.addSallary(GetFrom_DB.getUserID(selectedUser),Double.parseDouble(sallary.getText()));
            overviewExperience.setText(":     "+sallary.getText());
        } else {
            Update_DB.updateSallary(Double.parseDouble(sallary.getText()),selectedUser);
            overviewExperience.setText(":     "+sallary.getText());
        }
        sallary.setText("");
    }



    private void loadData() {
        String sql = "SELECT full_name, username, role_id FROM users";

        try (Connection connection = DB_connect.getConnect();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet rs = statement.executeQuery()) {

            int indx = 0;

            while (rs.next()) {
                if(rs.getInt("role_id") !=2){
                    setDoctor(rs.getString("full_name"), UserRole.roleName(rs.getInt("role_id")), indx,rs.getString("username"));
                    indx++;
                }
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}

