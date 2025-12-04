package Database;

import Roles.UserRole;

public class Update_DB {

    //role update
    public static void updateRole(String username,UserRole role){
        Pass_me_query query = new Pass_me_query();
        query.updateDB("users","role_id","username");
        query.update(role.getRole_id(), username);
    }

    public static void updateBill(String username,double cost){
        int userID = GetFrom_DB.getUserID(username);
        Pass_me_query query = new Pass_me_query();
        query.updateDB("patients","have_to_pay","user_id");
        query.update(cost,userID);
    }

    public static void updateStock(String Medicine, int stock){
        //update db
        Pass_me_query query = new Pass_me_query();
        query.updateDB("medicines","stock_quantity","name");
        query.update(Medicine,stock);
    }

    public static void updateRQ(boolean accept){
        //update DB
    }

    public static void prescription(int doctor,int patient,String note, int medicineID, int quantity2){
        //update db
    }



}
