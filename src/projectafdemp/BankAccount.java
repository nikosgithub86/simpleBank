package projectafdemp;

import java.sql.SQLException;
public class BankAccount {
    
    private double accountBalance;

    public double getAccountBalance(int userID) throws SQLException {
        Database db = new Database();
        db.createConnection();
        accountBalance = db.getUserBalance(userID);
        return accountBalance;
    }

    
}
