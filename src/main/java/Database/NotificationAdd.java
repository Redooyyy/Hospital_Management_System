package Database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class NotificationAdd {

    public static void addSallary(int user_id, String title, String message){

        String sql = "INSERT INTO notification (user_id, notification_title, notification_msg) VALUES (?, ?, ?)";
        try(Connection connection = DB_connect.getConnect(); PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, user_id);
            statement.setString(2, title);
            statement.setString(3,message);
            statement.executeUpdate();

            System.out.println("User added successfully"); // for debug purpose
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
