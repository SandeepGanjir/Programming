/* DE Shaw Interview Question:
*   A cab driver wants to maximize his earning from the trip where customer pays 
*    charges = destination - source + tip
*   Input :
*    Sources :      {0, 2,  9, 10, 11, 12}
*    Destination :  {5, 9, 11, 11, 14, 17}
*    Tips :         {1, 2,  3,  2,  2,  1}
*   Output :    20
*/

import java.util.*;

public class BestRoute {
    private int maxIncome(int[] src, int[] dest, int[] tips) {
        int lastPoint = 0;
        for (int i=0; i<dest.length; i++) {
            if (dest[i] > lastPoint)
                lastPoint = dest[i];
        }
        int[] incomes = new int[lastPoint+1];
        for (int i=0; i<src.length; i++) {
            int incomeFromCurrTrip = dest[i] - src[i] + tips[i];
            int incomeBeforeCurrTrip = 0;
            for (int j=src[i]; j>=0; j--) {
                if(incomes[j] > incomeBeforeCurrTrip)
                incomeBeforeCurrTrip = incomes[j];
            }
            incomes[dest[i]] = Math.max(incomes[dest[i]], incomeBeforeCurrTrip+incomeFromCurrTrip);
        }
        int maxIncome = 0;
        for (int i=0; i<incomes.length; i++) {
            maxIncome = Math.max(maxIncome, incomes[i]);
        }
        return maxIncome;
    }

    public static void main(String args[]) {
        BestRoute ins = new BestRoute();
        int[] src = {0, 2, 9, 10, 11, 12};
        int[] dest = {5, 9, 11, 11, 14, 17};
        int[] tips = {1, 2, 3, 2, 2, 1};
        System.out.println("The maximum income the driver can make from the given customer routes : ");
        System.out.println("Source : " + Arrays.toString(src) + "\nDestination: " + Arrays.toString(dest) + "\nTips : " + Arrays.toString(tips));
        System.out.println("Max Income : " + ins.maxIncome(src, dest, tips));
    }
}