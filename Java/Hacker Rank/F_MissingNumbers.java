/*
 * Numeros, the Artist, had two lists A and B, such that B was a permutation of
 * A. Numeros was very proud of these lists. Unfortunately, while transporting
 * them from one exhibition to another, some numbers were left out of A. Can you
 * find the missing numbers?
 */


public class F_MissingNumbers {
    static int[] missingNumbers(int[] arr, int[] brr) {
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < brr.length; i++)
            if (brr[i] < min)
                min = brr[i];

        int[] occur = new int[101];
        for (int i = 0; i < brr.length; i++)
            occur[brr[i] - min]++;
        for (int i = 0; i < arr.length; i++)
            occur[arr[i] - min]--;

        int count = 0;
        for (int i = 0; i < occur.length; i++)
            if (occur[i] > 0)
                count++; // Cause no need for duplicates otherwise count+=occur[i];*/

        int[] result = new int[count];
        int k = 0;
        // for (int i=0; i<occur.length; i++) for (int j=0; j<occur[i]; j++) result[k++] = min + i;
        for (int i = 0; i < occur.length; i++)
            if (occur[i] > 0)
                result[k++] = min + i;
        return result;
    }

    public static void main(String[] args) {
        int[] arr = { 203, 204, 205, 206, 207, 208, 203, 204, 205, 206 };
        int[] brr = { 203, 204, 204, 205, 206, 207, 205, 208, 203, 206, 205, 206, 204 };

        int[] result = missingNumbers(arr, brr);

        for (int i = 0; i < result.length; i++) {
            System.out.print(result[i] + (i != result.length - 1 ? " " : ""));
        }
    }
}