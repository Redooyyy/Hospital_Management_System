package FxmlControllers;

import Database.DB_connect;
import Roles.UserRole;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.effect.BlurType;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

import java.net.URL;
import java.sql.*;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ResourceBundle;

public class NotificationUI_controller implements Initializable {
    @FXML
    private VBox notificationVbox;
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private Button clearBtn;
    @FXML
    private Label noNotifications;
    @FXML
    private Label defaultNotificationShow;
    @FXML
    private Label notificationShow;
    private AnchorPane selected;
    private LocalDateTime now = LocalDateTime.now();

    private int userID;

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public void setNotification(String mainMessage, String subMassage, int index,String ago){
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
        notificationCard.setPrefHeight(59);
        notificationCard.setPrefWidth(735);
        notificationCard.setStyle("-fx-background-color:  #2F4050;" + "-fx-background-radius: 18;");

        notificationCard.setOnMouseClicked(event -> {
            if(!(event.getTarget() instanceof Button)){
                selected = (AnchorPane) event.getSource();
                notificationShow.setText(subMassage);  // I have something in mind finish it later
                defaultNotificationShow.setText("");
            }
        });

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
                "-fx-set-fill: white;"+"-fx-font-size: 17;"+"-fx-font-family: FreeSans;"
        );
        mainNotification.setTextFill(Color.WHITE);
        mainNotification.setPrefWidth(420);
        mainNotification.prefHeight(27);
        mainNotification.setLayoutX(21);
        mainNotification.setLayoutY(9);
        mainNotification.setText("➤   "+mainMessage);

        //sub notification
        Label subNotification = new Label();
        subNotification.setTextFill(Color.web("#8a7f7f"));
        subNotification.setStyle(
                "-fx-set-fill: #8a7f7f;"+"-fx-font-size: 14;"+"-fx-font-family: FreeSans;"
        );
        subNotification.setPrefWidth(420);
        subNotification.prefHeight(27);
        subNotification.setLayoutY(32);
        subNotification.setLayoutX(22);
        subNotification.setText("        "+subMassage);

        Label timeOfNotification = new Label();
        timeOfNotification.setTextFill(Color.web("#8a7f7f"));
        timeOfNotification.setStyle(
                "-fx-set-fill: #8a7f7f;"+"-fx-font-size: 15;"+"-fx-font-family: FreeSans;"
        );
        timeOfNotification.setPrefWidth(98);
        timeOfNotification.prefHeight(34);
        timeOfNotification.setLayoutY(22);
        timeOfNotification.setLayoutX(495);
        timeOfNotification.setText(ago+" ago"); //for testing

        //clear Button
        Button clear = new Button();
        clear.setText("Clear");
        clear.setStyle("-fx-background-color: red;"+"-fx-background-radius: 18;"+"-fx-font-size: 14;"+"-fx-font-family: FreeSans;"+"-fx-text-fill: white;"+"-fx-font-weight: bold");
        clear.setPrefHeight(27);
        clear.setPrefWidth(60);
        clear.setLayoutX(633);
        clear.setLayoutY(16);
        clear.setOnAction(event -> {
            notificationVbox.getChildren().remove(notificationCard);
            clearButtonVisible();
            if(ifSelectedIsDeleted(event)){
                defaultNotificationShow.setText("no notification is selected");
                notificationShow.setText("");
            } else {
                defaultNotificationShow.setText("");
            }
        });

        //adding all element to ->anchorPane ->Vbox
        notificationCard.getChildren().addAll(mainNotification,subNotification,timeOfNotification,clear);
        notificationVbox.getChildren().add(index,notificationCard);
        VBox.setMargin(notificationCard,new Insets(4,10,13,10));

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {


    }

    public void loadData() {
        String sql = "SELECT notification_title, notification_msg, user_id, notification_time FROM notification";

        try (Connection connection = DB_connect.getConnect();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet rs = statement.executeQuery()) {

            int indx = 0;

            while (rs.next()) {
                if(rs.getInt("user_id") == userID){
                    Timestamp timestamp = rs.getTimestamp("notification_time");
                    LocalDateTime notifyTime = timestamp.toLocalDateTime();
                    Duration duration = Duration.between(notifyTime,now);
                    long hours = duration.toHours();
                    long min = duration.toMinutes()%60;
                    String ago;
                    if(hours == 0) ago = ""+min+" min";
                    else ago = ""+hours+" hour";

                    setNotification(rs.getString("notification_title"),rs.getString("notification_msg"),indx,ago);
                    indx++;
                }
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void selectedCard(MouseEvent event){
        AnchorPane selcted = new AnchorPane();
        int indx = notificationVbox.getChildren().indexOf(selcted);
        notificationShow.setText(subtext(indx));
    }

    public void clearAll(){
        notificationShow.setText("");
        defaultNotificationShow.setText("no notification is selected");
        notificationVbox.getChildren().clear();
        clearButtonVisible();
    }

    //clear button visibility
    public void clearButtonVisible(){
        if(notificationVbox.getChildren().isEmpty()){
            clearBtn.setVisible(false);
            noNotifications.setText("no notifications");
            defaultNotificationShow.setText("no notification is selected");
        } else {
            clearBtn.setVisible(true);
        }
    }

    public boolean ifSelectedIsDeleted(ActionEvent event){
        Button clicked = (Button)event.getSource();
        AnchorPane pane = (AnchorPane) clicked.getParent();
        int indx = notificationVbox.getChildren().indexOf(pane);
        int indx2 = notificationVbox.getChildren().indexOf(selected);

        return indx == indx2;

    }

    //pass to the overview(only the latest 3 notifications)
    public String overViewPass(int indx){
       try{
           AnchorPane card = (AnchorPane) notificationVbox.getChildren().get(indx);
           Label mainText = (Label)card.getChildren().getFirst();
           return mainText.getText();
       } catch (Exception e){
           return null;
       }
    }
    public String subtext(int indx){
        AnchorPane card = (AnchorPane) notificationVbox.getChildren().get(indx);
        Label mainText = (Label)card.getChildren().get(2);
        return mainText.getText();
    }

    public String first(){
        return overViewPass(0) != null? overViewPass(0):null;
    }
    public String second(){
        return overViewPass(1) != null? overViewPass(1):null;
    }
    public String third(){
        return overViewPass(2) != null? overViewPass(2):null;
    }

}
