package Database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class Pass_me_query {
   
        private  String target;
        private  String query;

        public Pass_me_query(){}
    public Pass_me_query(String target, String whichTable, String whichRow) {
        this.target = target;
        //syntax example = "SELECT role_id FROM users WHERE user_id = ?";
        this.query = "SELECT " + target + " FROM " + whichTable + " WHERE " + whichRow + " = ?";
    }

    public void updateDB(String target, String whichCell, String whichRow) {
        this.target = target;
        //syntax example = "UPDATE users SET role = ? WHERE username = ?";
        this.query = "UPDATE " + target + " SET " + whichCell + " = ? WHERE " + whichRow + " = ?";
    }




    //for userID,role_ID,doctorID,patientID(by passing a string)
       public int returnInt(int anyID){
        try(Connection connection = DB_connect.getConnect();
            PreparedStatement statement = connection.prepareStatement(query)) {
            
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

    public double returnDouble(String anyName){
        try(Connection connection = DB_connect.getConnect(); PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setString(1, anyName);

            try(ResultSet result = statement.executeQuery()) {
                return result.next()?result.getDouble(target) : 0;
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



    //update
    void update(String updated,String username){
        try(Connection connection = DB_connect.getConnect(); PreparedStatement statement = connection.prepareStatement(query) ) {
            statement.setString(1,updated);
            statement.setString(2,username);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    void update(int updated,String username){
        try(Connection connection = DB_connect.getConnect(); PreparedStatement statement = connection.prepareStatement(query) ) {
            statement.setInt(1,updated);
            statement.setString(2,username);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    void update(double updated,int id){
        try(Connection connection = DB_connect.getConnect(); PreparedStatement statement = connection.prepareStatement(query) ) {
            statement.setDouble(1,updated);
            statement.setInt(2,id);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    void update(String updated,int id){
        try(Connection connection = DB_connect.getConnect(); PreparedStatement statement = connection.prepareStatement(query) ) {
            statement.setInt(1,id);
            statement.setString(2,updated);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
