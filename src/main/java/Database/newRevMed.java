package Database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class newRevMed {
    public static void addMed(double price){

        String sql = "INSERT INTO moneyCalculate (medicineMoney) VALUES (?)";
        try(Connection connection = DB_connect.getConnect(); PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setDouble(1, price);
            statement.executeUpdate();
            System.out.println("User added successfully"); // for debug purpose
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
