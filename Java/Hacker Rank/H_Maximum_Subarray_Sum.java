/*
 * Given an n-element array of integers, A, and an integer, m, determine the
 * maximum value of the sum of any of its subarrays modulo m. This means that
 * you must find the sum of each subarray %m, then print the maximum result of
 * this modulo operation for any of the n(n+1)/2 possible subarrays.
 * 
 * Input Format: The first line contains an integer, q, denoting the number of
 * queries to perform. Each query is described over two lines:
 * 1. The first line contains two space-separated integers describing the
 * respective n(the array length) and m(the right operand for the modulo
 * operations) values for the query.
 * 2. The second line contains space-separated integers describing the
 * respective elements of array A = a1, a2, ... an for that query.
 * 
 * Sample Input: 1
 *              5 7
 *              3 3 9 9 5
 * Output: 6
 */

import java.util.*;

public class H_Maximum_Subarray_Sum {
    static long maximumSum(long[] a, long m) {
        long result = 0;
        int n = a.length;
        long[] sumArr = new long[n];
        result = sumArr[0] = a[0] % m;
        TreeSet<Long> ts = new TreeSet<>();
        ts.add(sumArr[0]);
        for (int i = 1; i < n; i++) {
            sumArr[i] = (sumArr[i - 1] + a[i]) % m;
            ts.add(sumArr[i]);
            result = Math.max(result, sumArr[i]);
            Long rightNeighbour = ts.ceiling(sumArr[i] + 1l);
            if (rightNeighbour != null)
                result = Math.max(result, (sumArr[i] - rightNeighbour + m) % m);
        }
        return result;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int q = in.nextInt();
        for (int a0 = 0; a0 < q; a0++) {
            int n = in.nextInt();
            long m = in.nextLong();
            long[] a = new long[n];
            for (int a_i = 0; a_i < n; a_i++) {
                a[a_i] = in.nextLong();
            }
            long result = maximumSum(a, m);
            System.out.println(result);
        }
        in.close();
    }
}