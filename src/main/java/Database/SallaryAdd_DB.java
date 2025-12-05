package Database;

import Core_logics.Username;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SallaryAdd_DB {


    public static void addSallary(int user_id, double sallary){

        String sql = "INSERT INTO salaries (user_id, amount) VALUES (?, ?)";
        try(Connection connection = DB_connect.getConnect(); PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, user_id);
            statement.setDouble(2, sallary);

            statement.executeUpdate();

            System.out.println("User added successfully"); // for debug purpose
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
