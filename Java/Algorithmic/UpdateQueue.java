import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class UpdateQueue<E> {
    private class Item {
        private final E element;
        private long scheduledAt;
        private long processedAt;
        private boolean queued;

        private Item(E element) {
            this.element = element;
            this.queued = false;
            this.processedAt = System.currentTimeMillis();
        }
    }

    private final long delay;
    private final PriorityQueue<Item> queue;
    private final Map<E, Item> items;

    public UpdateQueue(final int initialSize, final int fixedDelay) {
        this.queue = new PriorityQueue<>(initialSize, Comparator.comparingLong(i -> i.scheduledAt));
        items = new HashMap<>();
        this.delay = fixedDelay;
    }

    public synchronized boolean isEmpty() {
        return queue.isEmpty();
    }

    public synchronized void offer(final E element, final boolean immediate) {
        Item item = items.computeIfAbsent(element, Item::new);
        long scheduleAt = item.processedAt + (immediate ? 0 : this.delay);

        if (item.queued) {
            if (item.scheduledAt > scheduleAt) {
                queue.remove(item);
                item.scheduledAt = scheduleAt;
                queue.offer(item);
                notifyAll();
            }
        } else {
            item.queued = true;
            item.scheduledAt = scheduleAt;
            queue.offer(item);
            notifyAll();
        }
    }

    public synchronized E poll() {
        Item item = queue.poll();
        if (item == null) return null;
        item.queued = false;
        item.processedAt = System.currentTimeMillis();
        return item.element;
    }

    private synchronized void waitForData() throws InterruptedException {
        while (true) {
            if (isEmpty()) {
                wait();
            } else {
                Item item = queue.peek();
                long waitPeriod = item.scheduledAt - System.currentTimeMillis();
                if (waitPeriod > 0)
                    wait(waitPeriod);
                else
                    return;
            }
        }
    }

    public synchronized E blockingGet() throws InterruptedException {
        waitForData();
        return poll();
    }

    public interface UpdateHandler<E> {
        void process(E element, boolean immediate);
    }
}
