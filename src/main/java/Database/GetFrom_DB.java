package Database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author Reo
 */
public class GetFrom_DB {
    
    //get userID by usernames
    public static int geting_user_id(String name){
        final String query = "SELECT user_id FROM users WHERE username = ?";
        try(Connection connection = DB_connect.getConnect(); PreparedStatement statement = connection.prepareStatement(query)) {
            
            statement.setString(1, name);   
            
            try(ResultSet result = statement.executeQuery()) {
                return result.next()?result.getInt("user_id") : null;
            } catch (Exception e) {
                e.printStackTrace();
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }
    
    //get fullname by userID
    public static String geting_user_fullname(int userID){
        final String query = "SELECT full_name FROM users WHERE user_id = ?";
        try(Connection connection = DB_connect.getConnect(); PreparedStatement statement = connection.prepareStatement(query)) {
            
            statement.setInt(1, userID);   
            
            try(ResultSet result = statement.executeQuery()) {
                return result.next()?result.getString("full_name") : null;
            } catch (Exception e) {
                e.printStackTrace();
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
    //get role by userID
    public static int geting_user_role(int userID){
        final String query = "SELECT role_id FROM users WHERE user_id = ?";
        try(Connection connection = DB_connect.getConnect(); PreparedStatement statement = connection.prepareStatement(query)) {
            
            statement.setInt(1, userID);   
            
            try(ResultSet result = statement.executeQuery()) {
                return result.next()?result.getInt("role_id") : null;
            } catch (Exception e) {
                e.printStackTrace();
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }
    
    
    
}
