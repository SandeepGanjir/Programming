/*
 * A simple implementation of AVL Tree.
 *  @author Sandeep Ganjir
 */

import java.util.Comparator;
import java.util.Scanner;
import java.io.StringReader;

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

        boolean find(int data) {
            if (comparator.compare(data, this.data) == 0) return true;
            if (comparator.compare(data, this.data) < 0)
                return this.leftChild == null ? false : this.leftChild.find(data);
            else
                return this.rightChild == null ? false : this.rightChild.find(data);
        }

        AvlNode insert(int data) {
            if (comparator.compare(data, this.data) == 0) return this;
            if (comparator.compare(data, this.data) < 0)
                leftChild = leftChild == null ? new AvlNode(data) : leftChild.insert(data);
            else
                rightChild = rightChild == null ? new AvlNode(data) : rightChild.insert(data);
            return rebalance();
        }

        AvlNode delete(int data, AvlNode nodeToDel) {
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

    public boolean find(int data) {
        return root == null ? false : root.find(data);
    }

    public void insert(int data) {
        root = root == null ? new AvlNode(data) : root.insert(data);
    }

    public void delete(int data) {
        root = root == null ? null : root.delete(data, null);
    }

    public void processRequest() {
        char inp = 'c';
        Scanner scanner = new Scanner(System.in);
        String msg = "##########################################################\n" +
                "Enter (f, i, I, d, D, q) to denote your choice :\n" +
                "1. f to search for element in the AVL tree\n" +
                "2. i to insert an element\n" +
                "3. I to insert multiple elements e.g. 15 11 12 7 9 5 5 3 10\n" +
                "4. d to delete an element\n" +
                "5. D to delete all the elements\n" +
                "6. q to quit the program\n" +
                "##########################################################";

        System.out.println(msg);
        while (inp != 'q') {
            System.out.print("\nEnter f, i, I, d, D or q : ");
            inp = scanner.next().charAt(0);
            switch (inp) {
                case 'q':
                    scanner.close();
                    System.exit(0);
                case 'f':
                    System.out.print("Enter input to search: ");
                    System.out.println(find(scanner.nextInt()));
                    break;
                case 'i':
                    System.out.print("Enter input to insert: ");
                    insert(scanner.nextInt());
                    break;
                case 'I':
                    System.out.print("Enter space separated integers to insert: ");
                    scanner.nextLine();
                    String inputString = scanner.nextLine();
                    for (Scanner sc = new Scanner(new StringReader(inputString)); sc.hasNextInt(); )
                        insert(sc.nextInt());
                    break;
                case 'd':
                    System.out.print("Enter input to delete: ");
                    delete(scanner.nextInt());
                    break;
                case 'D':
                    root = null;
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
    
/*  final int port = new Random().nextInt(65500);
    Vertx vertx = Vertx.vertx();
    vertx.createHttpServer().requestHandler(req -> req.response().end(Json.encodePrettily(avlTree.root))).listen(port);
    System.out.println("Check out the tree at: http://localhost:" + port);
*/
}
