package Roles;

import Database.GetFrom_DB;
import Database.Update_DB;

public class UserServices {

    //only admin can change role
    public void changeUserRole(String adminUsername,UserRole role, String username ){
        if(GetFrom_DB.getRoleID(GetFrom_DB.getUserID(adminUsername)) == UserRole.ADMIN.getRole_id()){
            Update_DB.updateRole(username,role);
        } else {
            System.out.println("Only admin can change the role");
        }
    }
}
