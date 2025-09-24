/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Core_logics;

import Database.GetFrom_DB;

/**
 *
 * @author Rasel
 */
public class LoginLogic {
    boolean loggedInUsername(String username,String password){
        
        GetFrom_DB pass1=new GetFrom_DB();
        if(pass1.getPasswordByUsername(username)==password){
        return true;
        }else
        return false;
    
    }
    boolean loggedInEmail(String email,String password){
        GetFrom_DB pass2=new GetFrom_DB();
    if(pass2.getPasswordByEmail(email)==password){
    return true;
    }else
    return false;
    }
    
}
    

