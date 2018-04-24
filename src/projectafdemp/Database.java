package projectafdemp;

import com.mysql.jdbc.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Database {

    public Connection con = null;
    public static ResultSet rs;

    // This method creates connection with database
    public void createConnection() {
        String url = "jdbc:mysql://localhost:3306/afdemp_java_1?useSSL=false";
        String username = "root";
        String password = "fermionio";

        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = (Connection) DriverManager.getConnection(url, username, password);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
// This method returns each user Id by providing his name and password

    public int userID(String x, String y) throws SQLException {

        Statement st = con.createStatement();
       ResultSet result = st.executeQuery("select * from users");
        while (result.next()) {
            if (x.equals(result.getString("username")) && y.equals(result.getString("password"))) {
                return result.getInt("id");
            }
        }
        return -1;
    }
//deposit an ammount of money to a user which is identified by his ID in table accounts 

    public boolean deposit(double amount, int userID) throws SQLException {
        if (con != null) {
            Statement st = con.createStatement();
            double x = getUserBalance(userID);
            if (x != -1) {
                st.execute("update accounts set amount = " + (x + amount) + " where user_id= " + userID);
            }

        }
        return false;

    }

//withdraw an ammount of money from a user which is identified by his ID in table accounts 
    public boolean withdraw(double amount, int userID) throws SQLException {
        if (con != null) {
            Statement st = con.createStatement();
            double x = getUserBalance(userID);
            if (x != -1) {
                st.execute("update accounts set amount = " + (x - amount) + " where user_id= " + userID);
            }

        }
        return false;
    }
//  that method returns each user balance(he is identified by ID)

    public double getUserBalance(int userID) throws SQLException {
        if (con != null) {
            Statement st = con.createStatement();
            ResultSet result = st.executeQuery("select * from accounts where user_id= " + userID);
            if (result.next()) {
                return result.getDouble("amount");

            }
        }
        return -1.0;
    }
//With that boolean method you can identify each user by his name and password in table users
    public boolean checkUsers(String x, String y) throws SQLException {
        try {
            PreparedStatement pst = con.prepareStatement("select * from users where username = ? and password = ?");
            pst.setString(1, x);
            pst.setString(2, y);
            rs = pst.executeQuery();
            if (rs.next()) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }


    public String getUserPass(String x) throws SQLException {
        Statement st = con.createStatement();
        ResultSet result = st.executeQuery("select * from users");
        while (result.next()) {
            if (x.equals(result.getString("password"))) {
                return result.getString("password");
            }
        }
        return null;

    }

}
//    public String getUserPassw(String x){
//         try {
//            PreparedStatement pst = con.prepareStatement("select * from users where username = ? ");
//            pst.setString(1, x);
//            rs = pst.executeQuery();
//            if (rs.next()) {
//                return rs.getString(x);
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//return false;
//    }

