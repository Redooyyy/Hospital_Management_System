/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.hms;

import Database.GetFrom_DB;
import Database.UserAdd_DB;

/**
 *
 * @author Reo
 */
public class HMS {

    public static void main(String[] args) {
        UserAdd_DB add = new UserAdd_DB();
        
        add.addUser("Reo", "Redoy", "rwarieal@gmail.com", "2005", "01311720456");
        
        System.out.println(GetFrom_DB.geting_user_id("Reo"));
        
    }
}
