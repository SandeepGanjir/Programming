/*
 * Given an array of integers, calculate which fraction of its elements are
 * positive, which fraction of its elements are negative, and which fraction of
 * its elements are zeroes, respectively. Print the decimal value (6 digits of
 * precision) of each fraction on a new line.
 * 
 * Input: 
 * The first line contains an integer, N, denoting the size of the array.
 * The second line contains space-separated integers describing an array of
 * numbers a1, a2, ...aN.
 */

import java.util.*;
import java.text.*;

public class B_Number_Formatting {
    static void printFraction(int num, int den) {
        DecimalFormat df = new DecimalFormat("0.000000");
        System.out.println(df.format((double) num / den));
    }

    static void plusMinus(int[] arr) {
        // Complete this function
        int n = arr.length;
        int noPositive = 0;
        int noNegative = 0;
        int noZeros = 0;
        for (int i = 0; i < n; i++) {
            if (arr[i] > 0)
                noPositive++;
            else if (arr[i] < 0)
                noNegative++;
            else
                noZeros++;
        }
        printFraction(noPositive, n);
        printFraction(noNegative, n);
        printFraction(noZeros, n);
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[] arr = new int[n];

        for (int arr_i = 0; arr_i < n; arr_i++) {
            arr[arr_i] = in.nextInt();
        }

        plusMinus(arr);
        in.close();
    }
}