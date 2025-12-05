package FxmlControllers.Admin;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class PharmacistAdmin_controller {
    @FXML
    private AnchorPane contentPane;




    public <T> void loadingContent(String fxmlPath) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPath));
            AnchorPane pane = loader.load();

            contentPane.getChildren().clear();
            contentPane.getChildren().add(pane);

            AnchorPane.setBottomAnchor(pane, 0.0);
            AnchorPane.setTopAnchor(pane, 0.0);
            AnchorPane.setLeftAnchor(pane, 0.0);
            AnchorPane.setRightAnchor(pane, 0.0);

            // Return controller so you can use it outside
            loader.getController();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void handOver(ActionEvent e) throws IOException {
        loadingContent("/UI/Pharmacist/HandOverUI.fxml");
    }

    public void checkStocks(){
        loadingContent("/UI/Pharmacist/SeeStacksUI.fxml");
    }

    public void addMed(){
        loadingContent("/UI/Pharmacist/AddMedicine.fxml");
    }
}
