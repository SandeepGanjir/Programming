/* 
*   Given an array “a” of n elements, return true if you can form a valid math 
*    expression by inserting +, - or * between each of the elements, such that 
*    the result is a multiple of m. You can assume left to right precedence.
*   Input :
*    Operations : +, -, *
*    Weights : [1, 4, 9]
*    m = 15
*   Output :  True [1+4*9 = 45]
*
*    m = 17 -> False
*/
import java.util.Arrays;

public class ArithmeticDP {
    public static void main(String[] args) {
        int[] Ar = {1, 4, 9};
        int m = 15;
        System.out.println(dp(Ar, m));
    }

    /**
     * Trick is that in {E1, E2, E3...} :> E1 (?) E2 % m = [E1 % m (?) E2 % m] % m
     * If (dp[j][i])
     *    dp[j + 1][(i (?) A[j]) % m] = true;
     */
    private static boolean dp(int[] A, int m) {
        if (A == null || m < 0)
            return false;

        boolean[][] dp = new boolean[2][m];
        dp[1][A[0]] = true;

        for (int j = 1; j < A.length; j++) {
            for (int k = 0; k < m; k++) {
                dp[(j + 1) % 2][k] = false;
            }
            for (int i = 0; i < m; i++) {
                if (dp[j % 2][i] == true) {
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