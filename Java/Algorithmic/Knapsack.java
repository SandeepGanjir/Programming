/* 0-1 Knapsack problem:
*   Given weights and values of n items, put these items in a knapsack  
*    of capacity W to get the maximum total value in the knapsack.
*   Input :
*    Values : {20, 5, 10, 40, 15, 25}
*    Weights : {1, 2, 3, 8, 7, 4}
*    Capacoty : 10
*   Output :    60
*/

public class Knapsack {
    private static int knapsack(int capacity, int[] weights, int[] values) {
        return capacity;
    }

    public static void main(String[] var0) {
        int[] values = new int[]{60, 100, 120};
        int[] weights = new int[]{10, 20, 30};
        int capacity = 50;
        System.out.println("Knapsack solution for given data is : " + knapsack(capacity, weights, values));
    }
}
