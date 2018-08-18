/*
 * Write a program that prints a staircase of size n.
*/

import java.util.*;

public class C_Staircase {
    private static final char SPACE = ' ';
    private static final char SYMBOL = '#';

    static void staircase(int n) {
        String str = "";
        for (int i = 0; i < n; i++)
            str += SPACE;
        for (int i = 0; i < n; i++) {
            char[] stArr = str.toCharArray();
            stArr[n - 1 - i] = SYMBOL;
            str = String.valueOf(stArr);
            System.out.println(str);
        }
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        staircase(n);
        in.close();
    }
}