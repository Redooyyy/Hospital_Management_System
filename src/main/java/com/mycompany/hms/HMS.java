/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.hms;

import Database.GetFrom_DB;
import Database.UserAdd_DB;

/**
 *
 * @author Reo
 **/
//checking remote updating

public class HMS {

    public static void main(String[] args) {
        UserAdd_DB add = new UserAdd_DB();
        //dummy data for debuging
        add.addUser("Redoy", "rwarieal@gmail.com", "2005", "01311720456");
        add.addUser("Rayan Ahmed", "rayan@gmail.com", "1234", "01346582145");
        
        System.out.println(GetFrom_DB.geting_user_id("rayan"));
        
    }
}
