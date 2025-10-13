/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
//just to save streak in github -_-
package com.mycompany.hms;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.util.Objects;

/**
 *
 * @author Reo
 **/
//checking remote updating

public class HMS extends Application{

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
            //Group root = new Group();
        Parent root1 = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/UI/LoginUI.fxml")));
        Scene scene = new Scene(root1);
        //login page can't be resizable
        //primaryStage.initStyle(StageStyle.TRANSPARENT);
        primaryStage.setResizable(false);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
