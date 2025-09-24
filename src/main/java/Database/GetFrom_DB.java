package Database;
/**
 *
 * @author Reo
 */
public class GetFrom_DB {
    
    //get userID
    public static int getUserID(String username){
    Pass_me_query query = new Pass_me_query("user_id", "users", "username");
    return query.returnInt(username);
    }
    
    //get role_id
    public static int getRoleID(int user_id){
    Pass_me_query query = new Pass_me_query("role_id", "users", "user_id");
    return query.returnInt(user_id);
    }  

    //get password(username)
    public static String getPasswordByUsername(String username){    
        Pass_me_query query = new Pass_me_query("password", "users", "username");
        return query.returnString(username);
    }
    
    //get password(emmail)
    public static String getPasswordByEmail(String email){    
        Pass_me_query query = new Pass_me_query("password", "users", "email");
        return query.returnString(email);
    }

}
