/*
 * Given an array, find maximum contigious sub array sum.
 * Input : {3, 16, -9, -15, 8, 13, -18, 7, 4, 9}
 * Output : Majority element (4) if exist, null otherwise
 * Expected Time Complexity: O(N).
 * Expected Auxiliary Space: O(1).
 *  @author Sandeep Ganjir
 */

import java.util.*;

public class KadaneAlgo {
    private int maxSubArraySum(int[] Ar) {
        int maxSoFar = 0;
        int currentSum = 0;
        for (int i = 0; i < Ar.length; i++) {
            currentSum += Ar[i];
            if (currentSum < 0)
                currentSum = 0;
            if (maxSoFar < currentSum)
                maxSoFar = currentSum;
        }
        return maxSoFar;
    }

    public static void main(String[] Args) {
        int[] Ar = {3, 16, -9, -15, 8, 13, -18, 7, 4, 9};
        KadaneAlgo ins = new KadaneAlgo();

        System.out.println("Maximum contigious sub array sum for : " + Arrays.toString(Ar));
        System.out.println(ins.maxSubArraySum(Ar));
    }
}