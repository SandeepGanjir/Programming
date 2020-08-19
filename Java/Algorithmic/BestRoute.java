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
    private long maxIncome(List<Long> src, List<Long> dest, List<Long> tips) {
        long lastPoint = 0;
        for (int i = 0; i < dest.size(); i++) {
            if (dest.get(i) > lastPoint)
                lastPoint = dest.get(i);
        }
        long[] incomes = new long[(int) (lastPoint + 1)];
        for (int i = 0; i < src.size(); i++) {
            long incomeFromCurrTrip = dest.get(i) - src.get(i) + tips.get(i);
            long incomeBeforeCurrTrip = 0;
            for (int j = src.get(i).intValue(); j >= 0; j--) {
                if (incomes[j] > incomeBeforeCurrTrip)
                    incomeBeforeCurrTrip = incomes[j];
            }
            int destIdx = dest.get(i).intValue();
            incomes[destIdx] = Math.max(incomes[destIdx], incomeBeforeCurrTrip + incomeFromCurrTrip);
        }
        long maxIncome = 0;
        for (int i = 0; i < incomes.length; i++) {
            maxIncome = Math.max(maxIncome, incomes[i]);
        }
        return maxIncome;
    }

    public static void main(String args[]) {
        BestRoute ins = new BestRoute();
        List<Long> src = Arrays.asList(0l, 2l, 9l, 10l, 11l, 12l);
        List<Long> dest = Arrays.asList(5l, 9l, 11l, 11l, 14l, 17l);
        List<Long> tips = Arrays.asList(1l, 2l, 3l, 2l, 2l, 1l);
        System.out.println("The maximum income the driver can make from the given customer routes : ");
        System.out.println("Source : " + src + "\nDestination: " + dest + "\nTips : " + tips);
        System.out.println("Max Income : " + ins.maxIncome(src, dest, tips));
    }
}