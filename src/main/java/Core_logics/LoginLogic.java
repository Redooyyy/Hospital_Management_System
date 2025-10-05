/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Core_logics;

import Database.GetFrom_DB;
import Database.Pass_me_query;

import java.util.Objects;

/**
 *
 * @author Rasel
 */
public class LoginLogic {
    public boolean loggedInUsername(String username,String password){
        
        GetFrom_DB pass1=new GetFrom_DB();
        if(Objects.equals(pass1.getPasswordByUsername(username), password)){
        return true;
        } else
        return false;
    
    }
    public boolean loggedInEmail(String email,String password){
        GetFrom_DB pass2=new GetFrom_DB();
    if(Objects.equals(pass2.getPasswordByEmail(email), password)){
    return true;
    }else
    return false;
    }

    public boolean checkRoleUsername(String username ,int role){
        GetFrom_DB roleid=new GetFrom_DB();
        if(Objects.equals(roleid.getRoleID(roleid.getUserID(username)), roleid)){
            return true;
        } else{
           return false;
        }
    
    }

    public boolean checkEmail(String email,int role){
        GetFrom_DB dbrole= new GetFrom_DB();
        if(Objects.equals(dbrole.getRoleID(dbrole.getUserIDbyEmail(email)),role)){
            return true;
        }else{
            return false;
        }
    }
       
}

    

