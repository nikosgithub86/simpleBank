package projectafdemp;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Formatter;
import java.util.Scanner;

public class FileAccess {

    public FileAccess() {
    }

    public static DateFormat df = new SimpleDateFormat("yyyy_MM_dd");
// that method creates a file in your Desktop and the name of the file depend of the date 
    public static void createFile() {
        final Formatter x;
        try {
            x = new Formatter("C:\\Users\\Nikolaos\\Desktop\\" + df.format(new Date()) + ".txt");
        } catch (Exception e) {
            System.out.println("you ve got an error");
        }
    }
// since you create a file, you can insert data into that file
    public static void writeToAFile(String x) throws FileNotFoundException, UnsupportedEncodingException {
        Scanner sc = new Scanner(System.in);

        PrintWriter writer = new PrintWriter("C:\\Users\\Nikolaos\\Desktop\\" + df.format(new Date()) + ".txt", "UTF-8");
        writer.append(x);

        writer.close();
    }

//    public static String convertToString(double x) {
//        return ("the ammount of money in that account is: " + x);
//
//    }

}
