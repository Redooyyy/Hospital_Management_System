/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Database;

import com.mysql.cj.protocol.Resultset;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author Reo
 */
public class GetFrom_DB {
    public static String geting_user_id(String name){
        final String query = "SELECT user_id FROM users WHERE username = ?";
        try(Connection connection = DB_connect.getConnect(); PreparedStatement statement = connection.prepareStatement(query)) {
            
            statement.setString(1, name);   
            
            try(ResultSet result = statement.executeQuery()) {
                return result.next()?result.getString("user_id") : null;
            } catch (Exception e) {
            }
            
        } catch (Exception e) {
        }
        return null;
    }
}
