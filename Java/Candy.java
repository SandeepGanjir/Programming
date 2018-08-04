/*
 * A teacher must distribute chocolates in class such that every student must
 * get at least 1 chocolate and student with higher score than their neighbour
 * should get atleast 1 extra chocolate than respective neighbour. Find the
 * distribution such that teacher will nead least amount of chocolates.
 *  @author Sandeep Ganjir
 */
//package candy;

import java.util.Arrays;
import java.util.Random;
import java.util.Stack;

public class Candy {

    //@param args the command line arguments
    public static void main(String[] args) {
        Candy instance = new Candy();
        instance._testCase1();
        instance._testCase2();
    }

    private void _testCase1() {
        int Ar[] = {5, 4, 2, 7, 8, 1, 9, 7, 2};
        int Res[] = getCandyDistribution(Ar);
        System.out.println("Fixed Case");
        System.out.println("Score distribution : " + Arrays.toString(Ar));
        System.out.println("Candy distribution : " + Arrays.toString(Res));
    }

    private void _testCase2() {
        int Ar[] = _generateRandom(10);
        int Res[] = getCandyDistribution(Ar);
        System.out.println("Random Case");
        System.out.println("Score distribution : " + Arrays.toString(Ar));
        System.out.println("Candy distribution : " + Arrays.toString(Res));
    }

    public int[] getCandyDistribution(int[] Ar) {
        int Res[] = new int[Ar.length];
        Stack<Integer> stack = new Stack<>();

        stack.push(Ar[0]);
        for (int i = 1; i < Ar.length; i++) {
            if (Ar[i] > stack.peek()) {
                _putCandy(Res, stack, Ar, i - 1);
            }
            stack.push(Ar[i]);
        }
        _putCandy(Res, stack, Ar, Ar.length - 1);
        return Res;
    }

    private static void _putCandy(int[] Res, Stack<Integer> st, int[] Ar, int last) {
        int k = 1;
        int i = last;
        while (!st.isEmpty()) {
            st.pop();
            if (st.isEmpty() && i > 0 && Res[i - 1] >= k) {
                Res[i] = Res[i - 1] + 1;
            } else {
                Res[i] = k++;
            }
            i--;
        }
    }

    private int[] _generateRandom(int size) {
        int[] Ar = new int[size];
        Random r = new Random();
        for (int i = 0; i < Ar.length; i++) {
            Ar[i] = r.nextInt(2 * size);
        }
        return Ar;
    }
}
