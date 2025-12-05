package FxmlControllers.Pharmacist;

import Database.GetFrom_DB;
import Database.Update_DB;
import Roles.Pharmacy.Medicine;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class AddMedicine_Controller {

    @FXML
    TextField medPrice;
    @FXML
    TextField medQuantity;
    @FXML
    TextField medName;
    @FXML
    Label errorText;


    public void confirm(ActionEvent event) {
        if(exception()){
            if(GetFrom_DB.getMedPrice(medName.getText()) == 0){
                Medicine medicine = new Medicine(medName.getText(),Integer.parseInt(medQuantity.getText()),Double.parseDouble(medPrice.getText()));
                medicine.addMedicine();
                errorText.setText("Successfully added medicine");
                cancel(event);
            } else {
                Update_DB.updateStock(medName.getText(),GetFrom_DB.getMedStock(medName.getText())+ Integer.parseInt(medQuantity.getText()));
                Update_DB.updateMedPrice(medName.getText(),Double.parseDouble(medPrice.getText()));
                errorText.setText("Successfully added medicine");
                cancel(event);
            }
        }
    }

    public void cancel(ActionEvent event) {
        medQuantity.setText("");
        medPrice.setText("");
        medName.setText("");
    }

    boolean exception(){
        int b;
        double a;
        if(medName.getText() == ""){
            errorText.setText("Please enter medicine name");
            return false;
        } else if(medPrice.getText() == "") {
            errorText.setText("Please enter medicine price");
            return false;
        } else if(medQuantity.getText() == ""){
            errorText.setText("Please enter medicine quantity");
            return false;
        }
        try {
            a = Double.parseDouble(medPrice.getText());
        } catch (NumberFormatException e) {
            errorText.setText("please enter a valid price");
            return false;
        }
        try {
            b = Integer.parseInt(medQuantity.getText());
        } catch (NumberFormatException e) {
            errorText.setText("please enter a valid Quantity");
            return false;
        }

        if(a<=0){
            errorText.setText("please enter a valid price");
            return false;
        }
        if(b<=0){
            errorText.setText("please enter a valid Quantity");
            return false;
        }
        return true;
    }
}
