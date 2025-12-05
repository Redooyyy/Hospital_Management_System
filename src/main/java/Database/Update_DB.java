package Database;

import Roles.UserRole;

import java.time.LocalDate;

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
    public static void updateMedPrice(String Medicine, double price){
        //update db
        Pass_me_query query = new Pass_me_query();
        query.updateDB("medicines","price","name");
        query.update(Medicine,price);
    }
    public static void addMed(String medName,int stock, double price){
        MedicineAdd.addMed(medName,"",stock,price);
    }


    public static void medRev(double price){
        LocalDate date = LocalDate.now();
        if(GetFrom_DB.getMedRev(date) == 0){
            newRevMed.addMed(price);
        } else{
            Pass_me_query query = new Pass_me_query();
            query.updateDBDate("moneyCalculate","medicineMoney","clocky");
            query.update(GetFrom_DB.getMedRev(date)+price,date);
        }
    }
    public static void updateRQ(boolean accept){
        //update DB
    }

    public static void prescription(int doctor,int patient,String note, int medicineID, int quantity2){
        //update db
    }

    //update password
    public static void updatePass(String username, String pass){
        Pass_me_query query = new Pass_me_query();
        query.updateDBDate("password","users","username");
        query.update(pass,username);
    }
    //update mobile-number
    public static void updateMobileNumber(String username, String number){
        Pass_me_query query = new Pass_me_query();
        query.updateDBDate("phone","users","username");
        query.update(number,username);
    }
    //update fullname
    public static void updateFullName(String name,String username){
        Pass_me_query query = new Pass_me_query();
        query.updateDBDate("full_name","users","username");
        query.update(name,username);
    }

    public static void updateSallary(double salary,String username){
        Pass_me_query query = new Pass_me_query();
        query.updateDBDate("amount","salaries","user_id");
        query.update(username,salary);
    }

}
