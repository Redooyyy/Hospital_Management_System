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
    public static int getUserIDbyEmail(String email){
        Pass_me_query query = new Pass_me_query("user_id", "users", "email");
        return query.returnInt(email);
    }
    //get role_id
    //admin_id -> 1
    //usr->2
    //doctor ->3
    //receptionist ->4
    //pharmacist ->5 (optional)
    public static int getRoleID(int user_id){
    Pass_me_query query = new Pass_me_query("role_id", "users", "user_id");
    return query.returnInt(user_id);
    }  

    //get password(username)
    public static String getPasswordByUsername(String username){    
        Pass_me_query query = new Pass_me_query("password", "users", "username");
        return query.returnString(username);
    }
    
    //get password(email)
    public static String getPasswordByEmail(String email){    
        Pass_me_query query = new Pass_me_query("password", "users", "email");
        return query.returnString(email);
    }

   public  static  String getFullNameByEmail(String email){
        Pass_me_query query = new Pass_me_query("full_name","users","email");
        return query.returnString(email);
   }
    public  static  String getFullNameByUsername(String username){
        Pass_me_query query = new Pass_me_query("full_name","users","username");
        return query.returnString(username);
    }
    public static  String getUserNameByEmail(String email){
        Pass_me_query query = new Pass_me_query("username","users","email");
        return query.returnString(email);
    }

}
