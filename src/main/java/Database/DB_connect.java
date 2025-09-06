/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Reo
 */
public class DB_connect {
    private static final String urll = "jdbc:mysql://localhost:3306/hms";
    private static final String nm = "HMSreo";
    private static final String pass = "hmsreo2005";
    
    public static Connection getConnect() throws SQLException{
    return DriverManager.getConnection(urll,nm,pass);
    }
}
