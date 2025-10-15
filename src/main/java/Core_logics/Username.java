package Core_logics;
/**
 *
 * @author Reo
 */
public class Username {
    public static String uniq_user_name(String email){
        final int indx = email.lastIndexOf("@");
        if(indx == -1){
            return "";
        }
        // gives the username of email(uniq because google already take uniq emails)
        return email.substring(0, indx);
    }
}
