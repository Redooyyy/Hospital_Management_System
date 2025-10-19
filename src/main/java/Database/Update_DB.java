package Database;

import Roles.UserRole;

public class Update_DB {

    //role update
    public static void updateRole(String username,UserRole role){
        Pass_me_query query = new Pass_me_query();
        query.updateDB("users","role_id","username");
        query.update(role.getRole_id(), username);
    }

}
