/*
 * Given n sticks of lengths l0, l1, ... ln, use of the sticks to construct a
 * non-degenerate triangle with the maximum possible perimeter. Then print the
 * lengths of its sides as space-separated integers in non-decreasing order.
 * 
 * If there are several valid triangles having the maximum perimeter, then
 * Choose the one with the longest maximum side, if more than one such triangle
 * meets the first criterion, choose the one with the longest minimum side.
 * 
 * Input Format: The first line contains single integer, n, denoting the number
 * of sticks. The second line contains space-separated integers, l0, l1, ... ln,
 * describing the respective stick lengths.
 * Sample Input: 6
 *              1 1 1 2 3 5
 * Output:      1 1 1
 */

import java.util.*;

public class G_MaximumPerimeterTriangle {
    static int[] maximumPerimeterTriangle(int[] l) {
        // Complete this function
        int[] sidesArr = new int[3];
        Arrays.sort(l);
        for (int i = l.length - 3; i > -1; i--) {
            if (l[i + 2] < l[i + 1] + l[i]) {
                System.arraycopy(l, i, sidesArr, 0, 3);
                break;
            }
        }
        return sidesArr;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[] l = new int[n];
        for (int l_i = 0; l_i < n; l_i++) {
            l[l_i] = in.nextInt();
        }
        int[] result = maximumPerimeterTriangle(l);
        if (result[0] != 0)
            for (int i = 0; i < result.length; i++) {
                System.out.print(result[i] + (i != result.length - 1 ? " " : ""));
            }
        else
            System.out.println("-1");
        in.close();
    }
}