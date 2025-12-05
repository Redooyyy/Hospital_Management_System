package FxmlControllers.Pharmacist;

import Database.DB_connect;
import Database.GetFrom_DB;
import Database.Update_DB;
import Roles.Pharmacy.Medicine;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;
import java.util.ResourceBundle;

public class HandOver_controller implements Initializable {
    @FXML
    private TextField searchField;
    @FXML
    private TextField searchFieldP;
    @FXML
    private ListView<String> suggesionList;
    @FXML
    private ListView<String> medListNm;
    @FXML
    private ListView<String> usrListNm;
    @FXML
    private ListView<Integer>medListQn;
    @FXML
    ListView<Double>medListPr;
    @FXML
    private ChoiceBox<Integer> dose;
    @FXML
    private ChoiceBox<Integer> day;
    @FXML
    private Label total;
    @FXML
    Label errorText;
    private double sum;
    private String userName;
    boolean isAdd = false;



    @Override
    public void initialize(URL location, ResourceBundle resources) {
        sum = 0;

        for(int i = 1;i<=250;i++){
            day.getItems().add(i);
            if(i<4) dose.getItems().add(i);
        }
        day.setValue(1);
        dose.setValue(1);
        day.setStyle("-fx-font-size: 16px;");
        dose.setStyle("-fx-font-size: 16px;");
        medListPr.setStyle("-fx-font-size: 16px; -fx-control-inner-background:  #2F4050; -fx-text-fill: white");
        medListQn.setStyle("-fx-font-size: 16px; -fx-control-inner-background:  #2F4050; -fx-text-fill: white");
        medListNm.setStyle("-fx-font-size: 16px; -fx-control-inner-background:  #2F4050; -fx-text-fill: white");


        suggesionList.setStyle("-fx-font-size: 16px; -fx-control-inner-background:  #2F4050; -fx-text-fill: white");
        usrListNm.setStyle("-fx-font-size: 16px; -fx-control-inner-background:  #2F4050; -fx-text-fill: white;visibility: false");


        //listener for live search
        searchField.textProperty().addListener((observable, oldValue, newValue) -> {
            if(newValue != null && !newValue.isEmpty()){
                loadSuggestions(newValue);
            } else {
                suggesionList.getItems().clear();
            }
        });

        searchFieldP.textProperty().addListener((observable, oldValue, newValue) -> {
            if(newValue != null && !newValue.isEmpty()){
                usrListNm.setVisible(true);
                loadUsr(newValue);
            } else {
                suggesionList.getItems().clear();
            }
        });

        usrListNm.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) ->{
            if (newValue != null) {
                searchFieldP.setText(newValue.substring(0,newValue.indexOf(" ")));
                usrListNm.setVisible(false);
                userName = newValue.substring(newValue.lastIndexOf(" ")+1);
                System.out.println(userName);
            }
        });

        //listener for selected item
        suggesionList.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) ->{
            if (newValue != null) {
                searchField.setText(newValue);
            }
        });
    }

    private void loadSuggestions(String query){
        ObservableList<String>results = FXCollections.observableArrayList();
        String sql = "SELECT name FROM medicines WHERE name LIKE? LIMIT 20";
        try(Connection connection = DB_connect.getConnect(); PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1,query+"%");
            ResultSet rs = statement.executeQuery();
            while (rs.next()){
                results.add(rs.getString("name"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        suggesionList.setItems(results);
    }

    private void loadUsr(String query){
        ObservableList<String>results = FXCollections.observableArrayList();
        String sql = "SELECT full_name FROM users WHERE full_name LIKE? AND role_id = 2 LIMIT 20";
        try(Connection connection = DB_connect.getConnect(); PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1,query+"%");
            ResultSet rs = statement.executeQuery();
            while (rs.next()){
                String full_name = rs.getString("full_name");
                results.add( full_name+"                  username : "+GetFrom_DB.getUserName(full_name));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        usrListNm.setItems(results);
    }

    public void add(){
        if(Objects.equals(searchField.getText(), "")) errorText.setText("Please enter medicine name");
        else if(dose.getValue() == null || day.getValue() == null) errorText.setText("please select day and dose");
       else if(searchField.getText() != null){
            medListNm.getItems().add(searchField.getText());
            medListQn.getItems().add(dose.getValue() * day.getValue());
            double pricePerMed = dose.getValue()*day.getValue()* GetFrom_DB.getMedPrice(searchField.getText());
            String prcPerMd = String.format("%3f",pricePerMed);
            medListPr.getItems().add(Double.valueOf(prcPerMd));
            sum+= dose.getValue()*day.getValue()* GetFrom_DB.getMedPrice(searchField.getText());
            String formatedSum = String.format("%.3f",sum);
            total.setText(formatedSum+" Tk");
            isAdd = true;
        }
        if(userName == null){
            errorText.setText("Please select a Patient");
        }
    }

    //updated db
    public void confirm(){
        if(userName == null) errorText.setText("Please select a Patient");
        if(!isAdd) errorText.setText("please add medicine to the list");
         else {
            Update_DB.updateBill(userName,sum);
            int x = medListNm.getItems().size();
            for(int i = 0;i<x;i++){
                if(Medicine.removeFromStock(medListNm.getItems().get(i),medListQn.getItems().get(i))){
                    System.out.println("all Okay");
                    String price = total.getText().substring(0,total.getText().indexOf(" "));
                    Update_DB.medRev(Double.parseDouble(price));
                } else {
                    errorText.setText("Medicine is out of stock");
                }
            }
        }
    }

    public void cancel(){
        errorText.setText("");
        searchField.setText("");
        searchFieldP.setText("");
        day.getSelectionModel().clearSelection();
        dose.getSelectionModel().clearSelection();
        medListNm.getItems().clear();
        medListQn.getItems().clear();
        medListPr.getItems().clear();
    }

}
