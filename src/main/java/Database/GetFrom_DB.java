package Database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author Reo
 */
public class GetFrom_DB {
    
    //get userID
    public static int getUserID(String username){
    Pass_me_query query = new Pass_me_query("user_id", "users", "username");
    return query.returnInt(username);
    }
    
    //get role_id
    public static int getRoleID(int userID){
    Pass_me_query query = new Pass_me_query("role_id", "users", "user_id");
    return query.returnInt(userID);
    }
    
    
    //get fullname by userID
//    public static String geting_user_fullname(int userID){
//        final String query = "SELECT full_name FROM users WHERE user_id = ?";
//        try(Connection connection = DB_connect.getConnect(); PreparedStatement statement = connection.prepareStatement(query)) {
//            
//            statement.setInt(1, userID);   
//            
//            try(ResultSet result = statement.executeQuery()) {
//                return result.next()?result.getString("full_name") : null;
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//            
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return null;
//    }
//    
    //get role by userID
//    public static int geting_user_role(int userID){
//        final String query = "SELECT role_id FROM users WHERE user_id = ?";
//        try(Connection connection = DB_connect.getConnect(); PreparedStatement statement = connection.prepareStatement(query)) {
//            
//            statement.setInt(1, userID);   
//            
//            try(ResultSet result = statement.executeQuery()) {
//                return result.next()?result.getInt("role_id") : null;
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//            
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return 0;
//    }
//    
    //user exist?(by username)
    
  
    
}
