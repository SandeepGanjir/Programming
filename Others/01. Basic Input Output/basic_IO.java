// Small Program to Read a user's name and greet them with Abbreviated name.
/*  Use as below:
    G:\Temp\Programming> javac basic_IO.java
    G:\Temp\Programming> java basic_IO
*/

import java.io.FileReader;
import java.util.Scanner;
import java.util.StringTokenizer;

public class basic_IO {
    public String abbreviateName(String p_Name) {
        String result = null;
        StringTokenizer st = new StringTokenizer(p_Name);
        while (st.hasMoreTokens()) {
            String token = st.nextToken().toString();
            if (result == null)
                result = token.charAt(0) + ".";
            else
                result += " " + token.charAt(0) + ".";
        }
        return result;
    }

    public void greetFromUserInput() {
        Scanner ins = new Scanner(System.in);
        String name = "";
        System.out.println("\nUser Input Mode: ");
        System.out.print("Please Enter Your Name: ");
        name = ins.nextLine();
        System.out.println("Good Morning " + abbreviateName(name) + "! How are you today?");
        ins.close();
    }

    public void greetFromFileInput() {
        String name = null;
        try {
            Scanner ins = new Scanner(new FileReader("input.txt"));
            name = ins.nextLine();
            ins.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        System.out.println("\nFile Input Mode: ");
        System.out.println("Good Evening " + abbreviateName(name) + "! How are you today?");
    }

    public static void main(String[] args) {
        basic_IO Ins = new basic_IO();
        Ins.greetFromUserInput();
        Ins.greetFromFileInput();
    }
}