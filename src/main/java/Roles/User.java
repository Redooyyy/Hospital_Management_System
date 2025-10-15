package Roles;

public class User {
    private String fullName;
    private String username;
    private String password;
    private String gender;
    private String email;
    private String number;
    private UserRole role;
    private final String[] genderOptions ={"Male","Female","Other"};

    //constructors
    User(){}
    User(String fullName,String username,String password, int indx,String email,String number){
        setFullName(fullName);
        setUsername(username);
        setPassword(password);
        setGender(indx);
        setEmail(email);
        setNumber(number);
    }
    User(String fullName,String username,String password, int indx,String email,String number,UserRole role){
        setFullName(fullName);
        setUsername(username);
        setPassword(password);
        setGender(indx);
        setEmail(email);
        setNumber(number);
        setRole(role);
    }



    //getters
    public String getFullName() {
        return fullName;
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


    //setters
    public void setGender(int indx) {
        this.gender = genderOptions[indx];
    } //1.Male 2.Female 3.Others
    public void setFullName(String fullName) {
        this.fullName = fullName;
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
    public void setRole(UserRole role) {
        this.role = role;
    }

}
