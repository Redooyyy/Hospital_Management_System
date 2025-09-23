/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Database;

import Core_logics.Username;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author Reo
 */
public class UserAdd_DB {
    private int role_id = 2; //by default patient

    public UserAdd_DB() {
    }

    //seter for role//only admin can access
    public void setRole_id(int role_id) {
        this.role_id = role_id;
    }

    public int getRole_id() {
        return role_id;
    }
    
    
    
    
   public void addUser(String full_name, String email,String password,String number){
      
      String sql = "INSERT INTO users (username, password, full_name, email, phone, role_id) VALUES (?, ?, ?, ?, ?, ?)";
       try(Connection connection = DB_connect.getConnect(); PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, Username.uniq_user_name(email));
            statement.setString(2, password);
            statement.setString(3, full_name);
            statement.setString(4, email);
            statement.setString(5, number);
            statement.setInt(6, this.role_id);
            statement.executeUpdate();
            
            System.out.println("User added successfully"); // for debug purpose
       } catch (SQLException e) {
           e.printStackTrace();
       }
   }
   
}
