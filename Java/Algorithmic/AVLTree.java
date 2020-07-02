/*
 * A simple implementation of AVL Tree.
 *  @author Sandeep Ganjir
 */

import java.util.Comparator;
import java.util.Scanner;

public class AVLTree {
    class AvlNode {
        private int data;
        private int height = 0;
        private AvlNode leftChild = null;
        private AvlNode rightChild = null;
        private final Comparator<Integer> comparator = Integer::compareTo;

        AvlNode(int data) {
            this.data = data;
        }

        public int value(){
            return data;
        }

        public boolean find(int data) {
            if (comparator.compare(data, this.data) == 0) return true;
            if (comparator.compare(data, this.data) < 0)
                return this.leftChild == null ? false : this.leftChild.find(data);
            else
                return this.rightChild == null ? false : this.rightChild.find(data);
        }

        public AvlNode insert(int data) {
            if (comparator.compare(data, this.data) == 0) return this;
            if (comparator.compare(data, this.data) < 0)
                leftChild = leftChild == null ? new AvlNode(data) : leftChild.insert(data);
            else
                rightChild = rightChild == null ? new AvlNode(data) : rightChild.insert(data);
            return rebalance();
        }

        public AvlNode delete(int data, AvlNode nodeToDel) {
            if (comparator.compare(data, this.data) == 0) {
                nodeToDel = this;
            }
            if (leftChild == null && rightChild == null && nodeToDel != null) {
                nodeToDel.data = this.data;
                return null;
            }
            if (comparator.compare(data, this.data) < 0)
                leftChild = leftChild == null ? null : leftChild.delete(data, nodeToDel);
            else
                rightChild = rightChild == null ? null : rightChild.delete(data, nodeToDel);
            return rebalance();
        }

        private AvlNode rebalance() {
            AvlNode rotNewCur;
            if (leftHeight() - rightHeight() > 1) {
                if (leftChild.leftHeight() > leftChild.rightHeight()) {
                    rotNewCur = leftChild;
                    leftChild = rotNewCur.rightChild;
                    rotNewCur.rightChild = this;
                } else {
                    rotNewCur = leftChild.rightChild;
                    leftChild.rightChild = rotNewCur.leftChild;
                    rotNewCur.leftChild = leftChild;
                    leftChild = rotNewCur.rightChild;
                    rotNewCur.rightChild = this;
                    rotNewCur.setHeight();
                }
            } else if (rightHeight() - leftHeight() > 1) {
                if (rightChild.rightHeight() > rightChild.leftHeight()) {
                    rotNewCur = rightChild;
                    rightChild = rotNewCur.leftChild;
                    rotNewCur.leftChild = this;
                } else {
                    rotNewCur = rightChild.leftChild;
                    rightChild.leftChild = rotNewCur.rightChild;
                    rotNewCur.rightChild = rightChild;
                    rightChild = rotNewCur.leftChild;
                    rotNewCur.leftChild = this;
                    rotNewCur.setHeight();
                }
            } else {
                rotNewCur = this;
            }
            setHeight();
            return rotNewCur;
        }

        private int leftHeight() {
            return leftChild == null ? -1 : leftChild.height;
        }

        private int rightHeight() {
            return rightChild == null ? -1 : rightChild.height;
        }

        private void setHeight() {
            height = Math.max(leftHeight(), rightHeight()) + 1;
        }
    }

    private AvlNode root = null;

    public void insert(int data) {
        root = root == null ? new AvlNode(data) : root.insert(data);
    }

    public void delete(int data) {
        root = root == null ? null : root.delete(data, null);
    }

    public void processRequest() {
        char inp = 'c';
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter inputs to do operation on AVL Tree");
        while (inp != 'q') {
            System.out.println("f, i, d, q");
            inp = scanner.next().charAt(0);
            switch (inp) {
                case 'q':
                    System.exit(0);
                case 'f':
                    System.out.print("Enter input to search: ");
                    System.out.println(root.find(scanner.nextInt()));
                    break;
                case 'i':
                    System.out.print("Enter input to insert: ");
                    insert(scanner.nextInt());
                    break;
                case 'd':
                    System.out.print("Enter input to delete: ");
                    delete(scanner.nextInt());
                    break;
            }
        }
    }

    private void loadSampleData() {
        int[] Ar = {15, 11, 12, 7, 9, 5, 5, 3, 10};
        for (int i = 0; i < Ar.length; i++)
            insert(Ar[i]);
    }

    public static void main(String[] args) {
        AVLTree avlTree = new AVLTree();
        avlTree.loadSampleData();
        avlTree.processRequest();
    }
}
