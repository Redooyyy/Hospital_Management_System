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
    //get fullName by email
   public  static  String getFullNameByEmail(String email){
        Pass_me_query query = new Pass_me_query("full_name","users","email");
        return query.returnString(email);
   }

    public  static  String getUserName(String full_name){
        Pass_me_query query = new Pass_me_query("username","users","full_name");
        return query.returnString(full_name);
    }
   //get fullName by username
    public  static  String getFullNameByUsername(String username){
        Pass_me_query query = new Pass_me_query("full_name","users","username");
        return query.returnString(username);
    }
    //get username by email
    public static  String getUserNameByEmail(String email){
        Pass_me_query query = new Pass_me_query("username","users","email");
        return query.returnString(email);
    }
    //get tips by unique id
    public static String getTips(int slNum){
        Pass_me_query query =new Pass_me_query("tips","health_tips","tips_id");
        return query.returnString(slNum);
    }

    public static double getMedPrice(String name){
        Pass_me_query query= new Pass_me_query("price","medicines","name");
        return query.returnDouble(name);
    }

    public static int getMedStock(String name){
        Pass_me_query query= new Pass_me_query("stock_quantity","medicines","name");
        return query.returnInt(name);
    }

}
