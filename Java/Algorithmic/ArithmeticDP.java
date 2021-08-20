/* 
*   Given an array “A” of n elements, return true if you can form a valid math 
*    expression by inserting +, - or * between each of the elements, such that 
*    the result is a multiple of m. You can assume left to right precedence.
*   Input :
*    Operations : +, -, *
*    A : [1, 4, 9]
*    m = 15
*    Output :  True [1+4*9 = 45]
*
*    A : [18, 16, 17, 5, 7, 12, 11, 3]
*    m = 381
*    Output :  False
*/
import java.util.*;

public class ArithmeticDP {
    public static void main(String[] args) {
        /*int[] Ar = {14, 7, 19, 8, 9, 14};
        System.out.println("For input Array : " + Arrays.toString(Ar));
        int m = 233;
        System.out.println(dp(Ar, m));*/
        inputArrayWithFalse();
    }

    private static void inputArrayWithFalse() {
        final int ARRAY_SIZE = 8;
        final int MAX_N_LOOKUP_VALUE = 1000;

        boolean flag = true;
        while (flag) {
            int[] Ar = _generateRandom(ARRAY_SIZE);
            List<Integer> result = new ArrayList<>();
            for (int n=1; n<MAX_N_LOOKUP_VALUE && result.size()<10; n++) {
                if (!dp(Ar, n))
                    result.add(n);
            }
            if (!result.isEmpty()) {
                flag = false;
                System.out.println("For input Array : " + Arrays.toString(Ar));
                System.out.println("Possible false answers : " + result);
            }
        }
    }

    private static int[] _generateRandom(int size) {
        int[] Ar = new int[size];
        Random r = new Random();
        for (int i = 0; i < Ar.length; i++) {
            Ar[i] = r.nextInt(3 * size);
        }
        return Ar;
    }

    /**
     * Trick is that in {E1, E2, E3...} :> E1 () E2 % m = [E1 % m () E2 % m] % m
     *  if(dp[j][i]) dp[i+1][(i {} A[j]) % m] = true
     * Time Complexity : O(n.m)
     * Space Complexity : O(m)
     */
    private static boolean dp(int[] A, int m) {
        if (A == null || m <= 0)
            return false;

        boolean[][] dp = new boolean[2][m];
        dp[1][A[0]%m] = true;

        for (int j = 1; j < A.length; j++) {
            for (int k = 0; k < m; k++) {
                dp[(j + 1) % 2][k] = false;
            }
            for (int i = 0; i < m; i++) {
                if (dp[j % 2][i]) {
                    dp[(j + 1) % 2][(i + A[j]) % m] = true;
                    dp[(j + 1) % 2][(i - A[j] + m) % m] = true;
                    dp[(j + 1) % 2][(i * A[j]) % m] = true;
                }
            }
            //System.out.println(Arrays.toString(dp[(j+1)%2]));
        }

        return dp[A.length % 2][0];
    }
}
