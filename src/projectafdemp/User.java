package projectafdemp;

import java.sql.SQLException;
import java.util.Scanner;

public class User {

   public  Database db;
    
    private String userName;
    private String password;
    public int id;
    
    public double  getUserBalance(int userID) throws SQLException {
        BankAccount ba =  new BankAccount();
        return ba.getAccountBalance(userID);               
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }
//default constructor
    public User() {
    }
//constructor with parameters userName, password and Database
    public User(String userName, String password, Database db) {
        this.userName = userName;
        this.password = password;
        this.db = db;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }
// returns the id of user (if method checkuser returns true else if checkuser return false the method return -1)
    public int login() throws SQLException {
        if (db.checkUsers(userName, password)) {
         
            this.id = db.userID(userName, password);
            return this.id ;
        }
        return -1;
    }

}
