package Roles;

import Core_logics.Username;
import Database.GetFrom_DB;
import Functionality.Signed_Up;

import java.util.Objects;

public class User {
    private String firstName;
    private String lastName;
    private String username;
    private String password;
    private String gender;
    private String email;
    private String number;
    private UserRole role;
    private String errorText;
    private boolean allOK = false;

    //constructors
    User(){}
    User(String firstName,String lastName,String pass1,String pass2, String gender,String email,String number,UserRole role){
        String username = Username.uniq_user_name(email);
        setFirstName(firstName);
        setLastName(lastName);
        setUsername(username);
        setPassword(pass1);
        setGender(gender);
        setEmail(email);
        setNumber(number);
        setRole(role);
    }

    public void setAll(String firstName,String lastName,String pass1,String pass2, String gender,String email,String number,UserRole role){
            String username = Username.uniq_user_name(email);
            setFirstName(firstName);
            setLastName(lastName);
            setUsername(username);
            setPassword(pass1);
            setGender(gender);
            setEmail(email);
            setNumber(number);
            setRole(role);
    }




    //getters

    public String getFirstName() {
        return firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public String getFullName() {
        return firstName+" "+lastName;
    }
    public String getUsername() {
        return username;
    }
    public String getPassword() {
        return password;
    }
    public String getGender() {
        return gender;
    }
    public String getEmail() {
        return email;
    }
    public String getNumber() {
        return number;
    }
    public UserRole getRole() {
        return role;
    }
    public String getErrorText() {
        return errorText;
    }
    public boolean getAllOK(){
        return allOK;
    }

    //setters
    public void setGender(String gender) {
        this.gender = gender;
    } //1.Male 2.Female 3.Others
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public void setNumber(String number) {
        this.number = number;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    private void setRole(UserRole role) {
        this.role = role;
    }

    //Common Cases

    public boolean exceptionsPassed(String password1,String password2){
        //Null safeties
        if(Objects.equals(firstName,"") || Objects.equals(lastName,"")){
            //errorLabel.setText("Please enter your first and last name");
            errorText = "Please enter your first and last name";
            System.out.println("F pass");
            allOK = false;
            return false;
        }

        if (firstName.length() < 2 || lastName.length() < 2){
           // errorLabel.setText("Please enter a valid name");
            errorText="Please enter a valid name";
            System.out.println("F2 pass");
            allOK = false;
            return false;
        }

        for(int i =0;i<firstName.length();i++){
            if((firstName.toLowerCase().charAt(i) < 'a' || firstName.toLowerCase().charAt(i) > 'z') && firstName.charAt(i) != ' '){
                //errorLabel.setText("Name can not contains number or special characters");
                errorText = "Name can not contains number or special characters";
                allOK = false;
                return false;
            }
        }
        for(int i =0; i < lastName.length(); i++){
            if((lastName.toLowerCase().charAt(i) < 'a' || lastName.toLowerCase().charAt(i) > 'z') && lastName.charAt(i)!= ' '){
               // errorLabel.setText("Name can not contains number or special characters");
                errorText = "Name can not contains number or special characters";
                allOK = false;
                return false;
            }
        }
        //tried object.equal but this hit an error while running -_-
      if(Objects.equals(gender, "")){
           // errorLabel.setText("Please select your gender");
            errorText = "Please select your gender";
            allOK = false;
            return false;
        }

        if(Objects.equals(email,"")){
           // errorLabel.setText("Please enter your email");
            errorText="Please enter your email";
            allOK = false;
            return false;
        }
        //still many exception, but we'll add those logic later for valid email
        int indx = email.lastIndexOf('@');
        String domain = email.substring(indx+1);
        String local;
        if(indx != -1){
            local = email.substring(0,indx);
        } else {
            local = email;
        }

        if(!domain.equals("gmail.com") || local.indexOf('@')!=-1) {
           // errorLabel.setText("Invalid email address");
            errorText="Invalid email address";
            allOK = false;
            return false;
        }
        if(!Objects.equals(GetFrom_DB.getPasswordByEmail(getEmail()), null)){
           // errorLabel.setText("Email is already registered");
            errorText="Email is already registered";
            allOK = false;
            return false;
        }
        if(Objects.equals(number,"")){
           // errorLabel.setText("Please enter your phone number");
            errorText="Please enter your phone number";
            allOK = false;
            return false;
        }
        //valid phone number or not
        String validNum="+-0123456789";

        for(int i =0;i<number.length();i++){
            if (number.length() < 5){
              //  errorLabel.setText("Invalid phone number");
                errorText="Invalid phone number";
                allOK = false;
                System.out.println(number);
                return  false;
            }
            if(validNum.indexOf(number.charAt(i)) == -1){
               // errorLabel.setText("Invalid phone number");
                errorText="Invalid phone number";
                allOK = false;
                System.out.println(number);
                return false ;
            }
        }

        if(Objects.equals(password,"")){
           // errorLabel.setText("Please set your password");
            allOK = false;
            return  false;
        }
        if(password.length() < 7){
           // errorLabel.setText("Password must be more than 7 characters");
            allOK = false;
            return  false;
        }
        if(password.length() > 15){
           // errorLabel.setText("Password must be less than 15 characters");
            allOK = false;
            return  false;
        }

        if(!newPassConfirmPass(password1,password2)){
            //errorLabel.setText("Password mismatch, Please enter matching password");
            errorText = "Password mismatch, Please enter matching password";
            allOK = false;
            return  false;
        }
        allOK = true;
        errorText = "";
        return true;
    }

    public boolean newPassConfirmPass(String pass1,String pass2){
        return Objects.equals(pass1, pass2);
    }

    public void saveToDataBase(){
        Signed_Up.savedInDB(getFullName(),getPassword(),getNumber(),getGender(),getEmail());
    }


}
