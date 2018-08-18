/* Flipkart Coding Round Question.
 * 
 * You are given csv files and you have to implement apis to provide following functions
 *  1. Sort by given column ascending/descending
 *  2. Join 2 files by given columns
 *  3. Perform set oprations: union, intersection, difference
 *  4. fgrep for searching contents of one file in other
 *  Bonus: operation can be chained
 */

import java.util.ArrayList;
import java.util.Map;
import java.util.NavigableMap;
import java.util.TreeMap;

/**
 *
 * @author sandeepganjir
 */
public class CSVoperations {

    static String f1[][] = {{"Sandeep", "AC", "123456"}, {"Naveen", "NA", "asnfdde"}};
    static String f2[][] = {{"fdsgAABCC", "Clear"}, {"na",  "Absent"}};
    static String f3[][] = {{"AC", "Clear"}, {"AB", "Gcaskjd"}, {"BH", "ewokjda"}};

    /*
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        CSVoperations sg = new CSVoperations();
        
        sg.print(sg.sort(f1, 3, true));  // Sort
        System.out.println("Sort Finished");
        System.out.println("");

        String[][] res = sg.join(f1, f2, 2, 1); // Join
        sg.print(res);
        System.out.println("Join Finished");
        System.out.println("");

        sg.comm(f2, f3, "O1"); // Comm
        System.out.println("O1 Finished");
        System.out.println("");
        sg.comm(f2, f3, "O2");
        System.out.println("O2 Finished");
        System.out.println("");
        sg.comm(f2, f3, "O3");
        System.out.println("O3 Finished");
        System.out.println("");
        sg.comm(f2, f3, "O4");
        System.out.println("O4 Finished");
        System.out.println("");

        sg.fgrep(f1, f2);
    }

    /*################# Public Methods #################*/
    public String[][] sort(String[][] f1, int j, boolean isAscending) {
        String[] ar = new String[f1.length];
        for (int i = 0; i < f1.length; i++) {
            ar[i] = f1[i][j - 1];
        }
        TreeMap<String, Integer> tm = new TreeMap<>();
        for (int i = 0; i < ar.length; i++) {
            tm.put(ar[i], i);
        }
        String[][] result;
        result = new String[f1.length][f1[0].length];
        int k = 0;
        NavigableMap<String, Integer> rm = isAscending ? tm : tm.descendingMap();
        for (Map.Entry<String, Integer> entry : rm.entrySet()) {
            //String key = entry.getKey();
            Integer value = entry.getValue();
            result[k++] = f1[value];
        }
        return result;
    }

    public String[][] join(String[][] f1, String[][] f2, int c1, int c2) {
        String[][] result = null;
        ArrayList<String[]> ar = new ArrayList<>();
        for (int i = 0; i < f1.length; i++) {
            for (int j = 0; j < f2.length; j++) {
                if (f1[i][c1 - 1].equals(f2[j][c2 - 1])) {
                    String[] res = takejoin(f1[i], f2[j], c2 - 1);
                    ar.add(res);
                }
            }
        }
        result = ar.toArray(new String[ar.size()][]);
        return result;
    }

    public void comm(String[][] f1, String[][] f2, String opr) {
        switch (opr) {
            case "O1":
                printAminusB(f1, f2);
                break;
            case "O2":
                printAminusB(f2, f1);
                break;
            case "O3":
                printAintersectB(f1, f2);
                break;
            case "O4":
                printAunionB(f1, f2);
                break;
        }
    }

    /*################# Private Methods #################*/
    private void print(String[] l1) {
        for (int j = 0; j < l1.length; j++) {
            System.out.print(" " + l1[j]);
        }
        System.out.println("");
    }

    private void print(String[][] f1) {
        for (int i = 0; i < f1.length; i++) {
            /*for (int j = 0; j < f1[i].length; j++) {
                System.out.print(" " + f1[i][j]);
            }*/
            print(f1[i]);
        }
    }

    private String[] takejoin(String[] ar1, String[] ar2, int dupIdx) {
        String[] res = new String[ar1.length + ar2.length - 1];
        int k = 0;
        for (int i = 0; i < ar1.length; i++) {
            res[k++] = ar1[i];
        }
        for (int j = 0; j < ar2.length; j++) {
            if (j != dupIdx) {
                res[k++] = ar2[j];
            }
        }
        return res;
    }

    private void printAminusB(String[][] f1, String[][] f2) {
        for (int i = 0; i < f1.length; i++) {
            if (notPresent(f1[i], f2)) {
                print(f1[i]);
            }
        }
    }

    private boolean notPresent(String[] ar, String[][] f2) {
        boolean flag = true;
        for (int i = 0; i < f2.length; i++) {
            boolean isAllMatch = true;
            for (int j = 0; j < f2[i].length; j++) {
                if (!f2[i][j].equals(ar[j])) {
                    isAllMatch = false;
                }
            }
            if (isAllMatch) {
                flag = false;
                break;
            }
        }
        return flag;
    }

    private void printAintersectB(String[][] f1, String[][] f2) {
        for (int i = 0; i < f1.length; i++) {
            if (!notPresent(f1[i], f2)) {
                print(f1[i]);
            }
        }
    }

    private void printAunionB(String[][] f1, String[][] f2) {
        printAminusB(f1, f2);
        print(f2);
    }

    private void fgrep(String[][] f1, String[][] f2) {
        for (int i = 0; i < f2.length; i++) {
            if (isSubStringIn(f1, f2[i])) {
                print(f2[i]);
            }
        }
    }

    private boolean isSubStringIn(String[][] f1, String[] ar) {
        boolean isAnyMatch = false;
        for (int i = 0; i < f1.length; i++) {
            for (int j = 0; j < f1[i].length; j++) {
                for (int k = 0; k < ar.length; k++) {
                    int idx = f1[i][j].indexOf(ar[k]);
                    if (idx >= 0) {
                        isAnyMatch = true;
                        break;
                    }
                }
            }
        }
        return isAnyMatch;
    }
}
