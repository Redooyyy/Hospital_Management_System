package FxmlControllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.effect.BlurType;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

import java.net.URL;
import java.util.ResourceBundle;

public class NotificationUI_controller implements Initializable {
    @FXML
    private VBox notificationVbox;
    @FXML
    ScrollPane scrollPane;

    public void setNotification(String mainMessage, String subMassage,int index){
        //hiding scroll bar (Another think that I had to search for so long to make ui look good -_-)
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);

        AnchorPane notificationCard = new AnchorPane();
        notificationCard.setPrefHeight(59);
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
                "-fx-set-fill: white;"+"-fx-font-size: 17;"+"-fx-font-family: FreeSans;"
        );
        mainNotification.setTextFill(Color.WHITE);
        mainNotification.setPrefWidth(551);
        mainNotification.prefHeight(27);
        mainNotification.setLayoutX(21);
        mainNotification.setLayoutY(9);
        mainNotification.setText(mainMessage);

        //sub notification
        Label subNotification = new Label();
        subNotification.setTextFill(Color.web("#8a7f7f"));
        subNotification.setStyle(
                "-fx-set-fill: #8a7f7f;"+"-fx-font-size: 14;"+"-fx-font-family: FreeSans;"
        );
        subNotification.setPrefWidth(551);
        subNotification.prefHeight(27);
        subNotification.setLayoutY(32);
        subNotification.setLayoutX(22);
        subNotification.setText(subMassage);

        //clear Button
        Button clear = new Button();
        clear.setText("Clear");
        clear.setStyle("-fx-background-color: red;"+"-fx-background-radius: 18;"+"-fx-font-size: 14;"+"-fx-font-family: FreeSans;"+"-fx-text-fill: white;"+"-fx-font-weight: bold");
        clear.setPrefHeight(27);
        clear.setPrefWidth(60);
        clear.setLayoutX(633);
        clear.setLayoutY(16);

        //adding all element to ->anchorPane ->Vbox
        notificationCard.getChildren().addAll(mainNotification,subNotification,clear);
        notificationVbox.getChildren().add(index,notificationCard);
        VBox.setMargin(notificationCard,new Insets(4,0,13,10));

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setNotification("Dr.Nirob Accepted your appointment request","Dear, Rayan! sorry for late response! We will met at 4:30 in 12 sep!! please don't be late! You will get well soon! not to worry",1);
        setNotification("Dr.Nirob Accepted your appointment request","Dear, Rayan! sorry for late response! We will met at 4:30 in 12 sep!! please don't be late! You will get well soon! not to worry",2);
        setNotification("Dr.Nirob Accepted your appointment request","Dear, Rayan! sorry for late response! We will met at 4:30 in 12 sep!! please don't be late! You will get well soon! not to worry",3);
        setNotification("Dr.Nirob Accepted your appointment request","Dear, Rayan! sorry for late response! We will met at 4:30 in 12 sep!! please don't be late! You will get well soon! not to worry",4);
        setNotification("Dr.Nirob Accepted your appointment request","Dear, Rayan! sorry for late response! We will met at 4:30 in 12 sep!! please don't be late! You will get well soon! not to worry",5);
        setNotification("Dr.Nirob Accepted your appointment request","Dear, Rayan! sorry for late response! We will met at 4:30 in 12 sep!! please don't be late! You will get well soon! not to worry",6);
        setNotification("Dr.Nirob Accepted your appointment request","Dear, Rayan! sorry for late response! We will met at 4:30 in 12 sep!! please don't be late! You will get well soon! not to worry",7);
        setNotification("Dr.Nirob Accepted your appointment request","Dear, Rayan! sorry for late response! We will met at 4:30 in 12 sep!! please don't be late! You will get well soon! not to worry",8);
        setNotification("Dr.Nirob Accepted your appointment request","Dear, Rayan! sorry for late response! We will met at 4:30 in 12 sep!! please don't be late! You will get well soon! not to worry",9);
        setNotification("Dr.Nirob Accepted your appointment request","Dear, Rayan! sorry for late response! We will met at 4:30 in 12 sep!! please don't be late! You will get well soon! not to worry",10);

    }
}
