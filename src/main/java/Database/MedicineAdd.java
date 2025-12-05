package Database;

import Core_logics.Username;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class MedicineAdd {


    public static void addMed(String name, String description,int stock,double price){

        String sql = "INSERT INTO medicines (name, description, stock_quantity, price) VALUES (?, ?, ?, ?)";
        try(Connection connection = DB_connect.getConnect(); PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, name);
            statement.setString(2, description);
            statement.setInt(3, stock);
            statement.setDouble(4, price);
            statement.executeUpdate();

            System.out.println("User added successfully"); // for debug purpose
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
