package FxmlControllers.Pharmacist;

import Database.DB_connect;
import Roles.Pharmacy.Medicine;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class SeeStocks_controller implements Initializable {
    @FXML
    private TableView<Medicine> medicineTable;

    @FXML
    private TableColumn<Medicine, String> nameColumn;

    @FXML
    private TableColumn<Medicine, Integer> stockColumn;

    @FXML
    private TableColumn<Medicine, Double> priceColumn;



    @Override
    public void initialize(URL location, ResourceBundle resources) {
        medicineTable.setStyle("-fx-background-color: #1E2A38; -fx-text-fill: white;");

        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        stockColumn.setCellValueFactory(new PropertyValueFactory<>("stock"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));

        loadData();
    }

    private void loadData() {
        String sql = "SELECT name, stock_quantity, price FROM medicines";

        try (Connection connection = DB_connect.getConnect();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet rs = statement.executeQuery()) {

            //learned something new
            ObservableList<Medicine> medicines = FXCollections.observableArrayList();

            while (rs.next()) {
                medicines.add(new Medicine(
                        rs.getString("name"),
                        rs.getInt("stock_quantity"),
                        rs.getDouble("price")
                ));
            }

            medicineTable.setItems(medicines);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}
