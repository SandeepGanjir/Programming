/* Google Interview Question:
*   One queue is given. Each person in queue has two information his height, and number of persons standing ahead of him taller than him. Some how queue got broken. You have to recreate same queue with that information.
*   Input :              Height : 155, 165, 158, 170, 173, 175, 178, 179
*       number of taller persons standing ahead : 7, 2, 0, 0, 3, 0, 1, 0
*   Output :        158, 170, 175, 165, 179, 178, 173, 155,
*/

import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;


public class RebuildQueue {

    public static void main(String[] args) {
        RebuildQueue instance = new RebuildQueue();
        Queue<Person> queue = instance.inputQueue();
        
        System.out.println("Each person in queue has two information his height, and number of persons standing ahead of him taller than them");
        System.out.println(" Given List");
        instance.printQueue(queue);

        System.out.println("\n After Fixing the Queue");
        instance.printQueue(instance.getOriginalQueue(queue));
    }

    public Queue<Person> getSortedQueue(Queue<Person> p_Queue) {
        Queue<Person> queue = p_Queue;
        Collections.sort((LinkedList) queue, (Person P1, Person P2) -> {
            if (P1.getPeopleWithMoreHeight() == P2.getPeopleWithMoreHeight()) {
                return P2.getHeight() - P1.getHeight();
            }
            return P1.getPeopleWithMoreHeight() - P2.getPeopleWithMoreHeight();
        });
        return queue;
    }

    public Queue<Person> getOriginalQueue(Queue<Person> p_Queue) {
        LinkedList<Person> result = new LinkedList<>();
        Queue<Person> queue = getSortedQueue(p_Queue);
        for (Person next : queue) {
            int height = next.getHeight();
            int i = next.getPeopleWithMoreHeight();
            int j = 0;
            while (i > 0) {
                if (result.get(j).getHeight() > height) {
                    --i;
                }
                j++;
            }
            result.add(j, next);
        }
        return (Queue) result;
    }

    private Queue<Person> inputQueue() {
        Queue<Person> queue = new LinkedList<>();
        queue.add(new Person(155, 7));
        queue.add(new Person(165, 2));
        queue.add(new Person(158, 0));
        queue.add(new Person(170, 0));
        queue.add(new Person(173, 3));
        queue.add(new Person(175, 0));
        queue.add(new Person(178, 1));
        queue.add(new Person(179, 0));
        return queue;
    }

    private void printQueue(Queue<Person> queue) {
        queue.forEach((next) -> {
            System.out.print("(" + next.getHeight() + ", " + next.getPeopleWithMoreHeight() + "); ");
        });
    }
}

class Person {
    private final int height;
    private final int rank;
    
    Person(int p_height, int p_rank){
        height = p_height;
        rank = p_rank;
    }

    public int getHeight() {
        return height;
    }

    public int getPeopleWithMoreHeight() {
        return rank;
    }
}