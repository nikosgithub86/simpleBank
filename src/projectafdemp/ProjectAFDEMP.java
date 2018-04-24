package projectafdemp;

import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ProjectAFDEMP {

    public static void main(String[] args) throws SQLException, FileNotFoundException, UnsupportedEncodingException {
        Database d = new Database();
        Menu menu = new Menu(d);
        d.createConnection();
        User user;
        Scanner input = new Scanner(System.in);
        while (true) {

            System.out.println("name: ");
            String name = input.nextLine();
            System.out.println("password: ");
            String pass = input.nextLine();
            user = new User(name, pass, d);
            if (user.login() != -1) {
                break;
            }

        }

        Menu.showOptions(user);
        while (true) {
            System.out.println("press an option: ");
            int option = input.nextInt();

            if (option < 6) {
                menu.enterOption(user, option);
            } else  {
                break;
            }

        }
    }

}
