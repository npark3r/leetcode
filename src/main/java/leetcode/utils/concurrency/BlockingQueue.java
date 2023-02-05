package leetcode.utils.concurrency;

import java.util.LinkedList;
import java.util.Queue;

public class BlockingQueue<T> {

    private Queue<T> queue = new LinkedList<>();
    private int capacity;

    public BlockingQueue(int capacity) {
        this.capacity = capacity;
    }

    /**
     * Adds item to end of queue if there is sufficient capacity
     * @param item
     * @throws InterruptedException
     */
    public synchronized void putItem(T item) throws InterruptedException {
        if (queue.size() == capacity) {
            wait();
        }

        queue.add(item);
        notify();
        System.out.println("Producing: " + item);
    }

    /**
     * Fetches item at head of synchronised queue
     *
     * @throws InterruptedException
     */
    public synchronized T fetchItem() throws InterruptedException {
        if (queue.isEmpty()) {
            wait();
        }

        T item = queue.remove();
        notify();
        System.out.println("Consuming: " + item);
        return item;
    }
}
