package projectafdemp;

import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Menu {

    public Database database;
    public Scanner input = new Scanner(System.in);
    public StringBuilder sb = new StringBuilder();

    public Menu(Database database) {
        this.database = database;
    }
// default constructor

    public Menu() {
    }
// doSB method appends the Stringbuilder 

    public void doSB(String y) {
        sb.append(y);
        sb.append("\r\n");
    }
// database getter

    public Database getDatabase() {
        return database;
    }
//database setter

    public void setDatabase(Database database) {
        this.database = database;
    }
//show options presents in console two different menus one for each kind of user 

    public static void showOptions(User x) {
        if (x.getId() == 1) {
            System.out.println("+------------------------------------+");
            System.out.println("         WELCOME                    ");
            System.out.println("         ADMIN                           ");
            System.out.println("+------------------------------------+");
            System.out.println("if you want to view the amount of money in cooperatives bank account press 1 \n if you want to view the amount of money of a members bank account press 2 \n if you want to deposit to a members bank account press 3\n if you want towithdraw money from a members bank account press 4\n if you want to send to a file todays transaction press 5 \n number 7 terminates the program");
        } else {
            System.out.println("+------------------------------------+");
            System.out.println("         WELCOME                     ");
            System.out.println("+--------------------------------------+");
            System.out.println("if you want to view the amount of money in your account press 1\n if you want to deposit money to cooperatives bank account press 2\n if you want to deposit money to a members bank account press 3\n if you want to send to a file todays transaction press 4 \n number 7 terminates the program");
        }
    }
//Identify the user.User Admin has 5 options while simple user has 4 option(its option is mentioned above)

    public void enterOption(User x, int option) throws SQLException, FileNotFoundException, UnsupportedEncodingException {

        if (x.getId() == 1) {

            if (option > 0 && option < 6) {
                switch (option) {
                    case 1:
                        System.out.println("The amount of money in cooperatives bank account is: " + x.getUserBalance(1));
                        doSB("the cooperatives bank account has " + String.valueOf(x.getUserBalance(1)) + "amount of money\n");
                        break;
                    case 2:

                        System.out.println("now you can view the amount of money in members accounts\npress numbers grater than one");
                        System.out.println("press i for the user i: ");
                        int i = input.nextInt();
                        if (i == 1) {

                            System.out.println("that is cooperatives bank account");
                            break;

                        }

                        try {
                            if (database.getUserBalance(i) > -1.0) {
                                System.out.println("user" + i + "has in his account:");
                                System.out.println(x.getUserBalance(i));
                            } else {
                                System.out.println("we dont possess user" + i);
                            }
                        } catch (SQLException ex) {
                            Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
                        }

                break;

            

            
          
            case 3:
                        System.out.println("you can now deposit money to a members bank account");
                        System.out.println("to which user you want to deposit? for user i press i");
                        int opt = input.nextInt();
                        if (x.getUserBalance(opt) > -1.0) {
                            if (opt != 1) {
                                System.out.println("please type the amount of money you want to deposit");
                                System.out.println("amount: ");
                                double a = input.nextDouble();
                               double b =x.getUserBalance(1);
                                double amount = validateAmount(a,b);
                                
                                database.deposit(amount, opt);
                                database.withdraw(amount, 1);
                                System.out.println("The balance of user" + opt + "is now : " + x.getUserBalance(opt));
                                doSB("The balance of user" + opt + "is now : " + x.getUserBalance(opt) + "as well as the cooperatives bank account is now: " + x.getUserBalance(1));
                            } else {
                                System.out.println("you should have press a valid option");
                            }
                        } else {
                            System.out.println("non existent user");
                        }
                        break;
                    case 4:
                        System.out.println("you can now withdraw money from users");
                        System.out.println("from which user you want to withdraw? for user i press i");
                        int op = input.nextInt();
                        if (x.getUserBalance(op) > -1.0) {
                            if (op != 1) {
                                    System.out.println("please type the amount of money you want to withdraw");
                                System.out.println("amount: ");
                                double a = input.nextDouble();
                                double b = x.getUserBalance(op);
                                double amount = validateAmount(a,b);
                                database.withdraw(amount, op);
                                database.deposit(amount, 1);
                                System.out.println("The balance of user" + op + "is now : " + x.getUserBalance(op));
                                doSB("The balance of user" + op + "is now : " + x.getUserBalance(op) + "as well as the cooperatives bank account is now: " +x.getUserBalance(1));
                            } else {
                                System.out.println("press a valid option");
                            }
                        } else {
                            System.out.println("non existent user");
                        }
                        break;

                    case 5:
                        System.out.println("you create a file is in your desktop with today's transaction");
                        FileAccess.createFile();
                        FileAccess.writeToAFile(sb.toString());
                        break;
                }

            } else {
                System.out.println("press a valid option");
        }

    }

    if (x.getId () 
        != 1) {
            if (option > 0 && option < 5) {
            switch (option) {
                case 1:
                    System.out.println("your balance in your account is : " + x.getUserBalance(x.getId()));
                    doSB("your balance in your account is : " + x.getUserBalance(x.getId()));
                    break;
                case 2:
                    System.out.println("you can now deposit to cooperatives bank account");
                    System.out.println("amount: ");
                    double a = input.nextDouble();
                    double b = x.getUserBalance(1);
                    double amount = validateAmount(a, b);
                    database.deposit(amount, 1);
                    database.withdraw(amount, x.getId());
                    System.out.println("The balance in your account is now : " + x.getUserBalance(x.getId()));
                    doSB("The balance in your account is now : " + x.getUserBalance(x.getId()) + "you deposit in cooperatives bank account : " + amount + " euro");
                    break;
                case 3:
                    System.out.println("you can now deposit to a member account");
                    System.out.println("to which user you want to deposit? ");
                    int opt = input.nextInt();
                    if (opt != 1 && opt != x.getId()) {
                        System.out.println("amount: ");
                        double m = input.nextDouble();
                        double w = x.getUserBalance(x.getId());
                        double money = validateAmount(m, w);
                        database.deposit(money, x.getId());
                        database.withdraw(money, x.getId());
                        System.out.println("The user " + opt + " now has " + money + " more euro in his account");
                        doSB("The user " + opt + " now has " + money + " more euro in his account");
                    } else {
                        System.out.println("press a valid option");
                    }
                    break;
                case 4:
                    System.out.println("you create a file is in your desktop with today's transaction");
                    FileAccess.createFile();
                    FileAccess.writeToAFile(sb.toString());
                    break;
            }

        } else {
            System.out.println("press a valid option");
        }
    }
}

//return only positive amount of money in transactions are valid
private double validateAmount(double a,double b) 
    {if(a<0 || a>= b){
                System.out.println("not enough funds");
                return 0;
    }else{
        return a;
    }
       
   
    }
}
