package FxmlControllers.Admin;

import Database.GetFrom_DB;
import FxmlControllers.LoginUI_controller;
import FxmlControllers.NotificationUI_controller;
import FxmlControllers.SwitchScene;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.HashSet;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.Set;


public class OverviewUI_controller implements Initializable {
    @FXML
   private Label notification1;
    @FXML
    private Label notification2;
    @FXML
    private Label notification3;
    @FXML
    private ImageView addAdminimg;
    @FXML
    private ImageView addpharimg;
    @FXML
    private ImageView addDoctorimg;
    @FXML
    private ImageView addRecepimg;
    private String username;
    private String fusername;

    public void setUsername(String username) {
        this.username = username;
    }

    //have to add a new table for these in my sql.....I'll finish it soon

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        addAdminimg.setOnMouseClicked(e->{
            try {
                addAdmin(new ActionEvent(addAdminimg,null));
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        addDoctorimg.setOnMouseClicked(e->{
            try {
                addDoctor(new ActionEvent(addDoctorimg,null));
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        addpharimg.setOnMouseClicked(e->{
            try {
                addPharma(new ActionEvent(addpharimg,null));
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        addRecepimg.setOnMouseClicked(e->{
            try {
                addRecept(new ActionEvent(addRecepimg,null));
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });



        Platform.runLater(()->{
                fusername = username;
        });

        setProfileImage();
        setHealthTips();
        try {
            setNotification();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void setProfileImage(){

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

    public void setNotification() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/UI/NotificationUI.fxml"));
        Parent root = loader.load();
        NotificationUI_controller notification = loader.getController();
        notification1.setText(notification.first());
        notification2.setText(notification.second());
        if (notification.third() != null) {
            notification3.setText(notification.third());
        } else {
            notification3.setText("");
        }
    }

    public void addAdmin(ActionEvent e) throws IOException {
        FXMLLoader load = new FXMLLoader(getClass().getResource("/UI/AdminUI/RoleChangeUI.fxml"));
        Parent root = load.load();
        RoleChnangeUI_controller controller = load.getController();
        controller.setRole("admin");
        controller.setUserName(fusername);
        System.out.println(fusername);
        Stage stage = (Stage)((Node)e.getSource()).getScene().getWindow(); //usages current stage rather than creating another
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void addDoctor(ActionEvent e) throws IOException {
        FXMLLoader load = new FXMLLoader(getClass().getResource("/UI/AdminUI/RoleChangeUI.fxml"));
        Parent root = load.load();
        RoleChnangeUI_controller controller = load.getController();
        controller.setRole("doctor");
        controller.setUserName(fusername);
        Stage stage = (Stage)((Node)e.getSource()).getScene().getWindow(); //usages current stage rather than creating another
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void addPharma(ActionEvent e) throws IOException {
        FXMLLoader load = new FXMLLoader(getClass().getResource("/UI/AdminUI/RoleChangeUI.fxml"));
        Parent root = load.load();
        RoleChnangeUI_controller controller = load.getController();
        controller.setRole("pharmacist");
        controller.setUserName(fusername);
        Stage stage = (Stage)((Node)e.getSource()).getScene().getWindow(); //usages current stage rather than creating another
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void addRecept(ActionEvent e) throws IOException {
        FXMLLoader load = new FXMLLoader(getClass().getResource("/UI/AdminUI/RoleChangeUI.fxml"));
        Parent root = load.load();
        RoleChnangeUI_controller controller = load.getController();
        controller.setRole("receptionist");
        controller.setUserName(fusername);
        Stage stage = (Stage)((Node)e.getSource()).getScene().getWindow(); //usages current stage rather than creating another
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

}

