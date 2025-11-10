package FxmlControllers.Admin;

import Core_logics.LoginLogic;
import Database.DB_connect;
import Database.GetFrom_DB;
import FxmlControllers.SwitchScene;
import FxmlControllers.UserUI_controller;
import Roles.Admin;
import Roles.UserRole;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
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
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;

public class RoleChnangeUI_controller implements Initializable {
    @FXML
    private VBox notificationVbox;
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private Button clearBtn;
    @FXML
    private Label changed;
    @FXML
    private Label errorText;
    @FXML
    private Label notificationShow;
    @FXML
    private AnchorPane passConfFeild;
    @FXML
    private PasswordField continuePass;
    private String userName;
    private String role;
    private String selectedTargetUser;
    private UserRole userRole;


    public void setRole(String role) {
        this.role = role;

        if("admin".equals(role)) userRole = UserRole.ADMIN;
        else if("doctor".equals(role)) userRole = UserRole.DOCTOR;
        else if("receptionist".equals(role)) userRole = UserRole.RECEPTIONIST;
        else if("pharmacist".equals(role)) userRole = UserRole.PHARMACIST;
    }


    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setNotification(String mainMessage, String subMassage, int index, String usrnm){
        //hiding scroll bar (Another think that I had to search for so long to make ui look good -_-)
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);

        //this for lines are from yt + some research just to stopping from automatic decreasing the height of my notification card while overflow -_-
        scrollPane.setFitToHeight(false);
        scrollPane.setFitToWidth(true);
        notificationVbox.setPrefHeight(Region.USE_COMPUTED_SIZE);
        notificationVbox.setPrefWidth(Region.USE_COMPUTED_SIZE);

        //Each Property are tested in scene-builder! after making the ui in scene builder I take note of colors, x y positioning, height and weight etc and implimented here for dynamically creating same notification card when a new notification will appear from database!!
        AnchorPane notificationCard = new AnchorPane();
        notificationCard.setPrefHeight(65);
        notificationCard.setPrefWidth(735);
        notificationCard.setStyle("-fx-background-color:  #2F4050;" + "-fx-background-radius: 18;");

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
        timeOfNotification.setLayoutX(380);
        timeOfNotification.setText(subMassage); //for testing


        Label inviID = new Label();
        inviID.setId("inviID");
        inviID.setTextFill(Color.web("#8a7f7f"));
        inviID.setStyle(
                "-fx-set-fill: #8a7f7f;"+"-fx-font-size: 17;"+"-fx-font-family: FreeSans;"+"-fx-font-weight: bold"
        );
        inviID.setPrefWidth(98);
        inviID.prefHeight(34);
        inviID.setLayoutY(24);
        inviID.setLayoutX(380);
        inviID.setText(usrnm); //for testing
        inviID.setVisible(false);



        //clear Button
        Button clear = new Button();
        clear.setText("Set as "+role);
        clear.setStyle("-fx-background-color: green;"+"-fx-background-radius: 18;"+"-fx-font-size: 14;"+"-fx-font-family: FreeSans;"+"-fx-text-fill: white;"+"-fx-font-weight: bold");
        clear.setPrefHeight(27);
        clear.setPrefWidth(120);
        clear.setLayoutX(585);
        clear.setLayoutY(18);
        clear.setOnAction(event -> {
            selectedTargetUser = usrnm;
            passConfFeild.setVisible(true);
        });

        //adding all element to ->anchorPane ->Vbox
        notificationCard.getChildren().addAll(imageView,mainNotification,timeOfNotification,clear,inviID);
        notificationVbox.getChildren().add(index,notificationCard);
        VBox.setMargin(notificationCard,new Insets(4,10,13,10));

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {


        passConfFeild.setVisible(false);

        //showing all users from db
        Platform.runLater(()->{
            String query = "SELECT * FROM users";
            try(Connection connection = DB_connect.getConnect(); PreparedStatement statement = connection.prepareStatement(query)) {

                try(ResultSet result = statement.executeQuery()) {
                    int indx = 0;
                    while (result.next()){
                        setNotification(result.getString("full_name"), UserRole.roleName(result.getInt("role_id")), indx,result.getString("username"));
                        indx++;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        });

    }

    public void clearAll(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/UI/AdminUI/AdminUserUI.fxml"));
        Parent root = loader.load();
        AdminUserUI_controller controller = loader.getController();
        controller.setFullName(GetFrom_DB.getFullNameByUsername(userName));
        controller.setUsername(userName);
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void confirm(ActionEvent event){
        if(selectedTargetUser == null){
            errorText.setText("No user selected!");
            return;
        }

        try {
            if(allOK()){
                Admin.changeRole(userName, selectedTargetUser, userRole);
                changed.setVisible(true);
                changed.setText("Successfully Changed");
                changed.setStyle("-fx-background-color:  #AFF2AF;");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }



    public boolean allOK() throws IOException {
        LoginLogic loginLogic = new LoginLogic();
        if(loginLogic.loggedInUsername(userName, continuePass.getText())){
            return true;
        } else {
            //wrong pass
            errorText.setText("Wrong password");
            errorText.setTextFill(Color.RED);
            return false;
        }
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
