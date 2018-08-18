/*
 * Hackerland is a one-dimensional city with houses, where each house is located
 * at some xi on the x-axis. The Mayor wants to install radio transmitters on
 * the roofs of the city's houses. Each transmitter has a range, k, meaning it
 * can transmit a signal to all houses <= k units of distance away. Given a map
 * of Hackerland and the value of k, can you find and print the minimum number
 * of transmitters needed to cover every house in the city? (Every house must be
 * covered by at least one transmitter) Each transmitter must be installed on
 * top of an existing house.
 * 
 * Input Format: The first line contains two space-separated integers describing
 * the respective values of n (the number of houses in Hackerland) and k (the
 * range of each transmitter). The second line contains space-separated integers
 * describing the respective locations of each house (i.e., x1, x2, ... xn).
 * E.g. 8 2
 *      7 2 4 6 5 9 12 11
 * Output: 3
 */

import java.util.*;

public class E_RadioTransmitters {
    static int hackerlandRadioTransmitters(int[] x, int k) {
        int count = 0;
        int n = x.length;
        Arrays.sort(x);
        for (int i = 0; i < n; i++) {
            int j = i;
            for (j = i; j < n && (x[j] - x[i] <= k); j++)
                ;
            i = --j;
            count++;
            for (j = i; j < n && (x[j] - x[i] <= k); j++)
                ;
            i = --j;
        }
        return count;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int k = in.nextInt();
        int[] x = new int[n];
        for (int x_i = 0; x_i < n; x_i++) {
            x[x_i] = in.nextInt();
        }
        int result = hackerlandRadioTransmitters(x, k);
        System.out.println(result);
        in.close();
    }
}