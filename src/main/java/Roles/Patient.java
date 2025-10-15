package Roles;

import java.util.Date;

public class Patient extends User{
    private Date dateOfBirth;
    private String address;
    private String bloodGroup;

    //constructors
    public Patient(){}
    public Patient(String firstName,String lastName,String pass1,String pass2, String gender,String email,String number){
        super(firstName,lastName,pass1,pass2,gender,email,number,UserRole.PATIENT);
    }


    //getters
    public Date getDateOfBirth() {
        return dateOfBirth;
    }
    public String getAddress() {
        return address;
    }
    public String getBloodGroup() {
        return bloodGroup;
    }

    //setters
    public void setAddress(String address) {
        this.address = address;
    }
    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }
    public void setBloodGroup(String bloodGroup) {
        this.bloodGroup = bloodGroup;
    }


}
