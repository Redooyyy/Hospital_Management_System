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
        
        int a = GetFrom_DB.getUserID("rwarieal");
        System.out.println(a);
        System.out.println(GetFrom_DB.getRoleID(a));
        
    }
}
