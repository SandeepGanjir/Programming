/*
 * A Binary Search Tree (BST) has the following properties: 1).Each node has a
 * unique value*. 2).A total order is defined on these values. 3).The left
 * subtree of a node contains only values less than the node's value. 4).The
 * right subtree of a node contains only values greater than the node's value.
 * 
 * You will be given an array of integers to insert into a BST that you create.
 * Complete the heightOfBST function below to return an integer array. The first
 * element should be the tree height of the BST and the second element should be
 * the sum of the heights of all of the BST nodes, the total height.
 * 
 * Input Format: In the first line you will be given an integer n which
 * represents the number of space-separated integers in the following line. In
 * the following line, there are space-separated integers a1, a2, ... an,
 * denoting the values to be inserted into the BST in this exact order.
 * 
 * Sample Input: 8
 *          500 400 300 450 425 475 600 550
 * Output: 3    7
 */

import java.io.*;
import java.util.*;

public class I_HeightOfBST {
    public static class Tree {
        public int value;
        public int height;
        public Tree leftChild;
        public Tree rightChild;

        public Tree(int val) {
            this.value = val;
            this.height = 0;
            this.leftChild = null;
            this.rightChild = null;
        }

        public int checkHeight() {
            int leftHeight = (this.leftChild != null) ? this.leftChild.height + 1 : 0;
            int rightHeight = (this.rightChild != null) ? this.rightChild.height + 1 : 0;
            height = Math.max(leftHeight, rightHeight);
            return height;
        }

        public Tree insert(int val) {
            Tree insNode;
            if (val == value)
                return this;
            else if (val < value) {
                if (this.leftChild == null) {
                    this.leftChild = new Tree(val);
                    insNode = this.leftChild;
                } else {
                    insNode = this.leftChild.insert(val);
                }
            } else {
                if (this.rightChild == null) {
                    this.rightChild = new Tree(val);
                    insNode = this.rightChild;
                } else {
                    insNode = this.rightChild.insert(val);
                }
            }
            checkHeight();
            return insNode;
        }

        public int sumHeights() {
            int leftHeights = (this.leftChild != null) ? this.leftChild.sumHeights() : 0;
            int rightHeights = (this.rightChild != null) ? this.rightChild.sumHeights() : 0;
            int sumHeights = leftHeights + rightHeights + this.height;
            return sumHeights;
        }
    }

    static int[] heightAndTotalHeight(int[] arr) {
        int[] result = new int[2];
        Tree root = new Tree(arr[0]);
        for (int i = 1; i < arr.length; i++) {
            root.insert(arr[i]);
        }
        result[0] = root.height;
        result[1] = root.sumHeights();
        return result;
    }

    private static final Scanner scan = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bw;
        //bw = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));
        //if (bw == null) {
            bw = new BufferedWriter(new OutputStreamWriter(System.out));
        //}
        int arrSize = Integer.parseInt(scan.nextLine().trim());
        int[] arr = new int[arrSize];
        String[] arrItems = scan.nextLine().split(" ");
        for (int arrItr = 0; arrItr < arrSize; arrItr++) {
            int arrItem = Integer.parseInt(arrItems[arrItr].trim());
            arr[arrItr] = arrItem;
        }
        int[] result = heightAndTotalHeight(arr);
        for (int resultItr = 0; resultItr < result.length; resultItr++) {
            bw.write(String.valueOf(result[resultItr]));
            if (resultItr != result.length - 1) {
                bw.write("\t");
            }
        }
        bw.newLine();
        bw.close();
    }
}