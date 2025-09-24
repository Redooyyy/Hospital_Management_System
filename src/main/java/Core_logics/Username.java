git/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Core_logics;

/**
 *
 * @author Reo
 */
public class Username {
    public static String uniq_user_name(String email){
        final int indx = email.lastIndexOf("@");
        String s = email.substring(0, indx); // gives the username of email(uniq because google already take uniq emails)
        return s;
    }
}
