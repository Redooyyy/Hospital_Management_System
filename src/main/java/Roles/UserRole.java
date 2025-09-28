/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package Roles;

/**
 *
 * @author Reo
 */
public enum UserRole {
    Admin(1),
    Patient(2),
    Doctor(3),
    Pharmacist(5),
    Receptionist(4);

    private int role_id;
    UserRole(int role_id){
        this.role_id=role_id;
    }

    public int getRole_id() {
        return role_id;
    }

    //flexible if more adding more roles in future
    public static int userRoleID(String role){
        UserRole[] all_roles = UserRole.values(); //stored all roles in an array;
        for (UserRole allRole : all_roles) {
            if (allRole.name().equals(role)) {
                return allRole.getRole_id();
            }
        }
        return 0;
    }
}
