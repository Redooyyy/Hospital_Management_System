/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author Reo
 */
public class Pass_me_query {
   
        private final String target;
        private final String query;

    public Pass_me_query(String target, String whichTable, String whichRow) {
        this.target = target;
        //syntax example = "SELECT role_id FROM users WHERE user_id = ?";
        this.query = "SELECT " + target + " FROM " + whichTable + " WHERE " + whichRow + " = ?";
    }
              
        
        //for userID,role_ID,doctorID,patientID(by passing a string)
       public int returnInt(int anyID){
        try(Connection connection = DB_connect.getConnect(); PreparedStatement statement = connection.prepareStatement(query)) {
            
            statement.setInt(1, anyID);   
            
            try(ResultSet result = statement.executeQuery()) {
                return result.next()?result.getInt(target) : 0;
            } catch (Exception e) {
                e.printStackTrace();
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }
        
       
         //for userID,role_ID,doctorID,patientID (by passing a string)
       public int returnInt(String anyName){
        try(Connection connection = DB_connect.getConnect(); PreparedStatement statement = connection.prepareStatement(query)) {
            
            statement.setString(1, anyName);   
            
            try(ResultSet result = statement.executeQuery()) {
                return result.next()?result.getInt(target) : 0;
            } catch (Exception e) {
                e.printStackTrace();
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

       
       //for username,fullname,password (by passing a int)
       public String returnString(int anyID){
        try(Connection connection = DB_connect.getConnect(); PreparedStatement statement = connection.prepareStatement(query)) {
            
            statement.setInt(1, anyID);   
            
            try(ResultSet result = statement.executeQuery()) {
                return result.next()?result.getString(target) : null;
            } catch (Exception e) {
                e.printStackTrace();
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
       
       
       //for username,fullname,password (by passing a string)
       public String returnString(String anyString){
        try(Connection connection = DB_connect.getConnect(); PreparedStatement statement = connection.prepareStatement(query)) {
            
            statement.setString(1, anyString);   
            
            try(ResultSet result = statement.executeQuery()) {
                return result.next()?result.getString(target) : null;
            } catch (Exception e) {
                e.printStackTrace();
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
       
}
