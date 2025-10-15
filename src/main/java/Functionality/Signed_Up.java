package Functionality;

import Database.UserAdd_DB;

public class Signed_Up {
    //add to db
    public static void savedInDB(String fullName,String password, String phoneNumber, String gender,String email){
        UserAdd_DB userAdd = new UserAdd_DB();
        userAdd.addUser(fullName,email,password,phoneNumber,gender);
    }

}
