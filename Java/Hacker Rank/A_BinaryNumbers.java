/*
 * Given a base-10 integer n, convert it to binary (base-2). Then find and print
 * the maximum number of consecutive 1's in its binary representation
 * 
 * Input: A single integer, n such that 1 <= n <= 1M Output: A single integer
 */

import java.util.*;

public class A_BinaryNumbers {
    public static String convertBaseTwo(int n) {
        String strResult = "";
        int num = n;
        while (n > 0) {
            strResult = n % 2 + strResult;
            n /= 2;
        }
        return strResult;
    }

    public static int getMaxOnes(String str) {
        int maxOnes = 0;
        StringTokenizer st1 = new StringTokenizer(str, "0");
        while (st1.hasMoreTokens()) {
            int ones = st1.nextToken().length();
            if (maxOnes < ones)
                maxOnes = ones;
        }
        return maxOnes;
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();

        String strBase2 = convertBaseTwo(n);
        int maxOnes = getMaxOnes(strBase2);
        System.out.println(maxOnes);

        scan.close();
    }
}