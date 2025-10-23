package Roles;

public class Admin extends User{
    public void changeRole(String adminUsername,String username,UserRole role){
        UserServices services = new UserServices();
        services.changeUserRole(adminUsername,role,username);
    }

    //see all staffs
    //increase salary
    //add staffs
    //delete staffs
    //accept buy medicine request from pharmacist
    public void seeStaffs(){}


}
